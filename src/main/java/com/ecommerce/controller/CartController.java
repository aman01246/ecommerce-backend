package com.ecommerce.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.ApiResponse;
import com.ecommerce.entity.CartItem;
import com.ecommerce.service.CartService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

	private final CartService service;
	
	@PostMapping("/add/{projectId}")
	public  ResponseEntity<ApiResponse<String>> addToCart(@PathVariable Long projectId, HttpServletRequest request){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		
		return ResponseEntity.ok(
	            new ApiResponse<>(true, service.addToCart(email, projectId), null)
	    );
	}
	
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<CartItem>>> getCart(HttpServletRequest request) {
		
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 String email = auth.getName();
		
		 return ResponseEntity.ok(
		            new ApiResponse<>(true, "Cart fetched", service.getCart(email))
		    );
	}
	
	@DeleteMapping("/{id}")
	public void remove(@PathVariable Long id) {
		service.removeItem(id);
	}
	
	
}
