package com.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.entity.CartItem;
import com.ecommerce.entity.Project;
import com.ecommerce.repository.CartItemRepository;
import com.ecommerce.repository.OrderItemRepository;
import com.ecommerce.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {

	private final ProjectRepository projectRepo;
	private final CartItemRepository cartItemRepo;
	private final OrderItemRepository orderItemRepo;
	
	
	public Project addProject(Project project) {
		return projectRepo.save(project);
	}
	
	public List<Project> getAllProjects(){
		return projectRepo.findAll();
	}
	
	public void updateProject(Long id, Project updated) {

	    Project project = projectRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Project not found"));

	    project.setTitle(updated.getTitle());
	    project.setPrice(updated.getPrice());

	    projectRepo.save(project);
	}
	
	public void deleteProject(Long id) {
		
		  Project project = projectRepo.findById(id)
			        .orElseThrow(() -> new RuntimeException("Project not found"));

			    // ❌ check order items
			    if (orderItemRepo.existsByProject(project)) {
			        throw new RuntimeException("product already purchased");
			    }

			    // ✅ delete cart items
			    List<CartItem> items = cartItemRepo.findByProject(project);
			    cartItemRepo.deleteAll(items);

			    projectRepo.delete(project);
	}
	
}
