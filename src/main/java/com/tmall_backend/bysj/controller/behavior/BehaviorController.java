package com.tmall_backend.bysj.controller.behavior;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.tmall_backend.bysj.common.ReturnObject;
import com.tmall_backend.bysj.common.constants.ErrInfo;
import com.tmall_backend.bysj.common.exception.BizException;
import com.tmall_backend.bysj.controller.behavior.dto.BuyDTO;
import com.tmall_backend.bysj.controller.behavior.dto.EnsureOrderDTO;
import com.tmall_backend.bysj.controller.behavior.dto.UpdateCommodityToTrolleyDTO;
import com.tmall_backend.bysj.service.behavior.BehaviorService;

/**
 * @author LiuWenshuo
 * Created on 2022-03-19
 */
@Controller
@RequestMapping("/api/behavior")
public class BehaviorController {

    @Autowired
    BehaviorService behaviorService;

    @PostMapping("/increaseOrDecreaseCommodityToTrolley")
    @ResponseBody
    @Transactional(rollbackFor=Exception.class)
    public ReturnObject increaseOrDecreaseCommodityToTrolley(HttpServletRequest req, @RequestBody
            UpdateCommodityToTrolleyDTO dto) {
        if (dto.getCommodityId() == null || dto.getNumber() == null) {
            return new ReturnObject(ErrInfo.PARAMETER_ERROR);
        }
        try {
            final Integer result = behaviorService.increaseOrDecreaseCommodityToTrolley(req, dto.getCommodityId(), dto.getNumber());
            return new ReturnObject(true, result, 0);
        }catch (BizException e) {
            return new ReturnObject(e);
        }
    }

    @PostMapping("/deleteCommodityFromTrolley")
    @ResponseBody
    @Transactional(rollbackFor=Exception.class)
    public ReturnObject deleteCommodityFromTrolley(HttpServletRequest req, @RequestBody UpdateCommodityToTrolleyDTO dto) {
        if (dto.getCommodityId() == null) {
            return new ReturnObject(ErrInfo.PARAMETER_ERROR);
        }
        try {
            final Integer result = behaviorService.deleteCommodityFromTrolley(req, dto.getCommodityId());
            return new ReturnObject(true, result, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }

    @PostMapping("/buy")
    @ResponseBody
    @Transactional(rollbackFor=Exception.class)
    public ReturnObject buy(HttpServletRequest req, @RequestBody BuyDTO dto) {
        if (dto.getOrderPrice() == null) {
            return new ReturnObject(ErrInfo.PARAMETER_ERROR);
        }
        try {
            final Map<String, Object> detail = JSON.parseObject(dto.getDetail());
            final Integer newOrderInfoId = behaviorService.buy(req, detail, dto.getOrderPrice());
            return new ReturnObject(true, newOrderInfoId, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnObject(ErrInfo.PARAMETER_ERROR);
        }
    }

    @GetMapping("/pay")
    @ResponseBody
    public ReturnObject pay(Integer orderInfoId) {
        if (orderInfoId == null) {
            return new ReturnObject(ErrInfo.PARAMETER_ERROR);
        }
        try {
            final Integer result = behaviorService.updateStatusInOrderInfo(orderInfoId, 2);
            return new ReturnObject(true, result, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }

    @RequestMapping("/returnCommodity")
    @ResponseBody
    @Transactional(rollbackFor=Exception.class)
    public ReturnObject returnCommodity(@RequestBody EnsureOrderDTO dto) {
        if (dto.getCommodityId() == null || dto.getOrderInfoId() == null) {
            return new ReturnObject(ErrInfo.PARAMETER_ERROR);
        }
        try {
            final Integer result = behaviorService.returnCommodity(dto.getOrderInfoId(), dto.getCommodityId());
            return new ReturnObject(true, result, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }

    @PostMapping("/ensureOrder")
    @ResponseBody
    public ReturnObject ensureOrder(@RequestBody EnsureOrderDTO dto) {
        if (dto.getCommodityId() == null || dto.getOrderInfoId() == null) {
            return new ReturnObject(ErrInfo.PARAMETER_ERROR);
        }
        try {
            final Integer result = behaviorService.updateStatusInOrderInfoForCom(
                    dto.getOrderInfoId(), dto.getCommodityId(), 3);
            return new ReturnObject(true, result, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }
}
