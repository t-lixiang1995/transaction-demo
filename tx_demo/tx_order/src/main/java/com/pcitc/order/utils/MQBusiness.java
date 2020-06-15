package com.pcitc.order.utils;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pcitc.order.service.IOrderService;
import com.rabbitmq.client.Channel;

/**
 * 
 * @author lixiang
 * @createTime 2019年12月11日 下午4:36:38
 * 
 */
@Component
public class MQBusiness {
	
	@Autowired
	private IOrderService orderService;
	
	/**
	 * 处理业务处理失败，回滚业务
	 */
	@RabbitListener(queues=MQProperties.QUEUE_NAME_TX_RETURN_FAILURE)
	public void process1(String orderId, Channel channel, Message message) throws Exception {
		try {
			orderService.shoppingRollback(Integer.valueOf(orderId));
		} catch (Exception e) {
			channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
			e.printStackTrace();
		}
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}
	/**
	 * 处理业务处理成功，修改订单状态
	 */
	@RabbitListener(queues=MQProperties.QUEUE_NAME_TX_RETURN_SUCCESS)
	public void process2(String orderId, Channel channel, Message message) throws Exception {
		try {
			orderService.shoppingCommit(Integer.valueOf(orderId));
		} catch (Exception e) {
			channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
			e.printStackTrace();
		}
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}
}
