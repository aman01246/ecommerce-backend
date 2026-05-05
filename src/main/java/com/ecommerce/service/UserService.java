package com.ecommerce.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.LoginResponse;
import com.ecommerce.dto.RegisterRequest;
import com.ecommerce.dto.UserDto;
import com.ecommerce.entity.User;
import com.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository repository;
	private final PasswordEncoder encoder;
	
	public String register(RegisterRequest request) {
		
		if(repository.findByEmail(request.getEmail()).isPresent()) {
			throw new RuntimeException("Email already registered");
		}
		
		User user = new User();
		user.setEmail(request.getEmail());
		user.setPassword(encoder.encode(request.getPassword()));
		user.setRole("USER");
		
		repository.save(user);
		
		return "User registered successfully";
	}
	
	public LoginResponse login(User user,String token) {
		
		User user2 = repository.findByEmail(user.getEmail())
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		UserDto dto = new UserDto();
		dto.setEmail(user2.getEmail());
		dto.setRole(user2.getRole());
		
		LoginResponse response = new LoginResponse();
		response.setToken(token);
		response.setUser(dto);
		
		return response;
	}
	
	public User getByEmail(String email) {
		
		User user = repository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));
		
		return user;
	}
	
}
