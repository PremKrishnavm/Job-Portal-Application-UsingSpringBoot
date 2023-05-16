package com.training.job_portal_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.job_portal_application.dto.ApplicantDto;
import com.training.job_portal_application.entity.Applicant;
import com.training.job_portal_application.service.ApplicantService;
import com.training.job_portal_application.util.responseStructure;

@RestController
@RequestMapping("/applicant")
public class ApplicantController {

	@Autowired
	private ApplicantService applicantService;
	
	@PostMapping
	public ResponseEntity<responseStructure<ApplicantDto>> saveApplicant(@RequestBody Applicant applicant){
		return applicantService.saveApplicant(applicant);
	}
	
	@GetMapping
	public ResponseEntity<responseStructure<Applicant>> getApplicant(@RequestParam long applicantId) {
		return applicantService.getApplicant(applicantId);
	}
}
