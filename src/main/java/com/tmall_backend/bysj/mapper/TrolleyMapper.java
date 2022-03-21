package com.tmall_backend.bysj.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.tmall_backend.bysj.entity.Trolley;

/**
 * @author LiuWenshuo
 * Created on 2022-03-19
 */
@Mapper
@Component
public interface TrolleyMapper {
    public Trolley queryTrolleyByCustomerName(String name);
    public Integer insert(Integer customerId);
    public Integer addCommodityToTrolley(@Param("customerId") Integer customerId,
            @Param("commodityId") Integer commodityId, @Param("number") Integer number);

    public Integer resetCommodityDetail(@Param("customerId") Integer customerId, @Param("commodityDetail") String commodityDetail);
}
