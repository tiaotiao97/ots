package com.ots.dao;

import com.ots.entity.Order;

import java.util.List;

public interface OrderDao {

    public List<Order> select(Order order);
    public int insert(Order order);
}
