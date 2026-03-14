package com.abes.dao;

import com.abes.entity.Order;
import java.util.List;

public interface OrderDao {
    public boolean addOrder(Order order, int custId);
    public Order getOrder(int orderId);
    public List<Order> getOrders(String custName);
}