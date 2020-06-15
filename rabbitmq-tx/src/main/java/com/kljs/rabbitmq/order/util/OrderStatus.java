package com.kljs.rabbitmq.order.util;

public enum OrderStatus {

    STATUS_0(0, "下单"),
    STATUS_1(1, "接单"),
    STATUS_2(2, "配送");

    private int value;
    private String desc;

    private OrderStatus(int value, String desc) {
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
