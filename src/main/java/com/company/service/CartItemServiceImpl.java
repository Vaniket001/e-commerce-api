package com.company.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.exception.CartItemException;
import com.company.exception.UserException;
import com.company.model.Cart;
import com.company.model.CartItem;
import com.company.model.Product;
import com.company.model.User;
import com.company.repository.CartItemRepository;
import com.company.repository.CartRepository;

@Service
public class CartItemServiceImpl implements CartItemService {
	
	private CartItemRepository cartItemRepository;
	
	private UserService userService;
	
	private CartRepository cartRepository;
	
	public CartItemServiceImpl(CartItemRepository cartItemRepository,UserService userService,CartRepository cartRepository) {
		this.cartItemRepository = cartItemRepository;
		this.userService = userService;
		this.cartRepository = cartRepository;
	}

	@Override
	public CartItem createCartItem(CartItem cartItem) {
		cartItem.setQuantity(1);
		cartItem.setPrice(cartItem.getProduct().getPrice()* cartItem.getQuantity());
		cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());
		
		CartItem createdCartItem = cartItemRepository.save(cartItem);
		return createdCartItem;
	}

	@Override
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
		
		CartItem item = findCartItemById(id);
		User user= userService.findUserById(item.getUserId());
		
		if(user.getId().equals(userId)) {
			item.setQuantity(item.getQuantity());
			item.setPrice(item.getQuantity()*item.getProduct().getPrice());
			item.setDiscountedPrice(item.getProduct().getDiscountedPrice()*item.getQuantity());
		}
		return cartItemRepository.save(item);
	}

	@Override
	public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
		
		CartItem cartItem = cartItemRepository.isCartItemExist(cart, product, size, userId);
		
		return cartItem;
	}

	@Override
	@Transactional
	public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
		
		CartItem cartItem = findCartItemById(cartItemId);
		if (cartItem == null) {
			throw new CartItemException("CartItem not found with id - " + cartItemId);
		}
		
		User user = userService.findUserById(cartItem.getUserId());
		
		User reqUser = userService.findUserById(userId);
		
		if(user.getId().equals(reqUser.getId())) {
			cartItemRepository.deleteById(cartItemId);
		}else {
			throw new UserException("You can't remove other's user's item");
		}

	}

	@Override
	public CartItem findCartItemById(Long cartItemId) throws CartItemException {
		
		Optional<CartItem> opt= cartItemRepository.findById(cartItemId);
		
		if(opt.isPresent()) {
			return opt.get();
		}
	  throw new CartItemException("Cart Item not found with id: "+ cartItemId);
	}

}
