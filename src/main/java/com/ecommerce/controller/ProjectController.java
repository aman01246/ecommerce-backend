package com.ecommerce.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.ApiResponse;
import com.ecommerce.entity.Project;
import com.ecommerce.service.ProjectService;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

	private final ProjectService service;
	
	@PostMapping
	public ResponseEntity<ApiResponse<Project>> addProject(@RequestBody Project project) {

	    return ResponseEntity.ok(
	            new ApiResponse<>(true, "Project added", service.addProject(project))
	    );
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<Project>>> getAll() {
		 return ResponseEntity.ok(
		            new ApiResponse<>(true, "Projects fetched", service.getAllProjects())
		    );
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<String>> updateProject(
	        @PathVariable Long id,
	        @RequestBody Project updatedProject) {

	    service.updateProject(id, updatedProject);

	    return ResponseEntity.ok(
	        new ApiResponse<>(true, "Project updated", null)
	    );
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
		
		service.deleteProject(id);
		
		return ResponseEntity.ok(
	            new ApiResponse<>(true, "Deleted Successfully", null)
	    );
	}
	
	
}

