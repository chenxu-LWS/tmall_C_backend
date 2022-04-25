package com.tmall_backend.bysj.controller.activity_picture;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmall_backend.bysj.common.ReturnListObject;
import com.tmall_backend.bysj.common.ReturnObject;
import com.tmall_backend.bysj.common.constants.ErrInfo;
import com.tmall_backend.bysj.common.exception.BizException;
import com.tmall_backend.bysj.service.activity_picture.ActivityPictureService;


/**
 * @author LiuWenshuo
 * Created on 2022-04-25
 */
@Controller
@RequestMapping("/api/activitypic")
public class ActivityPicController {
    @Autowired
    ActivityPictureService pictureService;

    @RequestMapping("/queryActivityPicByID")
    @ResponseBody
    public ReturnObject queryActivityPicByID(Integer activityId) {
        if (activityId == null) {
            return new ReturnObject(ErrInfo.PARAMETER_ERROR);
        }
        try {
            final String res = pictureService.queryActivityPicByID(activityId);
            return new ReturnObject(true, res, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }

    @RequestMapping("/queryActivityPics")
    @ResponseBody
    public ReturnListObject queryActivityPics(Integer number) {
        if (number == null) {
            return new ReturnListObject(ErrInfo.PARAMETER_ERROR);
        }
        try {
            final List<String> result = pictureService.queryActivityPics(number);
            return new ReturnListObject(true, new ArrayList<>(result), 0);
        } catch (BizException e) {
            return new ReturnListObject(e);
        }
    }
}
