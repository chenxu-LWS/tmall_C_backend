package com.tmall_backend.bysj.service.trolley;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.tmall_backend.bysj.common.constants.ErrInfo;
import com.tmall_backend.bysj.common.exception.BizException;
import com.tmall_backend.bysj.entity.Commodity;
import com.tmall_backend.bysj.entity.Trolley;
import com.tmall_backend.bysj.mapper.BrandMapper;
import com.tmall_backend.bysj.mapper.CategoryMapper;
import com.tmall_backend.bysj.mapper.CommodityMapper;
import com.tmall_backend.bysj.mapper.TrolleyMapper;
import com.tmall_backend.bysj.service.trolley.dto.CommodityForTrolleyDTO;
import com.tmall_backend.bysj.service.trolley.dto.TrolleyDTO;

/**
 * @author LiuWenshuo
 * Created on 2022-03-19
 */
@Service
public class TrolleyService {
    @Autowired
    TrolleyMapper trolleyMapper;
    @Autowired
    CommodityMapper commodityMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    BrandMapper brandMapper;

    public TrolleyDTO queryTrolleyByCustomerName(String name) {
        final Trolley trolley =
                trolleyMapper.queryTrolleyByCustomerName(name);
        final String commodityDetail = trolley.getCommodityDetail();
        final JSONArray objects = JSON.parseArray(commodityDetail);
        List<CommodityForTrolleyDTO> dtos = new ArrayList<>();
        for (Object object : objects) {
            Map<String, Integer> objDetail = (Map<String, Integer>) object;
            final Integer commodityId = objDetail.get("id");
            final Integer number = objDetail.get("number");
            System.out.println(commodityId);
            System.out.println(number);
            final Commodity commodity = commodityMapper
                    .queryCommodityById(commodityId);
            if (commodity == null) {
                throw new BizException(ErrInfo.COMMODITY_ID_NOT_EXISTS);
            }
            CommodityForTrolleyDTO dto = new CommodityForTrolleyDTO(commodity);
            dto.setCategory(categoryMapper.queryCategoryById(commodity.getCategoryID()));
            dto.setBrand(brandMapper.queryBrandById(commodity.getBrandID()));
            dto.setNumber(number);
            dtos.add(dto);
        }
        TrolleyDTO result = new TrolleyDTO();
        result.setCommodityNum(trolley.getCommodityNum());
        result.setCommodityDetail(dtos);
        result.setCustomerId(trolley.getCustomerId());
        return result;
    }

}
