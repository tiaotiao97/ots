package com.ots.service;

import com.ots.entity.Order;

import java.util.List;

public interface OrderService {

    public Order createOrder(Order order);
    public List<Order> showOrders(Order order);
    public Order addOrder(Order order);
}
