package com.tmall_backend.bysj.controller.back_order_info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmall_backend.bysj.common.ReturnObject;
import com.tmall_backend.bysj.common.exception.BizException;
import com.tmall_backend.bysj.controller.back_order_info.dto.InsertBackOrderInfoDTO;
import com.tmall_backend.bysj.service.back_order_info.BackOrderInfoService;
import com.tmall_backend.bysj.service.commodity.CommodityService;
import com.tmall_backend.bysj.service.order_info.OrderInfoService;

/**
 * @author LiuWenshuo
 * Created on 2022-03-12
 */
@Controller
@RequestMapping("/api/backOrderInfo")
public class BackOrderInfoController {

    @Autowired
    BackOrderInfoService backOrderInfoService;
    @Autowired
    OrderInfoService orderInfoService;
    @Autowired
    CommodityService commodityService;

    @RequestMapping("/queryById")
    @ResponseBody
    public ReturnObject queryBackOrderInfoById(Integer id) {
        try {
            return new ReturnObject(true,
                    backOrderInfoService.queryBackOrderInfoById(id), 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }

    @RequestMapping("/insert")
    @ResponseBody
    public ReturnObject insertBackOrderInfo(@RequestBody InsertBackOrderInfoDTO dto) {
        try {
            // 将退货信息更新到订单信息
            orderInfoService.updateCommodityStatusInOrderInfo(dto.getOrderInfoId(),
                    dto.getCommodityId(), 3);
            // 将货物库存还原
            commodityService.increaseOrDecreaseInventory(dto.getCommodityId(), dto.getBackNum());
            // 将退货信息入库
            final Integer result = backOrderInfoService.insertBackOrderInfo(
                    dto.getOrderInfoId(), dto.getCommodityId(), dto.getBackNum(), dto.getPrice());
            return new ReturnObject(true, result, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }
}
