package com.company.service;

import java.util.List;

import com.company.exception.OrderException;
import com.company.model.Address;
import com.company.model.Order;
import com.company.model.User;

public interface OrderService {
	
	public Order CreateOrder(User user, Address shippingAdress);
	
	public Order findOrderById(Long orderId) throws OrderException;
	
	public List<Order> usersOrderHistory(Long userId);
	
	public Order placedOrder(Long orderId) throws OrderException; 
	
	public Order confirmedOrder(Long orderId) throws OrderException;
	
	public Order shippedOrder(Long orderId) throws OrderException; 
	
	public Order deliveredOrder(Long orderId) throws OrderException;
	
	public Order cancledOrder(Long orderId) throws OrderException; 
	
	public List<Order> getAllOrders();
	
	public void deleteOrder(Long orderId) throws OrderException; 
}
