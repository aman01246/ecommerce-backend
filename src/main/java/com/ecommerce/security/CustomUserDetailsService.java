package com.ecommerce.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.User;
import com.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = repository.findByEmail(email)
				.orElseThrow(()-> new UsernameNotFoundException("User not found"));
		
		  return org.springframework.security.core.userdetails.User
	                .withUsername(user.getEmail())
	                .password(user.getPassword())
	                .roles(user.getRole())
	                .build();
	}

}
