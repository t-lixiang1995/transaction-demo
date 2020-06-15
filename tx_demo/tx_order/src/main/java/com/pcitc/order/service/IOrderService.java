package com.pcitc.order.service;

import com.pcitc.core.entity.Order;

/**
 * 
 * @author lixiang
 * @createTime 2019年7月24日 上午11:48:27
 * 
 */
public interface IOrderService {

	public int shopping(Order order) throws Exception;

	public int shopping4MQ(Order order) throws Exception;
	
	public void shoppingRollback(int orderId) throws Exception;
	
	public void shoppingCommit(int orderId) throws Exception;
}
