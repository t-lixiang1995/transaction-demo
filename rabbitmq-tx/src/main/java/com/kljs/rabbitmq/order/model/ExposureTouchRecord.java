package com.kljs.rabbitmq.order.model;

import lombok.Data;

import java.sql.Date;

@Data
public class ExposureTouchRecord {

    private Long id;

    private Long shopId;

    private Long userId;

    private Integer cost;

    private Date exposureTime;

    private Long adId;

    private Date updatedAt;

    public ExposureTouchRecord(Long shopId, Long userId, Integer cost, Date exposureTime, Long adId) {
        this.shopId = shopId;
        this.userId = userId;
        this.cost = cost;
        this.exposureTime = exposureTime;
        this.adId = adId;
    }
}