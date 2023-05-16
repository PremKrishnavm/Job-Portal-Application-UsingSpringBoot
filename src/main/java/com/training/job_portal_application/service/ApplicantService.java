package com.training.job_portal_application.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.training.job_portal_application.dao.ApplicantDao;
import com.training.job_portal_application.dto.ApplicantDto;
import com.training.job_portal_application.entity.Applicant;
import com.training.job_portal_application.exception.ApllicantNotFoundByIdException;
import com.training.job_portal_application.util.responseStructure;

@Service
public class ApplicantService {

	@Autowired
	private ApplicantDao applicantDao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ApplicantDto applicantDto;

	public ResponseEntity<responseStructure<ApplicantDto>> saveApplicant(Applicant applicant) {

		applicant = applicantDao.addApplicant(applicant);
		ApplicantDto applicantDto = this.modelMapper.map(applicant, ApplicantDto.class);
		responseStructure<ApplicantDto> responseStructure = new responseStructure<>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Application Add Successfully");
		responseStructure.setData(applicantDto);
		return new ResponseEntity<responseStructure<ApplicantDto>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<responseStructure<Applicant>> getApplicant(long applicantId) {
		Applicant applicant = applicantDao.getApplicant(applicantId);
		if (applicant != null) {
			applicantDto.setApplicatId(applicant.getApplicatId());
			applicantDto.setApplicantName(applicant.getApplicantName());
			applicantDto.setApplicantEmail(applicant.getApplicantEmail());
			applicantDto.setApplicantPhNo(applicant.getApplicantPhNo());

			responseStructure<Applicant> responseStructure = new responseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("applicant Found..");
			responseStructure.setData(applicantDto);
			return new ResponseEntity<responseStructure<Applicant>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new ApllicantNotFoundByIdException("Failed to find applicant!!!");
		}
	}
}
