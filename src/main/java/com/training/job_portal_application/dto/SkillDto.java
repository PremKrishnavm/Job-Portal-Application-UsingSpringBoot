package com.training.job_portal_application.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class SkillDto {
	private long skillId;
	private String skillName;
}
