package com.pcitc.demo.service.jta;

import com.pcitc.demo.dao.exp.ReduceStockException;
import com.pcitc.demo.model.Order;

public interface OrderService {
	public void createOrder(Order order) throws ReduceStockException;
	
	public Integer createOrderByTxTemplate(Order order);
	
	public Integer createOrderByTxManual(Order order);

}
