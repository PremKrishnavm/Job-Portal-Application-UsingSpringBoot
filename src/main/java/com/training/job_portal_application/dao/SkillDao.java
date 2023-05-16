package com.training.job_portal_application.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.training.job_portal_application.entity.Skill;
import com.training.job_portal_application.repository.SkillRepo;

@Repository
public class SkillDao {
	
	@Autowired
	private SkillRepo skillRepo;
	
	public Skill saveSkill(Skill skill) {
		return skillRepo.save(skill);
	}
	public Skill getSkillByName(String skillName) {
		Optional<Skill> optional = skillRepo.getSkillByName(skillName);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
	}
}
