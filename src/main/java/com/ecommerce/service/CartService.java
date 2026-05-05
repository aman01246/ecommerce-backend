package com.ecommerce.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.CartItem;
import com.ecommerce.entity.Project;
import com.ecommerce.entity.User;
import com.ecommerce.repository.CartItemRepository;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.ProjectRepository;
import com.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class CartService {

	private final CartRepository cartRepo;
	private final CartItemRepository cartItemRepo;
	private final UserRepository userRepo;
	private final ProjectRepository projectRepo;
	
	private Cart getUserCart(User user) {
		
		return cartRepo.findByUser(user)
				.orElseGet(()-> {
					Cart cart = new Cart();
					cart.setUser(user);
					return cartRepo.save(cart);
				});
		
	}
	
	// add item to cart
	public String addToCart(String email, Long projectId) {
		
		User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
		Project project = projectRepo.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found"));
		
		System.out.println("Email: " + email);
		
		Cart cart = getUserCart(user);
		
		CartItem existingItem = cartItemRepo
		        .findByCartAndProject(cart, project)
		        .orElse(null);

		if (existingItem != null) {
		    // update quantity
		    existingItem.setQuantity(existingItem.getQuantity() + 1);
		    cartItemRepo.save(existingItem);
		} else {
		    // create new item
		    CartItem item = new CartItem();
		    item.setCart(cart);
		    item.setProject(project);
		    item.setQuantity(1);
		    cartItemRepo.save(item);
		}
		
		return "Added to cart";
	}
	
	public List<CartItem> getCart(String email){
		User user = userRepo.findByEmail(email).orElseThrow();
		Cart cart = getUserCart(user);
		
		return cartItemRepo.findByCart(cart);
		
	}
	
	public void removeItem(Long itemId) {
		cartItemRepo.deleteById(itemId);
	}
	
	
	
}
