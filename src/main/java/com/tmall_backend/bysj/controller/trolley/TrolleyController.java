package com.tmall_backend.bysj.controller.trolley;

import static com.tmall_backend.bysj.common.constants.Constants.SESSION_KEY;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmall_backend.bysj.common.ReturnObject;
import com.tmall_backend.bysj.common.constants.ErrInfo;
import com.tmall_backend.bysj.common.exception.BizException;
import com.tmall_backend.bysj.service.trolley.TrolleyService;
import com.tmall_backend.bysj.service.trolley.dto.TrolleyDTO;

/**
 * @author LiuWenshuo
 * Created on 2022-03-19
 */
@RequestMapping("/api/trolley")
@Controller
public class TrolleyController {
    @Autowired
    TrolleyService trolleyService;

    @ResponseBody
    @RequestMapping("/queryTrolley")
    public ReturnObject queryTrolley(HttpServletRequest req) {
        final Object custName = req.getSession().getAttribute(SESSION_KEY);
        if (custName == null) {
            return new ReturnObject(ErrInfo.GET_LOGIN_USER_ERROR);
        }
        try {
            final TrolleyDTO trolleyDTO =
                    trolleyService.queryTrolleyByCustomerName((String) custName);
            return new ReturnObject(true, trolleyDTO, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }
}
