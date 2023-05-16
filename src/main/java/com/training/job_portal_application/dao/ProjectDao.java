package com.training.job_portal_application.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.training.job_portal_application.repository.ProjectRepo;

@Repository
public class ProjectDao {
	
	@Autowired
	private ProjectRepo projectRepo;
}
