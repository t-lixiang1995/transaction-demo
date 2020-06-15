package com.kljs.rabbitmq.order.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TradeOrderVo {

    private Long id;
    private Long orderId;
    private Integer orderStatus;
    private Integer payType;
    private Integer payStatus;
    private Long shopId;
    private LocalDateTime paidTime;
    private LocalDateTime confirmTime;
    private LocalDateTime finishedTime;
    private Long userId;

}
