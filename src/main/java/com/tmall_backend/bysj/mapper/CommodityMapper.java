package com.tmall_backend.bysj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.tmall_backend.bysj.entity.Commodity;
import com.tmall_backend.bysj.entity.CommoditySaleVolume;
import com.tmall_backend.bysj.entity.CommoditySaleVolumeByBrand;


/**
 * @author LiuWenshuo
 * Created on 2022-03-08
 */
@Mapper
@Component
public interface CommodityMapper {
    public List<Commodity> queryCommodityByConditionByPage(@Param("ambiName") String ambiName,
            @Param("list") List<Integer> categories, @Param("brandId") Integer brandId,
            @Param("propK") String propK, @Param("propV") String propV,
            @Param("priceLow") Double priceLow, @Param("priceHigh") Double priceHigh,
            @Param("sortedBy") String sortedBy, @Param("sortDesc") Boolean sortDesc, @Param("onlyOnSale") Boolean onlyOnSale,
            @Param("offset") Integer offset,
            @Param("pageSize") Integer pageSize);
    public Integer queryCommodityByConditionTotalNum(@Param("ambiName") String ambiName,
            @Param("list") List<Integer> categories, @Param("brandId") Integer brandId,
            @Param("priceLow") Double priceLow, @Param("priceHigh") Double priceHigh,
            @Param("propK") String propK, @Param("propV") String propV, @Param("onlyOnSale") Boolean onlyOnSale);

    public Commodity queryCommodityById(Integer id);
    public List<Commodity> queryCommodityByCategoryId(@Param("list") List<Integer> categories);
    // 品牌ID筛选
    public List<Commodity> queryCommodityByBrandIdByPage(@Param("brandId") Integer brandId,
            @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);
    public Integer queryCommodityByBrandIdTotalNum(Integer brandId);

    // 查询所有
    public List<Commodity> queryAllByPage(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);
    public Integer queryAllTotalNum();

    public List<Commodity> ambiQueryCommodityByPage(@Param("ambiName") String ambiName,
            @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);
    public Integer ambiQueryCommodityTotalNum(@Param("ambiName") String ambiName);

    public List<CommoditySaleVolume> getTopNCategories(@Param("topN") Integer topN);
    public List<Commodity> getTopNCommodities(@Param("topN") Integer topN);
    public List<CommoditySaleVolumeByBrand> getTopNBrands(@Param("topN") Integer topN);

    public Integer updateCommodityProperties(@Param("id") Integer id, @Param("props") String props);
    public Integer updateCommodityStatus(@Param("id") Integer id, @Param("status") Integer status);
    public Integer updateCommodityPrice(@Param("id") Integer id, @Param("newPrice") Integer newPrice);
    public Integer increaseOrDecreaseInventory(@Param("id") Integer id, @Param("stockNum") Integer stockNum);
    public Integer increaseOrDecreaseSaleVolume(@Param("id") Integer id, @Param("number") Integer number);
}
