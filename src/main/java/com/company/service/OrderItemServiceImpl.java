package com.company.service;

import org.springframework.stereotype.Service;

import com.company.model.OrderItem;
import com.company.repository.OrderItemRepository;

@Service
public class OrderItemServiceImpl implements OrderItemService {

	private OrderItemRepository orderItemRepository;
	
	public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
		this.orderItemRepository= orderItemRepository;
	}
	
	@Override
	public OrderItem createOrderItem(OrderItem orderItem) {
		
		return orderItemRepository.save(orderItem);
	}

}
