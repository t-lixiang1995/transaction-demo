package com.kljs.rabbitmq.order.dao.db1;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Mapper
@Component("shopBalanceMapperDB1")
public interface ShopBalanceMapperDB1 {

    @Update("UPDATE t_shop_balance SET balance = balance - #{cost} WHERE shop_id =#{shopId}")
    int costFromAd(long shopId, int cost);

}