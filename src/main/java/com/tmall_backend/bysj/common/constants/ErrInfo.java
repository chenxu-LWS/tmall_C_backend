package com.tmall_backend.bysj.common.constants;

import lombok.Getter;

/**
 * @author LiuWenshuo
 * Created on 2022-02-07
 */
@Getter
public enum ErrInfo {
    // 登录相关
    LOGIN_ERR_NAME_NOT_EXISTS(1001, "用户名不存在或密码错误，请重试"),
    REGISTER_ERR_NAME_EXISTS(1002, "该用户名已被占用"),
    OLD_PASSWD_ERROR(1003, "旧密码不正确"),

    // 品类相关
    PARENT_CATEGORY_NOT_EXISTS(1003, "父品类ID不存在"),
    INSERT_CATEGORY_NAME_EXISTS(1004, "品类名已存在"),
    DELETE_CATEGORY_FAILED(1005, "删除失败，请先删除其所有子品类"),
    CATEGORY_ID_NOT_EXISTS(1006, "当前品类ID不存在"),

    // 品牌相关
    BRAND_ID_NOT_EXISTS(1007, "当前品牌ID不存在"),
    INSERT_BRAND_NAME_EXISTS(1008, "待新增的品牌名已存在"),

    // 商品相关
    COMMODITY_ID_NOT_EXISTS(1009, "当前商品ID不存在"),
    COMMODITY_ALREADY_ON_SALE(1010, "商品已经上架了"),
    COMMODITY_STATUS_ERROR(1011, "请先上架商品或商品已经被下架"),
    COMMODITY_PROP_FORMAT_ERROR(1012, "商品属性的json格式错误,需要是String-String的map形式"),

    // 订单相关
    ORDERINFO_DETAIL_FORMAT_ERROR(1013, "订单详情格式错误,请检查"),
    ORDERINFO_ID_NOT_EXISTS(1014, "订单ID不存在"),
    ORDERINFO_DETAIL_INVENTORY_INFINITY(1015, "商品库存不足"),
    ORDERINFO_DETAIL_CONTAINS_COMM_NOT_EXISTS(1016, "订单中的商品ID不存在"),

    // 退货单相关
    BACKORDERINFO_ID_NOT_EXISTS(1016, "退货单ID不存在"),

    // 购物车相关
    GET_LOGIN_USER_ERROR(1017, "尚未登录，没有获取到用户名"),

    // 行为相关
    DECREASE_FROM_TROLLEY(1018, "购物车中不存在该商品，无法进行扣减"),
    DELETE_FROM_TROLLEY(1019, "购物车中不存在该商品，无法删除"),
    BUY_ERROR_INVENTORY_NOT_AVAILABLE(1020, "库存不足，下单失败"),
    BUY_ERROR_COMMODITY_STATUS_NOT_ONSALE(1021, "商品不是上架状态，下单失败"),
    RETURN_COM_ERROR_ALREADY_RETURN(1022, "商品已经退款了，不能重复退款"),

    // 通用错误码
    PARAMETER_ERROR(10000, "参数不合法"),
    PARAMETER_ERROR_CANNOT_CAST_TO_JSON(10001, "参数无法转换为json,请检查"),
    OSS_ERROR(10002, "oss对象存储出错");

    Integer code;
    String message;

    ErrInfo(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
