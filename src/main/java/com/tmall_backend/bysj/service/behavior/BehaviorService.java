package com.tmall_backend.bysj.service.behavior;

import static com.tmall_backend.bysj.common.constants.Constants.SESSION_KEY;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import com.tmall_backend.bysj.service.activity.ActivityService;
import com.tmall_backend.bysj.service.activity.dto.ActivityDTO;
import com.tmall_backend.bysj.service.back_order_info.BackOrderInfoService;
import com.tmall_backend.bysj.service.behavior.dto.GetActFromOrderCommodityBaseDTO;
import com.tmall_backend.bysj.service.behavior.dto.RealPriceFromOrderDTO;
import com.tmall_backend.bysj.service.behavior.dto.RealPriceFromOrderSingleComDTO;
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
    @Autowired
    ActivityService activityService;

    /**
     * ???????????????????????????/??????/???????????????
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
        if (alreadyExists != null) {// ??????????????????????????????????????????????????????+number??????
            alreadyExists.put("number", alreadyExists.get("number") + number);
            details.add(0, alreadyExists);
            return trolleyMapper.resetCommodityDetail(trolley.getCustomerId(), JSON.toJSONString(details));
        } else {
            if (number < 0) {
                throw new BizException(ErrInfo.DECREASE_FROM_TROLLEY);
            }
            // ???????????????????????????????????????????????????????????????????????????
            return trolleyMapper.addCommodityToTrolley(trolley.getCustomerId(), commodityId, number);
        }
    }

    /**
     * ??????????????????????????????
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
     * ??????
     * @param detail
     * @return
     */
    public Integer buy(HttpServletRequest req, Map<String, Object> detail, Double orderPrice) {
        final Object userName = req.getSession().getAttribute(SESSION_KEY);
        if (userName == null) {
            throw new BizException(ErrInfo.GET_LOGIN_USER_ERROR);
        }
        // ??????
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
            // ????????????????????????(1)
            objV.put("status", 1);
            detail.put(commodityId, objV);
            commodityBoughtList.add(Integer.parseInt(commodityId));
            // ????????????
            commodityService.increaseOrDecreaseInventory(Integer.parseInt(commodityId), - (Integer) objV.get("number"));
            // ????????????
            commodityService.increaseOrDecreaseSaleVolume(Integer.parseInt(commodityId), (Integer) objV.get("number"));
        });
        // ????????????
        Integer newOrderInfoId = orderInfoService.insertOrderInfo((String) userName, JSON.toJSONString(detail), orderPrice);
        // ?????????????????????
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
        return newOrderInfoId;
    }

    /**
     * ??????????????????????????????????????????
     * @param detail
     * @return
     */
    public List<ActivityDTO> getActivitiesFromOrder(Map<String, Object> detail) {
        Map<Integer, Set<ActivityDTO>> comIdActMap = new HashMap<>();
        Map<Integer, Set<GetActFromOrderCommodityBaseDTO>> actComInfoMap = new HashMap<>();
        Map<Integer, ActivityDTO> activityMap = new HashMap<>();
        // 1. ???????????????????????????????????????????????????????????????
        detail.forEach((commodityId, v) -> {
            Integer comId;
            try {
                comId = Integer.parseInt(commodityId);
            } catch (Exception e) {
                throw new BizException(ErrInfo.ORDERINFO_DETAIL_FORMAT_ERROR);
            }
            Map<String, Object> objV = (Map<String, Object>) v;
            if(objV.size() != 2 || !objV.containsKey("number") || !objV.containsKey("price")) {
                throw new BizException(ErrInfo.ORDERINFO_DETAIL_FORMAT_ERROR);
            }
            final Commodity commodity = commodityMapper.queryCommodityById(comId);
            if (commodity == null) {
                throw new BizException(ErrInfo.COMMODITY_ID_NOT_EXISTS);
            }
            final List<ActivityDTO> activities = activityService.queryActivityByCommodityId(comId);
            activities.forEach(activity -> {
                activityMap.put(activity.getId(), activity);
                final Set<ActivityDTO> activityDTOS = comIdActMap.get(comId);
                if (activityDTOS == null) {
                    Set<ActivityDTO> newSet = new HashSet<>();
                    newSet.add(activity);
                    comIdActMap.put(comId, newSet);
                } else {
                    activityDTOS.add(activity);
                    comIdActMap.put(comId, activityDTOS);
                }

                final Set<GetActFromOrderCommodityBaseDTO> comInfos = actComInfoMap.get(activity.getId());
                if (comInfos == null) {
                    Set<GetActFromOrderCommodityBaseDTO> newSet = new HashSet<>();
                    GetActFromOrderCommodityBaseDTO newDTO =
                            new GetActFromOrderCommodityBaseDTO(Integer.parseInt(commodityId),
                                    Integer.parseInt(String.valueOf(objV.get("number")))
                                    , Double.parseDouble(String.valueOf(objV.get("price"))));
                    newSet.add(newDTO);
                    actComInfoMap.put(activity.getId(), newSet);
                } else {
                    GetActFromOrderCommodityBaseDTO newDTO =
                            new GetActFromOrderCommodityBaseDTO(Integer.parseInt(commodityId),
                                    Integer.parseInt(String.valueOf(objV.get("number")))
                                    , Double.parseDouble(String.valueOf(objV.get("price"))));
                    comInfos.add(newDTO);
                    actComInfoMap.put(activity.getId(), comInfos);
                }
            });
        });

        // 2.?????????????????????????????????????????????????????????????????????
        List<ActivityDTO> finalAvailableActs = new ArrayList<>();
        actComInfoMap.forEach((activityId, commodityBaseDTOSet) -> {
            final ActivityDTO activityDTO = activityMap.get(activityId);
            final Integer type = activityDTO.getType();
            if (type == 1) {// ??????????????????????????????????????????????????????????????????????????????
                double price = 0.0;
                for (GetActFromOrderCommodityBaseDTO commodityBaseDTO : commodityBaseDTOSet) {
                    price += commodityBaseDTO.getOldPrice() * commodityBaseDTO.getNumber();
                }
                if (price > activityDTO.getINT01()) {// ??????????????????????????????????????????????????????
                    finalAvailableActs.add(activityDTO);
                }
            } else if (type == 2) {// ????????????????????????????????????????????????????????????????????????xxx??????
                double price = 0.0;
                for (GetActFromOrderCommodityBaseDTO commodityBaseDTO : commodityBaseDTOSet) {
                    price += commodityBaseDTO.getOldPrice() * commodityBaseDTO.getNumber();
                }
                System.out.println(price);
                if (price > activityDTO.getINT01() && price > activityDTO.getINT02()) {
                    finalAvailableActs.add(activityDTO);
                }
            } else if (type == 3) {// ?????????????????????????????????????????????????????????
                finalAvailableActs.add(activityDTO);
            }
        });
        return finalAvailableActs;
    }

    /**
     * ??????????????????????????????????????????????????????
     * @param detail
     * @return
     */
    public RealPriceFromOrderDTO getRealPriceFromOrder(Map<String, Object> detail, Integer activityId) {
        // ??????????????????????????????
        final List<ActivityDTO> activitiesFromOrder = getActivitiesFromOrder(detail);
        ActivityDTO currentActivityDTO = null;
        for (ActivityDTO dto : activitiesFromOrder) {
            if (dto.getId().equals(activityId)) {
                currentActivityDTO = dto;
                break;
            }
        }
        if (currentActivityDTO == null) {
            throw new BizException(ErrInfo.CURRENT_ACTIVITY_NOT_AVAILABLE);
        }
        // ??????????????????????????????
        Map<Integer, RealPriceFromOrderSingleComDTO> inActivityComs = new HashMap<>();
        detail.forEach((commodityId, v) -> {
            Integer comId;
            try {
                comId = Integer.parseInt(commodityId);
            } catch (Exception e) {
                throw new BizException(ErrInfo.ORDERINFO_DETAIL_FORMAT_ERROR);
            }
            Map<String, Object> objV = (Map<String, Object>) v;
            if(objV.size() != 2 || !objV.containsKey("number") || !objV.containsKey("price")) {
                throw new BizException(ErrInfo.ORDERINFO_DETAIL_FORMAT_ERROR);
            }
            final Commodity commodity = commodityMapper.queryCommodityById(comId);
            if (commodity == null) {
                throw new BizException(ErrInfo.COMMODITY_ID_NOT_EXISTS);
            }
            final List<ActivityDTO> activities = activityService.queryActivityByCommodityId(comId);
            for (ActivityDTO activity : activities) {
                if (activity.getId().equals(activityId)) {// ??????????????????????????????????????????
                    RealPriceFromOrderSingleComDTO dto =
                            new RealPriceFromOrderSingleComDTO(comId,
                                    Double.parseDouble(String.valueOf(objV.get("price"))),
                                    0.0,
                                    Integer.parseInt(String.valueOf(objV.get("number"))));
                    inActivityComs.put(comId, dto);
                }
            }
        });
        double allMinus = 0.0;// ?????????
        AtomicReference<Double> totalOld = new AtomicReference<>(0.0);// ?????????
        // ???????????????????????????????????????
        if (currentActivityDTO.getType() == 1) {// ???????????????
            allMinus = currentActivityDTO.getINT01();
            inActivityComs.forEach((comId, commodityDTO) ->
                    totalOld.updateAndGet(v -> v + commodityDTO.getOldPrice() * commodityDTO.getNumber()));
            ActivityDTO finalCurrentActivityDTO = currentActivityDTO;
            inActivityComs.forEach((comId, commodityDTO) -> commodityDTO.setActPrice(getFormatDouble(commodityDTO.getOldPrice() -
                    finalCurrentActivityDTO.getINT01() * (commodityDTO.getOldPrice() / totalOld.get()))));
        } else if (currentActivityDTO.getType() == 2) {// ????????????
            inActivityComs.forEach((comId, commodityDTO) ->
                    totalOld.updateAndGet(v -> v + commodityDTO.getOldPrice() * commodityDTO.getNumber()));
            // ????????????????????????????????????
            int actNum = totalOld.get().intValue() / currentActivityDTO.getINT01();
            System.out.println(actNum);
            // ??????????????????????????????
            allMinus = actNum * currentActivityDTO.getINT02();
            ActivityDTO finalCurrentActivityDTO = currentActivityDTO;
            inActivityComs.forEach((comId, commodityDTO) -> commodityDTO.setActPrice(getFormatDouble(commodityDTO.getOldPrice() -
                    finalCurrentActivityDTO.getINT02() * (commodityDTO.getOldPrice() / totalOld.get()))));
        } else if (currentActivityDTO.getType() == 3) {// ????????????
            inActivityComs.forEach((comId, commodityDTO) ->
                    totalOld.updateAndGet(v -> v + commodityDTO.getOldPrice() * commodityDTO.getNumber()));
            // ??????????????????????????????
            allMinus = totalOld.get() * 0.1 * (10 - currentActivityDTO.getINT01());
            ActivityDTO finalCurrentActivityDTO = currentActivityDTO;
            inActivityComs.forEach((comId, commodityDTO) -> commodityDTO.setActPrice(getFormatDouble(commodityDTO.getOldPrice() -
                    finalCurrentActivityDTO.getINT01() * (commodityDTO.getOldPrice() / totalOld.get()))));
        } else {
            System.out.println("type?????????...???????????????");
            throw new BizException(ErrInfo.PARAMETER_ERROR);
        }
        System.out.println(inActivityComs);
        // ??????????????????????????????
        List<RealPriceFromOrderSingleComDTO> resDetailList = new ArrayList<>();
        inActivityComs.forEach((comId, commodityDTO) -> resDetailList.add(commodityDTO));
        detail.forEach((commodityId, comDetail) -> {
            Integer comId;
            try {
                comId = Integer.parseInt(commodityId);
            } catch (Exception e) {
                throw new BizException(ErrInfo.ORDERINFO_DETAIL_FORMAT_ERROR);
            }
            Map<String, Object> objV = (Map<String, Object>) comDetail;
            if (!inActivityComs.containsKey(comId)) { // ???????????????????????????????????????
                final double oldPrice = Double.parseDouble(String.valueOf(objV.get("price")));
                final int number = Integer.parseInt(String.valueOf(objV.get("number")));
                RealPriceFromOrderSingleComDTO dto =
                        new RealPriceFromOrderSingleComDTO(comId, oldPrice, oldPrice, number);
                totalOld.updateAndGet(v -> v + oldPrice * number);
                resDetailList.add(dto);
            }
        });
        RealPriceFromOrderDTO result = new RealPriceFromOrderDTO();
        result.setCommodityDetails(resDetailList);
        result.setTotalRealPrice(getFormatDouble(totalOld.get()));
        result.setTotalActPrice(getFormatDouble(totalOld.get() - allMinus));
        result.setActivity(currentActivityDTO);
        System.out.println(result);
        return result;
    }

    /**
     * ???????????????????????????????????????????????????
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
     * ????????????????????????????????????
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
     * ??????
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
        // ????????????????????????????????????
        orderInfoService.updateCommodityStatusInOrderInfo(orderInfoId,
                commodityId, 4);
        // ?????????????????????
        commodityService.increaseOrDecreaseInventory(commodityId, num.get());
        // ????????????????????????
        commodityService.increaseOrDecreaseSaleVolume(commodityId, -num.get());
        // ?????????????????????
        return backOrderInfoService.insertBackOrderInfo(
                orderInfoId, commodityId, num.get(), price.get() * num.get());
    }

    private double getFormatDouble(double num) {
        BigDecimal b = new BigDecimal(num);
        return b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
