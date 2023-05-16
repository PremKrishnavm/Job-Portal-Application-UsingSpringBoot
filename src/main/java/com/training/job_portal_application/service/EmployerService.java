package com.training.job_portal_application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.training.job_portal_application.dao.EmployerDao;
import com.training.job_portal_application.dto.EmployerDto;
import com.training.job_portal_application.entity.Employer;
import com.training.job_portal_application.exception.EmployerNotFoundByIdException;
import com.training.job_portal_application.util.responseStructure;


@Service
public class EmployerService {
	
	@Autowired
	private EmployerDao employerDao;
	
	@Autowired
	private EmployerDto employerDto;
	
	public ResponseEntity<responseStructure<Employer>> addEmployer(Employer employer){
		Employer employer2 = employerDao.addEmployer(employer);
		
		responseStructure<Employer> responseStructure = new responseStructure<>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Employer Added Successfully.");
		responseStructure.setData(employer2);
		return new ResponseEntity<responseStructure<Employer>>(responseStructure,HttpStatus.CREATED);
	}

	public ResponseEntity<responseStructure<Employer>> getEmployerById(long employerId) {
		Employer employer  = employerDao.getEmployer(employerId);
		if(employer!=null) {
			employerDto.setEmployeeId(employer.getEmployerId());
			employerDto.setEmployeeName(employer.getEmployerName());
			employerDto.setEmployeeEmail(employer.getEmployerEmail());
//			employerDto.setJobs(employer.getJobs());
			
			responseStructure<Employer> responseStructure = new responseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Employer found Successfully.");
			responseStructure.setData(employer);
			return new ResponseEntity<responseStructure<Employer>>(responseStructure,HttpStatus.FOUND);
		}else {
			throw new EmployerNotFoundByIdException("Failed to find to Employer!!");
		}
	}
}
