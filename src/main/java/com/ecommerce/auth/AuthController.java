package com.ecommerce.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.ApiResponse;
import com.ecommerce.dto.LoginResponse;
import com.ecommerce.dto.RegisterRequest;
import com.ecommerce.entity.User;
import com.ecommerce.security.JwtUtil;
import com.ecommerce.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;
	private final UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody User user) {

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						user.getEmail(), 
						user.getPassword())
				);
		User userEntity = userService.getByEmail(user.getEmail());
		String token = jwtUtil.generateToken(userEntity.getEmail(),userEntity.getRole());
		
		  LoginResponse response = userService.login(user, token);
		
		 return ResponseEntity.ok(
		            new ApiResponse<>(true, "Login successful", response)); 
	}
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<String>> register(@Valid @RequestBody RegisterRequest request) {
		
		 String res = userService.register(request);

		return ResponseEntity.ok(
	            new ApiResponse<>(true, res, null)
			    );
		
	}
	
	
	
}
