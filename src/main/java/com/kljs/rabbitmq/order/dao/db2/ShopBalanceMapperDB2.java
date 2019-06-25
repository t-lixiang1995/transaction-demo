package com.kljs.rabbitmq.order.dao.db2;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "shopBalanceMapperDB2")
public interface ShopBalanceMapperDB2 {

    @Update("UPDATE t_shop_balance SET balance = balance - #{cost} WHERE shop_id =#{shopId}")
    int costFromAd(long shopId, int cost);

}