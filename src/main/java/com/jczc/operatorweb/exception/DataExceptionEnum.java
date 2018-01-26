package com.jczc.operatorweb.exception;

/**
 * Created with IntelliJ IDEA
 * User : jono
 * Date : 2017/11/1
 */
public enum DataExceptionEnum {
    PARAMS_ERROR                    ("20001","参数错误"),
    MANUFACTURER_IS_NOT_EXIST       ("20002","厂商不存在"),
    USER_IS_NOT_EXIST               ("20003","用户不存在"),
    PASSWORD_IS_ERROR               ("20004","密码错误"),
    USER_IS_EXIST                   ("20005","该用户已注册"),
    BALANCE                         ("20006","余额不足"),
    PLATE_NO_IS_EXIST               ("20007","车牌号已存在"),
    PRODUCT_IS_NOT_EXIST            ("20008","产品不存在"),
    PILE_IS_NOW_EXIST               ("20009","充电桩不存在"),
    NOT_SET_PRICE                   ("20010","未设置价格"),
    GROUP_IS_NOT_EXIST              ("20011","充电站不存在"),
    NO_RESOURCE                     ("20012","当前没有资源"),
    NO_GROUPS                       ("20013","当前没有充电站"),
    ACCESS_TOKEN_EEOR               ("20014","用户授权码错误"),
    ALREADY_PAYED					("20015","重复为已支付账单付费"),


    SERVER_INNER_ERROR              ("00000","服务器内部错误");
    public String value;
    public String description;

    DataExceptionEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }
}
