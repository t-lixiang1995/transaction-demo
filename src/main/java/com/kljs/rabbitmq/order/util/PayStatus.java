package com.kljs.rabbitmq.order.util;

public enum PayStatus {

    STATUS_0(0, "待支付"),
    STATUS_1(1, "已支付"),
    STATUS_2(2, "退款中"),
    STATUS_3(3, "已退款");

    private int value;
    private String desc;

    private PayStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
