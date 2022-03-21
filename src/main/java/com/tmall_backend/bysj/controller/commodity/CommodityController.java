package com.tmall_backend.bysj.controller.commodity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmall_backend.bysj.common.ReturnListObject;
import com.tmall_backend.bysj.common.ReturnObject;
import com.tmall_backend.bysj.common.ReturnPageObject;
import com.tmall_backend.bysj.common.constants.ErrInfo;
import com.tmall_backend.bysj.common.exception.BizException;
import com.tmall_backend.bysj.common.page.PageBean;
import com.tmall_backend.bysj.controller.commodity.dto.QueryCommodityByConditionDTO;
import com.tmall_backend.bysj.entity.Brand;
import com.tmall_backend.bysj.service.commodity.CommodityService;
import com.tmall_backend.bysj.service.commodity.dto.CommodityDTO;

/**
 * @author LiuWenshuo
 * Created on 2022-03-18
 */
@Controller
@RequestMapping("/api/commodity")
public class CommodityController {
    @Autowired
    CommodityService commodityService;

    @GetMapping("/queryPropsByCategoryId")
    @ResponseBody
    public ReturnObject queryPropsByCategoryId(Integer categoryId) {
        if (categoryId == null) {
            return new ReturnObject(ErrInfo.PARAMETER_ERROR);
        }
        try {
            final Map<String, Set<String>> propMap = commodityService.queryPropsByCategoryId(categoryId);
            return new ReturnObject(true, propMap, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }

    @GetMapping("/queryBrandsByCategoryId")
    @ResponseBody
    public ReturnListObject queryBrandsByCategoryId(Integer categoryId) {
        if (categoryId == null) {
            return new ReturnListObject(ErrInfo.PARAMETER_ERROR);
        }
        try {
            final List<Brand> brands = commodityService.queryBrandsByCategoryId(categoryId);
            return new ReturnListObject(true, new ArrayList<>(brands), 0);
        } catch (BizException e) {
            return new ReturnListObject(e);
        }
    }

    @PostMapping("/queryCommodityByConditionByPage")
    @ResponseBody
    public ReturnPageObject<CommodityDTO> queryCommodityByConditionByPage(@RequestBody QueryCommodityByConditionDTO dto) {
        final PageBean<CommodityDTO> commodityDTOPageBean = commodityService
                .queryCommodityByConditionByPage(dto.getCategoryId(), dto.getBrandId(),
                        dto.getPropK(), dto.getPropV(), dto.getPriceLow(), dto.getPriceHigh(),
                        dto.getSortedBy(), dto.getSortDesc(), dto.getOnlyOnSale(), dto.getPageNo(), dto.getPageSize());
        return new ReturnPageObject<>(true, commodityDTOPageBean, 0);
    }

    @RequestMapping("/queryById")
    @ResponseBody
    public ReturnObject queryById(Integer id) {
        try {
            final CommodityDTO dto = commodityService.queryById(id);
            return new ReturnObject(true, dto, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }
}
