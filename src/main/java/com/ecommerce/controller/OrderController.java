package com.ecommerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.ApiResponse;
import com.ecommerce.service.OrderService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

	private final OrderService service;
	
	@PostMapping("/checkout")
	public ResponseEntity<ApiResponse<String>> checkout() {
		String email = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		System.out.println(
			    SecurityContextHolder.getContext().getAuthentication()
			);
		return ResponseEntity.ok(
	            new ApiResponse<>(true, service.checkout(email), null)
	    );
	}
	
	
}
