package com.training.job_portal_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.job_portal_application.entity.Employer;
import com.training.job_portal_application.service.EmployerService;
import com.training.job_portal_application.util.responseStructure;



@RestController
@RequestMapping("/employer")
public class EmployerController {
	
	@Autowired
	private EmployerService employerService;
	
	@PostMapping
	public ResponseEntity<responseStructure<Employer>> addEmployer(@RequestBody Employer employer){
		return employerService.addEmployer(employer);
	}
	
	@GetMapping
	public ResponseEntity<responseStructure<Employer>> getEmployerById(@RequestParam long employerId) {
		return employerService.getEmployerById(employerId);
	}
}
