package com.training.job_portal_application.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class JobDto {	
	private long jobId;
	private String jobTitle;
	private String jobDiscription;
	private String company;
	private double salary;
}
