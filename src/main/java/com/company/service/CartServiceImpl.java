package com.company.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.exception.ProductException;
import com.company.model.Cart;
import com.company.model.CartItem;
import com.company.model.Product;
import com.company.model.User;
import com.company.repository.CartRepository;
import com.company.request.AddItemRequest;

@Service
public class CartServiceImpl implements CartService {

	private CartRepository cartRepository;
	
	private CartItemService cartItemService;
	
	private ProductService productService;
	
	public CartServiceImpl(CartRepository cartRepository, CartItemService cartItemService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
        this.productService = productService;
	}
	@Override
	public Cart createCart(User user) {
		
		Cart cart = new Cart();
		cart.setUser(user);
		return cartRepository.save(cart);
	}

	@Override
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
		
		Cart cart = cartRepository.findByUserId(userId);
		Product product = productService.findProductById(req.getProductId());
		
		CartItem isPresent = cartItemService.isCartItemExist(cart, product, req.getSize(), userId);
		
		if(isPresent == null) {
			CartItem cartItem = new CartItem();
			cartItem.setProduct(product);
			cartItem.setCart(cart);
			cartItem.setQuantity(req.getQuantity());
			cartItem.setUserId(userId);
			
			int price = req.getQuantity()*product.getDiscountedPrice();
			cartItem.setPrice(price);
			cartItem.setSize(req.getSize());
			CartItem createdCartItem = cartItemService.createCartItem(cartItem);
			cart.getCartItems().add(createdCartItem);
		}
		
		
		return "Item Added To Cart";
	}

	@Transactional
	@Override
	public Cart findUserCart(Long userId) {
		
		Cart cart = cartRepository.findByUserId(userId);
		int totalPrice = 0;
		int totalDiscountedPrice = 0;
		int totalItem = 0;
		
		for(CartItem cartItem : cart.getCartItems()) {
			totalPrice += cartItem.getPrice();
			totalDiscountedPrice += cartItem.getDiscountedPrice();
			totalItem += cartItem.getQuantity();
		}
		
		cart.setTotalPrice(totalPrice);
		cart.setTotalDiscountedPrice(totalDiscountedPrice);
		cart.setTotalItem(totalItem);
		cart.setDiscount(totalPrice-totalDiscountedPrice);
		
		return cartRepository.save(cart);
	}
	



}
