package com.kljs.rabbitmq.order.dao.db1;

import com.kljs.rabbitmq.order.model.BalanceFlow;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component("balanceFlowMapperDB1")
public interface BalanceFlowMapperDB1 {


    @Insert("INSERT INTO t_balance_flow(shop_id, create_time, cost) VALUES(#{shopId}, #{createTime}, #{cost})")
    int insert(BalanceFlow balanceFlow);

}