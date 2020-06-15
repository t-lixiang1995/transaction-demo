package com.pcitc.demo.dao.jta;

import com.pcitc.demo.model.Order;

public interface OrderDao {
	public void saveOrder(Order order);
}
