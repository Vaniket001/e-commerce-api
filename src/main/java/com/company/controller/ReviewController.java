package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.exception.ProductException;
import com.company.exception.UserException;
import com.company.model.Review;
import com.company.model.User;
import com.company.request.ReviewRequest;
import com.company.service.ReviewService;
import com.company.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("api/reviews")
public class ReviewController {

	@Autowired
	private UserService userService;

	@Autowired
	private ReviewService reviewService;

	@PostMapping("/create")
	public ResponseEntity<Review> createReview(@RequestBody ReviewRequest req,
			@RequestHeader("Authorization") String jwt) throws UserException, ProductException {

		User user = userService.findUserProfileByJwt(jwt);

		Review review = reviewService.createReview(req, user);

		return new ResponseEntity<Review>(review, HttpStatus.CREATED);
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Review>> getProductsReview(@PathVariable Long productId)
			throws UserException, ProductException {

		List<Review> reviews = reviewService.getAllReviews(productId);

		return new ResponseEntity<List<Review>>(reviews, HttpStatus.ACCEPTED);
	}

}
