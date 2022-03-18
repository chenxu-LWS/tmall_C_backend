package com.tmall_backend.bysj.controller.commodity_picture;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmall_backend.bysj.common.ReturnListObject;
import com.tmall_backend.bysj.common.exception.BizException;
import com.tmall_backend.bysj.service.commodity_picture.CommodityPictureService;

/**
 * @author LiuWenshuo
 * Created on 2022-03-16
 */
@Controller
@RequestMapping("/api/commoditypic")
public class CommodityPicController {
    @Autowired
    CommodityPictureService pictureService;
    @RequestMapping("/queryMainPicByCommodityId")
    @ResponseBody
    public ReturnListObject queryMainPicByCommodityId (Integer commodityId) {
        try {
            final List<String> pictures = pictureService.queryMainPicByCommodityId(commodityId);
            return new ReturnListObject(true,
                    new ArrayList<>(pictures), 0);
        }catch (BizException e) {
            return new ReturnListObject(e);
        }
    }

    @RequestMapping("/queryDetailPicByCommodityId")
    @ResponseBody
    public ReturnListObject queryDetailPicByCommodityId (Integer commodityId) {
        try {
            final List<String> pictures = pictureService.queryDetailPicByCommodityId(commodityId);
            return new ReturnListObject(true,
                    new ArrayList<>(pictures), 0);
        }catch (BizException e) {
            return new ReturnListObject(e);
        }
    }
}
