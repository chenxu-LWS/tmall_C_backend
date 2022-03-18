package com.tmall_backend.bysj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.tmall_backend.bysj.entity.Commodity;


/**
 * @author LiuWenshuo
 * Created on 2022-03-08
 */
@Mapper
@Component
public interface CommodityMapper {
    public List<Commodity> queryCommodity();
    public Commodity queryCommodityById(Integer id);
    public List<Commodity> queryCommodityByCategoryId(@Param("list") List<Integer> categories);
    // 品牌ID筛选
    public List<Commodity> queryCommodityByBrandIdByPage(@Param("brandId") Integer brandId,
            @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);
    public Integer queryCommodityByBrandIdTotalNum(Integer brandId);

    // 查询所有
    public List<Commodity> queryAllByPage(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);
    public Integer queryAllTotalNum();

    public Integer updateCommodityProperties(@Param("id") Integer id, @Param("props") String props);
    public Integer updateCommodityStatus(@Param("id") Integer id, @Param("status") Integer status);
    public Integer updateCommodityPrice(@Param("id") Integer id, @Param("newPrice") Integer newPrice);
    public Integer increaseOrDecreaseInventory(@Param("id") Integer id, @Param("stockNum") Integer stockNum);
}
