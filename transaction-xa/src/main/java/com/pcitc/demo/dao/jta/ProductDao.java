package com.pcitc.demo.dao.jta;

import com.pcitc.demo.dao.exp.ReduceStockException;

public interface ProductDao {
	public int reduceStock(Integer productId, Integer amount) throws ReduceStockException;
}
