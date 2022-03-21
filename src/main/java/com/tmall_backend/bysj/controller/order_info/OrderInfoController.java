package com.tmall_backend.bysj.controller.order_info;

import static com.tmall_backend.bysj.common.constants.Constants.SESSION_KEY;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmall_backend.bysj.common.ReturnObject;
import com.tmall_backend.bysj.common.ReturnPageObject;
import com.tmall_backend.bysj.common.constants.ErrInfo;
import com.tmall_backend.bysj.common.exception.BizException;
import com.tmall_backend.bysj.common.page.PageBean;
import com.tmall_backend.bysj.controller.PageBaseDTO;
import com.tmall_backend.bysj.service.commodity.CommodityService;
import com.tmall_backend.bysj.service.order_info.OrderInfoService;
import com.tmall_backend.bysj.service.order_info.dto.OrderInfoDTO;

/**
 * @author LiuWenshuo
 * Created on 2022-03-11
 */
@Controller
@RequestMapping("/api/orderInfo")
public class OrderInfoController {
    @Autowired
    OrderInfoService orderInfoService;
    @Autowired
    CommodityService commodityService;

    @RequestMapping("/queryById")
    @ResponseBody
    public ReturnObject queryById(Integer id) {
        try {
            final OrderInfoDTO orderInfoDTO = orderInfoService.queryOrderInfoById(id);
            return new ReturnObject(true, orderInfoDTO, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }

    @PostMapping("/queryByCustomerNameByPage")
    @ResponseBody
    public ReturnPageObject<OrderInfoDTO> queryOrderInfoByCustomerNameByPage(HttpServletRequest req,
            @RequestBody PageBaseDTO dto) {
        if (Boolean.TRUE.equals(dto.hasNull())) {
            return new ReturnPageObject<>(ErrInfo.PARAMETER_ERROR);
        }
        final Object userName = req.getSession().getAttribute(SESSION_KEY);
        final PageBean<OrderInfoDTO> orderInfoDTOPageBean = orderInfoService
                .queryOrderInfoByCustomerNameByPage((String) userName, dto.getPageNo(), dto.getPageSize());
        return new ReturnPageObject<>(true, orderInfoDTOPageBean, 0);
    }
}
