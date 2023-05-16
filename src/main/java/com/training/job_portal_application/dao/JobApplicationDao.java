package com.training.job_portal_application.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.training.job_portal_application.repository.JobApplicationRepo;

@Repository
public class JobApplicationDao {
	
	@Autowired
	private JobApplicationRepo jobApplicationRepo;
}
