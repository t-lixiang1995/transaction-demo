package com.kljs.rabbitmq.order.model;

import java.util.Date;
import lombok.Data;

@Data
public class BalanceFlow {

    private Long id;

    private Long shopId;

    private Date createTime;

    private Integer cost;

    private Date updatedAt;

    public BalanceFlow(Long shopId, Date createTime, Integer cost) {
        this.shopId = shopId;
        this.createTime = createTime;
        this.cost = cost;
    }

}