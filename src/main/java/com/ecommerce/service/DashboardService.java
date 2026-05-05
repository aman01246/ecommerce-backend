package com.ecommerce.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ecommerce.repository.OrderItemRepository;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final OrderRepository orderRepo;
    private final OrderItemRepository orderItemRepo;
    private final ProjectRepository projectRepo;

    public Map<String, Object> getStats() {

        long totalOrders = orderRepo.count();
        long totalProducts = projectRepo.count();

        Double revenue = orderItemRepo.getTotalRevenue();
        if (revenue == null) revenue = 0.0;

        Map<String, Object> stats = new HashMap<>();
        stats.put("orders", totalOrders);
        stats.put("products", totalProducts);
        stats.put("revenue", revenue);

        return stats;
    }
	
}
