package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.exception.OrderException;
import com.company.exception.UserException;
import com.company.model.Address;
import com.company.model.Order;
import com.company.model.User;
import com.company.service.OrderService;
import com.company.service.UserService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@PostMapping("/")
	public ResponseEntity<Order> createOrder(@RequestBody Address shippingAddress,
			@RequestHeader("Authorization") String jwt) throws UserException {

		User user = userService.findUserProfileByJwt(jwt);

		Order order = orderService.CreateOrder(user, shippingAddress);
		System.out.println("order :: " + order);

		return new ResponseEntity<Order>(order, HttpStatus.CREATED);
	}

	@GetMapping("/user")
	public ResponseEntity<List<Order>> userOrderHostory(@RequestHeader("Authorization") String jwt)
			throws UserException {

		User user = userService.findUserProfileByJwt(jwt);

		List<Order> orders = orderService.usersOrderHistory(user.getId());

		return new ResponseEntity<List<Order>>(orders, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Order> findOrderById(@PathVariable("id") Long orderId,
			@RequestHeader("Authorization") String jwt) throws OrderException, UserException {

		User user = userService.findUserProfileByJwt(jwt);

		Order order = orderService.findOrderById(orderId);

		return new ResponseEntity<Order>(order, HttpStatus.ACCEPTED);
	}

}
