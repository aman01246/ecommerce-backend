package com.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.CartItem;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.OrderItem;
import com.ecommerce.entity.User;
import com.ecommerce.entity.UserProject;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.UserProjectRepository;
import com.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final UserRepository userRepo;
	private final CartRepository cartRepo;
	private final OrderRepository orderRepo;
	private final UserProjectRepository userProjectRepo;
	
	
	public String checkout(String email) {
		
		User user = userRepo.findByEmail(email)
				.orElseThrow(()-> new RuntimeException("User not found"));
		
		Cart cart = cartRepo.findByUser(user)
				.orElseThrow(()-> new RuntimeException("Cart empty"));
		
		List<CartItem> items = cart.getItems();
		
		if(items == null || items.isEmpty()) {
			throw new RuntimeException("Cart is empty");
		}
		
		Order order = new Order();
		order.setUser(user);
		
		double total = 0;
		
		for(CartItem item:items) {
			
			OrderItem orderItem = new OrderItem();
			
			orderItem.setProject(item.getProject());
			orderItem.setPrice(item.getProject().getPrice());
			orderItem.setOrder(order);
			
			total += item.getProject().getPrice();
			
			order.getItems().add(orderItem);
			
			UserProject up = new UserProject();
			up.setUser(user);
			up.setProject(item.getProject());
			userProjectRepo.save(up);
		}
		
		order.setTotalPrice(total);
		orderRepo.save(order);
		
		cart.getItems().clear();
		cartRepo.save(cart);
		return "Order placed successfully";
		
	}
	
	
}
