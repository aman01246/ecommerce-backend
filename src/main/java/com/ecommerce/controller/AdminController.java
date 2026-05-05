package com.ecommerce.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.ApiResponse;
import com.ecommerce.service.DashboardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

	private final DashboardService service;
	
	@GetMapping("/stats")
	public ResponseEntity<ApiResponse<Map<String, Object>>> getStats() {

	    return ResponseEntity.ok(
	        new ApiResponse<>(true, "Stats fetched", service.getStats())
	    );
	}
}
