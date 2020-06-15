package com.kljs.rabbitmq.order.dao.db2;

import com.kljs.rabbitmq.order.model.ExposureTouchRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "exposureTouchRecordMapperDB2")
public interface ExposureTouchRecordMapperDB2 {

    @Insert("INSERT INTO t_exposure_touch_record(shop_id, user_id, cost, exposure_time, ad_id) VALUES(#{shopId}, #{userId}, #{cost}, #{exposureTime}, #{adId})")
    int insert(ExposureTouchRecord exposureTouchRecord);

}