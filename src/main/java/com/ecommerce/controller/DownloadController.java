package com.ecommerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.ApiResponse;
import com.ecommerce.service.DownloadService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/download")
@RequiredArgsConstructor
public class DownloadController {

	private final DownloadService service;
	
	@GetMapping("/{projectId}")
	public ResponseEntity<ApiResponse<String>> download(@PathVariable Long projectId) {

		String email = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		
		 return ResponseEntity.ok(
		            new ApiResponse<>(true, "Download link", service.download(email, projectId))
		    );
	
	}
	
	
}
