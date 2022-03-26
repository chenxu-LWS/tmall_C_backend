package com.tmall_backend.bysj.service.behavior;

import static com.tmall_backend.bysj.common.constants.Constants.SESSION_KEY;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.tmall_backend.bysj.common.constants.ErrInfo;
import com.tmall_backend.bysj.common.exception.BizException;
import com.tmall_backend.bysj.entity.Commodity;
import com.tmall_backend.bysj.entity.Trolley;
import com.tmall_backend.bysj.mapper.CommodityMapper;
import com.tmall_backend.bysj.mapper.TrolleyMapper;
import com.tmall_backend.bysj.service.back_order_info.BackOrderInfoService;
import com.tmall_backend.bysj.service.commodity.CommodityService;
import com.tmall_backend.bysj.service.order_info.OrderInfoService;
import com.tmall_backend.bysj.service.order_info.dto.OrderInfoDTO;
import com.tmall_backend.bysj.service.trolley.TrolleyService;

/**
 * @author LiuWenshuo
 * Created on 2022-03-20
 */
@Service
public class BehaviorService {
    @Autowired
    CommodityMapper commodityMapper;
    @Autowired
    TrolleyMapper trolleyMapper;


    @Autowired
    TrolleyService trolleyService;
    @Autowired
    OrderInfoService orderInfoService;
    @Autowired
    CommodityService commodityService;
    @Autowired
    BackOrderInfoService backOrderInfoService;

    /**
     * 购物车商品数量增加/减少/加入购物车
     * @param req
     * @param commodityId
     * @param number
     * @return
     */
    public Integer increaseOrDecreaseCommodityToTrolley(HttpServletRequest req, Integer commodityId, Integer number) throws BizException{
        final Object userName = req.getSession().getAttribute(SESSION_KEY);
        if (userName == null) {
            throw new BizException(ErrInfo.GET_LOGIN_USER_ERROR);
        }
        if (commodityMapper.queryCommodityById(commodityId) == null) {
            throw new BizException(ErrInfo.COMMODITY_ID_NOT_EXISTS);
        }
        final Trolley trolley = trolleyMapper.queryTrolleyByCustomerName((String) userName);
        final String commodityDetail = trolley.getCommodityDetail();
        final List<Object> details = JSON.parseArray(commodityDetail);
        Iterator<Object> iterator = details.listIterator();
        Map<String, Integer> alreadyExists = null;
        while(iterator.hasNext()) {
            Map<String, Integer> current = (Map<String, Integer>)iterator.next();
            final Integer currentCommodityId = current.get("id");
            if (currentCommodityId.equals(commodityId)) {
                alreadyExists = current;
                iterator.remove();
            }
        }
        if (alreadyExists != null) {// 如果当前购物车已经存在该商品，则数量+number即可
            alreadyExists.put("number", alreadyExists.get("number") + number);
            details.add(0, alreadyExists);
            return trolleyMapper.resetCommodityDetail(trolley.getCustomerId(), JSON.toJSONString(details));
        } else {
            if (number < 0) {
                throw new BizException(ErrInfo.DECREASE_FROM_TROLLEY);
            }
            // 如果当前购物车不存在该商品，则需要正常加购一个商品
            return trolleyMapper.addCommodityToTrolley(trolley.getCustomerId(), commodityId, number);
        }
    }

    /**
     * 购物车中删除某个商品
     * @param req
     * @param commodityId
     * @return
     * @throws BizException
     */
    public Integer deleteCommodityFromTrolley(HttpServletRequest req, Integer commodityId) throws BizException{
        final Object userName = req.getSession().getAttribute(SESSION_KEY);
        if (userName == null) {
            throw new BizException(ErrInfo.GET_LOGIN_USER_ERROR);
        }
        if (commodityMapper.queryCommodityById(commodityId) == null) {
            throw new BizException(ErrInfo.COMMODITY_ID_NOT_EXISTS);
        }
        final Trolley trolley = trolleyMapper.queryTrolleyByCustomerName((String) userName);
        final String commodityDetail = trolley.getCommodityDetail();
        final List<Object> details = JSON.parseArray(commodityDetail);
        Iterator<Object> iterator = details.listIterator();
        Map<String, Integer> alreadyExists = null;
        while(iterator.hasNext()) {
            Map<String, Integer> current = (Map<String, Integer>)iterator.next();
            final Integer currentCommodityId = current.get("id");
            if (currentCommodityId.equals(commodityId)) {
                alreadyExists = current;
                iterator.remove();
            }
        }
        if (alreadyExists == null) {
            throw new BizException(ErrInfo.DELETE_FROM_TROLLEY);
        }
        return trolleyMapper.resetCommodityDetail(trolley.getCustomerId(), JSON.toJSONString(details));
    }

