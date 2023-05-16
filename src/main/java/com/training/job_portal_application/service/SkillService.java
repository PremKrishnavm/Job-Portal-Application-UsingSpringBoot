package com.training.job_portal_application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.training.job_portal_application.dao.ApplicantDao;
import com.training.job_portal_application.dao.ResumeDao;
import com.training.job_portal_application.dao.SkillDao;
import com.training.job_portal_application.dto.SkillDto;
import com.training.job_portal_application.entity.Applicant;
import com.training.job_portal_application.entity.Resume;
import com.training.job_portal_application.entity.Skill;
import com.training.job_portal_application.exception.ApllicantNotFoundByIdException;
import com.training.job_portal_application.exception.ResumeNotFoundIdException;
import com.training.job_portal_application.exception.SkillNotFoundByIdException;
import com.training.job_portal_application.util.responseStructure;

@Service
public class SkillService {

	@Autowired
	private SkillDao skillDao;
	@Autowired
	private ApplicantDao applicantDao;
	@Autowired
	private ResumeDao resumeDao;
	@Autowired
	private SkillDto skillDto;

	public ResponseEntity<responseStructure<Resume>> saveSkill(long ApplicantId, String[] skills) {
		Applicant applicant = applicantDao.getApplicant(ApplicantId);
		if (applicant != null) {
			Resume resume = applicant.getResume();
			if (resume != null) {
				/**
				 * -iterate over the Sting arrays skills that is received check if the sill is
				 * present with matching name, if present add the existing to the resume or else
				 * create an new skill -------- oneTomany - from resume to skill we will be
				 * changing that to manyTomany
				 */

				for (String skill : skills) {
					Skill existingskill = skillDao.getSkillByName(skill);
					if (!resume.getSkills().contains(existingskill)) {
						if (existingskill != null) {
							resume.getSkills().add(existingskill);
						} else {
							Skill newSkill = new Skill();
							newSkill.setSkillName(skill);
							resume.getSkills().add(newSkill);
						}
					}
				}

				resume = resumeDao.saveResume(resume);
				responseStructure<Resume> respoStructure = new responseStructure<>();
				respoStructure.setStatusCode(HttpStatus.CREATED.value());
				respoStructure.setMessage("Resume added Successfully!!");
				respoStructure.setData(resume);
				return new ResponseEntity<responseStructure<Resume>>(respoStructure, HttpStatus.CREATED);

			} else {
				throw new ResumeNotFoundIdException("Failed to Add Skills!!!");
			}
		} else {
			throw new ApllicantNotFoundByIdException("Failed to Add Resume!!!");
		}
	}

	public ResponseEntity<responseStructure<Skill>> getSkillById(String skillId) {
		Skill skill = skillDao.getSkillByName(skillId);
		if(skill!=null) {
			skillDto.setSkillId(skill.getSkillId());
			skillDto.setSkillName(skill.getSkillName());
						
			responseStructure<Skill> responseStructure = new responseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("skill Found..");
			responseStructure.setData(skillDto);
			return new ResponseEntity<responseStructure<Skill>> (responseStructure, HttpStatus.FOUND);
		}else {
			throw new SkillNotFoundByIdException("Failed to find Skill!!!");
		}
	}
}
