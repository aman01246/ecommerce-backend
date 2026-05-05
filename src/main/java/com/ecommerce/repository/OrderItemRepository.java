package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecommerce.entity.OrderItem;
import com.ecommerce.entity.Project;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	boolean existsByProject(Project project);

	@Query("SELECT SUM(oi.price) FROM OrderItem oi")
	Double getTotalRevenue();

}
