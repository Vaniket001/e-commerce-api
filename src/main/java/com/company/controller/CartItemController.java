package com.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.exception.CartItemException;
import com.company.exception.ProductException;
import com.company.exception.UserException;
import com.company.model.CartItem;
import com.company.model.User;
import com.company.request.AddItemRequest;
import com.company.response.ApiResponse;
import com.company.service.CartItemService;
import com.company.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/cart_items")
public class CartItemController {

	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private UserService userService;

	@GetMapping("/item/{cartItemId}")
	public ResponseEntity<CartItem> findCartItemById(@PathVariable Long cartItemId,
			@RequestHeader("Authorization") String jwt) throws CartItemException {

		CartItem item = cartItemService.findCartItemById(cartItemId);
		return new ResponseEntity<CartItem>(item, HttpStatus.OK);
	}
	
	@DeleteMapping("/{cartItemId}")
	public ResponseEntity<ApiResponse> addItemToCart(@PathVariable Long cartItemId,
			@RequestHeader("Authorization") String jwt) throws UserException, ProductException, CartItemException {

		User user = userService.findUserProfileByJwt(jwt);
		cartItemService.removeCartItem(user.getId(), cartItemId);
		
		ApiResponse response = new ApiResponse();
		response.setMessage("deleted item from cart");
		response.setStatus(true);

		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

}
