package com.training.job_portal_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.job_portal_application.entity.Resume;
import com.training.job_portal_application.entity.Skill;
import com.training.job_portal_application.service.SkillService;
import com.training.job_portal_application.util.responseStructure;

@RestController
@RequestMapping("/skills")
public class SkillController {
	
	@Autowired
	private SkillService skillService;
	
	@PostMapping
	public ResponseEntity<responseStructure<Resume>> saveSkills
	(@RequestParam long applicantId ,@RequestParam String[] skills){
		return  skillService.saveSkill(applicantId, skills);
	}
	
	@GetMapping
	public ResponseEntity<responseStructure<Skill>> getJobById(@RequestParam String skillName) {
		return skillService.getSkillById(skillName);
	}
}
