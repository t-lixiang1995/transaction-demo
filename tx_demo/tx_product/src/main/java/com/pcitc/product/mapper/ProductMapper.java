package com.pcitc.product.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pcitc.core.entity.Order;

/**
 * 
 * @author lixiang
 * @createTime 2019年7月24日 上午11:48:27 
 * 
 */
@Mapper
public interface ProductMapper {
	public int update(Order order);
}
