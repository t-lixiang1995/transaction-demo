package com.kljs.rabbitmq.order.dao.db1;

import com.kljs.rabbitmq.order.model.Advertisement;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "advertisementMapperDB1")
public interface AdvertisementMapperDB1 {

    @Select("SELECT * FROM `t_advertisement` WHERE id >= (SELECT floor(RAND() * (SELECT MAX(id) FROM `t_advertisement`)))  ORDER BY id LIMIT 1")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "shopId", column = "shop_id"),
            @Result(property = "name", column = "name"),
    })
    Advertisement getRankOne();

    @Insert("INSERT INTO t_advertisement(id, shop_id, name) VALUES(#{id}, #{shopId}, #{name})")
    int insert(Advertisement advertisement);



}