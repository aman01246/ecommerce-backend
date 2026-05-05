package com.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.CartItem;
import com.ecommerce.entity.Project;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	List<CartItem> findByProject(Project project);
	List<CartItem> findByCart(Cart cart);
	Optional<CartItem> findByCartAndProject(Cart cart, Project project);
	void deleteByProject(Project project);
	
}
