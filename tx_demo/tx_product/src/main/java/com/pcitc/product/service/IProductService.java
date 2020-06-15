package com.pcitc.product.service;

import org.springframework.amqp.core.Message;

import com.rabbitmq.client.Channel;

import com.pcitc.core.entity.Order;

/**
 * 
 * @author lixiang
 * @createTime 2019年7月24日 上午11:48:27
 * 
 */
public interface IProductService {
	
	public int updateProduct(Order order) throws Exception;
	public void updateProduct4MQ(Order order, Channel channel, Message message) throws Exception;
}
