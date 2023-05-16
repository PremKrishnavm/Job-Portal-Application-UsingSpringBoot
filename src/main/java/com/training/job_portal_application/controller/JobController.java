package com.training.job_portal_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.job_portal_application.dto.JobDto;
import com.training.job_portal_application.entity.Job;
import com.training.job_portal_application.service.JobService;
import com.training.job_portal_application.util.responseStructure;


@RestController
@RequestMapping("/job")
public class JobController {
	
	@Autowired
	private JobService jobService;
	
	@PostMapping
	public ResponseEntity<responseStructure<Job>> addJob(@RequestBody JobDto jobDto, @RequestParam long employerId){
		return jobService.addJob(jobDto, employerId);
	}
	
	@GetMapping
	public ResponseEntity<responseStructure<Job>> getJobById(@RequestParam long jobId) {
		return jobService.getJobById(jobId);
	}
}
