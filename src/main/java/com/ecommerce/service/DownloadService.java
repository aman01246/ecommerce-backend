package com.ecommerce.service;

import org.springframework.stereotype.Service;

import com.ecommerce.entity.Project;
import com.ecommerce.entity.User;
import com.ecommerce.repository.ProjectRepository;
import com.ecommerce.repository.UserProjectRepository;
import com.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DownloadService {

	private final UserRepository userRepo;
	private final ProjectRepository projectRepo;
	private final UserProjectRepository userProjectRepo;
	
	public String download(String email, Long projectId) {
		User user = userRepo.findByEmail(email)
				.orElseThrow(()->new RuntimeException("User not found"));
		
		Project project = projectRepo.findById(projectId)
				.orElseThrow(()-> new RuntimeException("Project not found"));
		
		boolean hasAccess = userProjectRepo
				.existsByUserIdAndProjectId(user.getId(), projectId);
		
		if(!hasAccess) {
			throw new RuntimeException("You have not purchase this project");
		}
		
		return project.getFileUrl();
		
	}
	
}
