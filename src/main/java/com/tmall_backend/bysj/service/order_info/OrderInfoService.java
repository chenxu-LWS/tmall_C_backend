package com.tmall_backend.bysj.service.order_info;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.tmall_backend.bysj.common.constants.ErrInfo;
import com.tmall_backend.bysj.common.exception.BizException;
import com.tmall_backend.bysj.common.page.PageBean;
import com.tmall_backend.bysj.entity.OrderInfo;
import com.tmall_backend.bysj.mapper.OrderInfoMapper;
import com.tmall_backend.bysj.service.order_info.dto.OrderInfoDTO;

/**
 * @author LiuWenshuo
 * Created on 2022-03-11
 */
@Service
public class OrderInfoService {
    @Autowired
    OrderInfoMapper orderInfoMapper;

    public OrderInfoDTO queryOrderInfoById(Integer id) throws BizException {
        final OrderInfo orderInfo = orderInfoMapper.queryOrderInfoById(id);
        if (orderInfo == null) {
            throw new BizException(ErrInfo.ORDERINFO_ID_NOT_EXISTS);
        }
        return new OrderInfoDTO(orderInfo);
    }

    public PageBean<OrderInfoDTO> queryOrderInfoByCustomerNameByPage(String customerName, Integer pageNo, Integer pageSize) {
        final List<OrderInfo> orderInfos =
                orderInfoMapper.queryOrderInfoByCustomerNameByPage(customerName, pageNo * pageSize, pageSize);
        List<OrderInfoDTO> dtos = new ArrayList<>();
        orderInfos.forEach(orderInfo -> dtos.add(new OrderInfoDTO(orderInfo)));
        PageBean<OrderInfoDTO> result = new PageBean<>();
        result.setPageNo(pageNo);
        result.setPageSize(pageSize);
        result.setList(dtos);
        result.setTotalNum(orderInfoMapper.queryOrderInfoByCustomerNameTotalNum(customerName));
        return result;
    }

    public Integer insertOrderInfo(String customerName, String detail, Double orderPrice) {
        final OrderInfo orderInfo = new OrderInfo(null, customerName, detail, orderPrice, null);
        orderInfoMapper.insertOrderInfo(orderInfo);
        return orderInfo.getId();
    }

    public Integer updateCommodityStatusInOrderInfo(Integer orderInfoId,
            Integer commodityId, Integer newStatus) {
        final OrderInfo oldOrderInfo = orderInfoMapper.queryOrderInfoById(orderInfoId);
        if (oldOrderInfo == null) {
            throw new BizException(ErrInfo.ORDERINFO_ID_NOT_EXISTS);
        }
        final String oldDetail = oldOrderInfo.getDetail();
        Map<String, Object> oldDetailJSON = JSON.parseObject(oldDetail);
        oldDetailJSON.forEach((k, v) -> {
            Map<String, Object> vMap = (Map<String, Object>) v;
            if (Integer.parseInt(k) == commodityId) {
                vMap.put("status", newStatus);
            }
        });
        OrderInfo orderInfo = new OrderInfo(orderInfoId, null,
                JSON.toJSONString(oldDetailJSON), null, null);
        return orderInfoMapper.updateCommodityStatusInOrderInfo(orderInfo);
    }
}