    /**
     * 下单
     * @param detail
     * @return
     */
    public Integer buy(HttpServletRequest req, Map<String, Object> detail, Double orderPrice) {
        final Object userName = req.getSession().getAttribute(SESSION_KEY);
        if (userName == null) {
            throw new BizException(ErrInfo.GET_LOGIN_USER_ERROR);
        }
        // 校验
        //"detail":"{"5":{"number":1,"price":109.9},"6":{"number":2,"price":109.9}}"
        List<Integer> commodityBoughtList = new ArrayList<>();
        detail.forEach((commodityId, v) -> {
            try {
                Integer.parseInt(commodityId);
            } catch (Exception e) {
                throw new BizException(ErrInfo.ORDERINFO_DETAIL_FORMAT_ERROR);
            }
            Map<String, Object> objV = (Map<String, Object>) v;
            if(objV.size() != 2 || !objV.containsKey("number") || !objV.containsKey("price")) {
                throw new BizException(ErrInfo.ORDERINFO_DETAIL_FORMAT_ERROR);
            }
            final Commodity commodity = commodityMapper.queryCommodityById(Integer.parseInt(commodityId));
            if (commodity == null) {
                throw new BizException(ErrInfo.COMMODITY_ID_NOT_EXISTS);
            }
            if (commodity.getStatus() != 1) {
                throw new BizException(ErrInfo.BUY_ERROR_COMMODITY_STATUS_NOT_ONSALE);
            }
            if ((Integer) objV.get("number") > commodity.getInventory()) {
                throw new BizException(ErrInfo.BUY_ERROR_INVENTORY_NOT_AVAILABLE);
            }
            // 默认状态为进行中(1)
            objV.put("status", 1);
            detail.put(commodityId, objV);
            commodityBoughtList.add(Integer.parseInt(commodityId));
            // 库存扣减
            commodityMapper.increaseOrDecreaseInventory(Integer.parseInt(commodityId), - (Integer) objV.get("number"));
            // 销量增加
            commodityMapper.increaseOrDecreaseSaleVolume(Integer.parseInt(commodityId), (Integer) objV.get("number"));
        });
        // 新增订单
        orderInfoService.insertOrderInfo((String) userName, JSON.toJSONString(detail), orderPrice);
        // 购物车商品删除
        final Trolley trolley = trolleyMapper.queryTrolleyByCustomerName((String) userName);
        final String oldTrolleyDetail = trolley.getCommodityDetail();
        final List<Object> details = JSON.parseArray(oldTrolleyDetail);
        Iterator<Object> iterator = details.listIterator();
        while (iterator.hasNext()) {
            Map<String, Integer> current = (Map<String, Integer>)iterator.next();
            if (commodityBoughtList.contains(current.get("id"))) {
                iterator.remove();
            }
        }
        trolleyMapper.resetCommodityDetail(trolley.getCustomerId(), JSON.toJSONString(details));
        return 0;
    }

    /**
     * 将订单状态中所有商品的状态进行变更
     * @return
     */
    public Integer updateStatusInOrderInfo(Integer orderInfoId, Integer newStatus) throws BizException {
        final OrderInfoDTO orderInfoDTO = orderInfoService.queryOrderInfoById(orderInfoId);
        final Map<String, Object> detail = orderInfoDTO.getDetail();
        List<Integer> comIdsInInfo = new ArrayList<>();
        detail.forEach((k, v) -> comIdsInInfo.add(Integer.parseInt(k)));
        comIdsInInfo.forEach(commodityId -> orderInfoService.updateCommodityStatusInOrderInfo(orderInfoId, commodityId, newStatus));
        return 0;
    }

    /**
     * 变更订单中某个商品的状态
     * @param orderInfoId
     * @param commodityId
     * @param newStatus
     * @return
     */
    public Integer updateStatusInOrderInfoForCom(Integer orderInfoId, Integer commodityId, Integer newStatus) {
        final OrderInfoDTO orderInfoDTO = orderInfoService.queryOrderInfoById(orderInfoId);
        AtomicBoolean exists = new AtomicBoolean(false);
        orderInfoDTO.getDetail().forEach((k, v) -> {
            if (commodityId == Integer.parseInt(k)) {
                exists.set(true);
            }
        });
        if (!exists.get()) {
            throw new BizException(ErrInfo.ORDERINFO_DETAIL_CONTAINS_COMM_NOT_EXISTS);
        }
        return orderInfoService.updateCommodityStatusInOrderInfo(orderInfoId, commodityId, newStatus);
    }

    /**
     * 退款
     * @param orderInfoId
     * @param commodityId
     * @return
     * @throws BizException
     */
    public Integer returnCommodity(Integer orderInfoId, Integer commodityId) throws BizException{
        final OrderInfoDTO orderInfoDTO = orderInfoService.queryOrderInfoById(orderInfoId);
        AtomicReference<Integer> num = new AtomicReference<>(0);
        AtomicReference<Double> price = new AtomicReference<>(0.0);
        final Map<String, Object> detail1 = orderInfoDTO.getDetail();
        detail1.forEach((comId, detail) -> {
            System.out.println(detail);
            if (Integer.parseInt(comId) == commodityId) {
                Map<String, Object> jsonDetail = (Map<String, Object>) detail;
                num.set((Integer) jsonDetail.get("number"));
                System.out.println(jsonDetail.get("price").toString());
                price.set((Double.parseDouble(jsonDetail.get("price").toString())));
                if ((Integer) jsonDetail.get("status") == 4) {
                    throw new BizException(ErrInfo.RETURN_COM_ERROR_ALREADY_RETURN);
                }
            }
        });
        // 将退货信息更新到订单信息
        orderInfoService.updateCommodityStatusInOrderInfo(orderInfoId,
                commodityId, 4);
        // 将货物库存还原
        commodityService.increaseOrDecreaseInventory(commodityId, num.get());
        // 将退货信息入库
        return backOrderInfoService.insertBackOrderInfo(
                orderInfoId, commodityId, num.get(), price.get() * num.get());
    }
}
