package com.kljs.rabbitmq.order.model;

import lombok.Data;

@Data
public class AdvertisementMessage {

    private long adId;
    private long shopId;
    private long userId;
    private int cost;
    private long timestamp;

    public AdvertisementMessage() {

    }

    public AdvertisementMessage(long adId, long shopId, long userId, int cost, long timestamp) {
        this.adId = adId;
        this.shopId = shopId;
        this.userId = userId;
        this.cost = cost;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "AdvertisementMessage{" +
                "adId=" + adId +
                ", shopId=" + shopId +
                ", userId=" + userId +
                ", cost=" + cost +
                ", timestamp=" + timestamp +
                '}';
    }
}
