package com.tmall_backend.bysj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.tmall_backend.bysj.entity.OrderInfo;


/**
 * @author LiuWenshuo
 * Created on 2022-03-11
 */
@Mapper
@Component
public interface OrderInfoMapper {
    public OrderInfo queryOrderInfoById(Integer id);
    public List<OrderInfo> queryOrderInfoByCustomerNameByPage(
            @Param("customerName") String customerName,
            @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);
    public Integer queryOrderInfoByCustomerNameTotalNum(String customerName);

    public Integer insertOrderInfo(OrderInfo orderInfo);
    public Integer updateCommodityStatusInOrderInfo(OrderInfo orderInfo);
}
