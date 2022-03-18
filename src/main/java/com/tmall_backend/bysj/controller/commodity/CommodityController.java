package com.tmall_backend.bysj.controller.commodity;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmall_backend.bysj.common.ReturnObject;
import com.tmall_backend.bysj.common.constants.ErrInfo;
import com.tmall_backend.bysj.common.exception.BizException;
import com.tmall_backend.bysj.service.commodity.CommodityService;

/**
 * @author LiuWenshuo
 * Created on 2022-03-18
 */
@Controller
@RequestMapping("/api/commodity")
public class CommodityController {
    @Autowired
    CommodityService commodityService;

    @RequestMapping("/queryPropsByCategoryId")
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
}
