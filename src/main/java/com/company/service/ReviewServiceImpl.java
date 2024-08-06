package com.company.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.company.exception.ProductException;
import com.company.model.Product;
import com.company.model.Review;
import com.company.model.User;
import com.company.repository.ProductRepository;
import com.company.repository.ReviewRepository;
import com.company.request.ReviewRequest;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	private ReviewRepository reviewRepository;
	
	private ProductService productService;
	
	private ProductRepository productRepository;
	
	public ReviewServiceImpl(ReviewRepository reviewRepository,ProductService productService, ProductRepository productRepository) {
		this.reviewRepository = reviewRepository;
		this.productService = productService;
		this.productRepository = productRepository;
	}

	@Override
	public Review createReview(ReviewRequest req, User user) throws ProductException {
		
		Product product = productService.findProductById(req.getProductId());
		
		Review review = new Review();
		review.setUser(user);
		review.setProduct(product);
		review.setReview(req.getReview());
		review.setCreatedAt(LocalDateTime.now());
		
		return reviewRepository.save(review);
	}

	@Override
	public List<Review> getAllReviews(Long productId) {
		
		return reviewRepository.getAllProductsReview(productId);
	}

}
