package com.ecommerce.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterRequest {

	@Email
	private String email;
	
	@Size(min = 4)
	private String password;
	
}
