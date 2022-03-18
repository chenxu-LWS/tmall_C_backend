package com.tmall_backend.bysj.controller.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmall_backend.bysj.common.ReturnObject;
import com.tmall_backend.bysj.common.constants.ErrInfo;
import com.tmall_backend.bysj.service.brand.BrandService;


/**
 * @author LiuWenshuo
 * Created on 2022-03-07
 */
@Controller
@RequestMapping("/api/brand")
public class BrandController {
    @Autowired
    BrandService brandService;

    @RequestMapping("/queryById")
    @ResponseBody
    public ReturnObject queryBrandById(Integer id) {
        if (id == null) {
            return new ReturnObject(ErrInfo.PARAMETER_ERROR);
        }
        return new ReturnObject(true, brandService.queryBrandById(id), 0);
    }
}
