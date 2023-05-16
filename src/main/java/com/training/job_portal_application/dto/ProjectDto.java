package com.training.job_portal_application.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class ProjectDto {
	private long projectId;
	private String projectTitle;
	private String projectDescription;
	private String projectSiteURL;
}
