package com.training.job_portal_application.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.training.job_portal_application.dao.ApplicantDao;
import com.training.job_portal_application.dao.ResumeDao;
import com.training.job_portal_application.dto.ResumeDto;
import com.training.job_portal_application.entity.Applicant;
import com.training.job_portal_application.entity.Resume;
import com.training.job_portal_application.exception.ApllicantNotFoundByIdException;
import com.training.job_portal_application.exception.ResumeNotFoundIdException;
import com.training.job_portal_application.util.responseStructure;

@Service
public class ResumeService {

	@Autowired
	private ResumeDao resumeDao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ApplicantDao applicantDao;
	@Autowired
	private ResumeDto resumeDto;

	public ResponseEntity<responseStructure<Resume>> saveResume(long applicantId, ResumeDto resumeDto) {

		Applicant applicant = applicantDao.getApplicant(applicantId);
		if (applicant != null) {
			Resume existingResume = applicant.getResume();
			Resume resume = this.modelMapper.map(resumeDto, Resume.class);
			if (existingResume != null) {
				resume.setResumeId(existingResume.getResumeId());
			}
			resume.setApplicant(applicant);
			resume = resumeDao.saveResume(resume);
			applicant.setResume(resume);
			applicantDao.addApplicant(applicant);

			responseStructure<Resume> respoStructure = new responseStructure<>();
			respoStructure.setStatusCode(HttpStatus.CREATED.value());
			respoStructure.setMessage("Resume added Successfully!!");
			respoStructure.setData(resume);
			return new ResponseEntity<responseStructure<Resume>>(respoStructure, HttpStatus.CREATED);
		} else {
			 throw new ApllicantNotFoundByIdException("Failed to add resume!");
		}

	}

	public ResponseEntity<responseStructure<Resume>> getResumeById(long resumeId) {
		Resume resume = resumeDao.getResumeById(resumeId);
		if (resume != null) {
			resumeDto.setResumeId(resume.getResumeId());
			resumeDto.setSummary(resume.getSummary());
			resumeDto.setQualification(resume.getQualification());
			resumeDto.setUniversity(resume.getUniversity());
			resumeDto.setSocialProfile1(resume.getSocialProfile1());
			resumeDto.setSocialProfile2(resume.getSocialProfile2());
			resumeDto.setSocialProfile3(resume.getSocialProfile3());
			resumeDto.setCertification(resume.getCertification());

			responseStructure<Resume> responseStructure = new responseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Resume Found..");
			responseStructure.setData(resumeDto);
			return new ResponseEntity<responseStructure<Resume>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new ResumeNotFoundIdException("Failed to find Resume!!!");
		}
	}
}
