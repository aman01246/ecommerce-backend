package com.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecommerce.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<ApiResponse<String>> handleIllegalState(Exception ex) {
		return ResponseEntity.badRequest().body(new ApiResponse<>(false, ex.getMessage(), null));
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ApiResponse<String>> handleRuntime(RuntimeException ex) {

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(false, ex.getMessage(), null));
	}

}
