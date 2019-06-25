package com.kljs.rabbitmq.order.model;

import lombok.Data;

import java.sql.Date;


@Data
public class ShopBalance {

    private Long id;

    private Long shopId;

    private Integer balance;

    private Date updatedAt;
}