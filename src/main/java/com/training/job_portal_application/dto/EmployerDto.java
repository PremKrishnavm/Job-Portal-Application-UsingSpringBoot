package com.training.job_portal_application.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Setter
@Getter
public class EmployerDto {
	private long employeeId;
	private String employeeName;
	private String employeeEmail;
	
//	private List<Job> jobs;s
}
