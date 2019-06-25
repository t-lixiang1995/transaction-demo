package com.kljs.rabbitmq.order.model;

import lombok.Data;

@Data
public class Advertisement {

    private Long id;

    private Long shopId;

    private String name;
}