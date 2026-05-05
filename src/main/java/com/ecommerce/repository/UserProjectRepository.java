package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entity.UserProject;

public interface UserProjectRepository extends JpaRepository<UserProject, Long>{

	boolean existsByUserIdAndProjectId(Long userId,Long projectId);
	
}
