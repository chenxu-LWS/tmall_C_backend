package com.tmall_backend.bysj.controller.activity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.tmall_backend.bysj.controller.PageBaseDTO;
import com.tmall_backend.bysj.controller.activity.dto.QueryActivityByCommodityIdDTO;
import com.tmall_backend.bysj.entity.Activity;
import com.tmall_backend.bysj.service.activity.ActivityService;
import com.tmall_backend.bysj.service.activity.dto.ActivityDTO;


/**
 * @author LiuWenshuo
 * Created on 2022-03-27
 */
@Controller
@RequestMapping("/api/activity")
public class ActivityController {
    @Autowired
    ActivityService activityService;

    @ResponseBody
    @PostMapping("/queryOnlineByPage")
    public ReturnPageObject<Activity> queryOnlineByPage(@RequestBody PageBaseDTO dto) {
        if (dto.hasNull()) {
            return new ReturnPageObject<>(ErrInfo.PARAMETER_ERROR);
        }
        try {
            final PageBean<Activity> activityPageBean =
                    activityService.queryOnlineActivityByPage(dto.getPageNo(), dto.getPageSize());
            return new ReturnPageObject<>(true, activityPageBean, 0);
        } catch (BizException e) {
            return new ReturnPageObject<>(e);
        }
    }

    @ResponseBody
    @RequestMapping("/queryById")
    public ReturnObject queryById(Integer id) {
        if (id == null) {
            return new ReturnObject(ErrInfo.PARAMETER_ERROR);
        }
        try {
            final Activity activity =
                    activityService.queryActivityById(id);
            return new ReturnObject(true, activity, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }

    @ResponseBody
    @RequestMapping("/queryByCommodityId")
    public ReturnListObject queryByCommodityId(Integer commodityId) {
        if (commodityId == null){
            return new ReturnListObject(ErrInfo.PARAMETER_ERROR);
        }
        try {
            final List<ActivityDTO> activityDTOs = activityService.queryActivityByCommodityId(commodityId);
            List<QueryActivityByCommodityIdDTO> activityDTOS = new ArrayList<>();
            activityDTOs.forEach(activity -> activityDTOS.add(new QueryActivityByCommodityIdDTO(activity)));
            return new ReturnListObject(true, new ArrayList<>(activityDTOS), 0);
        } catch (BizException e) {
            return new ReturnListObject(e);
        }
    }
}
