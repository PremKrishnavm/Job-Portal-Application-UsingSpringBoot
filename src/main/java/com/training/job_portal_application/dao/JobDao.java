package com.training.job_portal_application.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.training.job_portal_application.entity.Job;
import com.training.job_portal_application.repository.JobRepo;


@Repository
public class JobDao {
	
	@Autowired
	private JobRepo jobRepo;
	
	public Job addJob(Job job) {
		return jobRepo.save(job);
	}
	
	public Job getJobById(long jobId) {
		Optional<Job> optional = jobRepo.findById(jobId);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
	}
}
