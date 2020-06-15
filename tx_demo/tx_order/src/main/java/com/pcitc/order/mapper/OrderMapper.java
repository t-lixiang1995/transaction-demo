package com.pcitc.order.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pcitc.core.entity.Order;

/**
 * 
 * @author lixiang
 * @createTime 2019年7月24日 上午11:48:27 
 * 
 */
@Mapper
public interface OrderMapper {
	public int insert(Order order);
	public int delete(int orderId);
	public int update(int orderId);
}
