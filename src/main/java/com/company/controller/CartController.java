package com.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.exception.ProductException;
import com.company.exception.UserException;
import com.company.model.Cart;
import com.company.model.User;
import com.company.request.AddItemRequest;
import com.company.response.ApiResponse;
import com.company.service.CartService;
import com.company.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/cart")
@Tag(name = "Cart Management", description = "find user cart, add item to cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private UserService userService;

	@GetMapping("/")
	@Operation(description = "find cart by user id")
	public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws UserException {

		User user = userService.findUserProfileByJwt(jwt);
		Cart cart = cartService.findUserCart(user.getId());
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}

	@PutMapping("/add")
	@Operation(description = "add item to cart")
	public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddItemRequest req,
			@RequestHeader("Authorization") String jwt) throws UserException, ProductException {

		User user = userService.findUserProfileByJwt(jwt);

		cartService.addCartItem(user.getId(), req);
		
		ApiResponse response = new ApiResponse();
		response.setMessage("Item added to the cart");
		response.setStatus(true);

		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

}
