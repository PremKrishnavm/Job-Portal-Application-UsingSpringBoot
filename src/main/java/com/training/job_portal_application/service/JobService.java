package com.training.job_portal_application.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.training.job_portal_application.dao.EmployerDao;
import com.training.job_portal_application.dao.JobDao;
import com.training.job_portal_application.dto.JobDto;
import com.training.job_portal_application.entity.Employer;
import com.training.job_portal_application.entity.Job;
import com.training.job_portal_application.exception.EmployerNotFoundByIdException;
import com.training.job_portal_application.exception.JobNotFoundByIdException;
import com.training.job_portal_application.util.responseStructure;


@Service
public class JobService {
	
	@Autowired
	private JobDao jobDao;
	@Autowired
	private EmployerDao employerDao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private JobDto jobDto;
	
	public ResponseEntity<responseStructure<Job>> addJob(JobDto jobDto, long employerId){
		
		Employer employer = employerDao.getEmployer(employerId);
		if(employer!=null) {
			Job job = this.modelMapper.map(jobDto, Job.class);
			job.setJobCreateDatetime(LocalDateTime.now());
			job.setEmployer(employer);
			job = jobDao.addJob(job);
			
			employer.getJobs().add(job);
			employerDao.addEmployer(employer);
			
			jobDto.setJobId(job.getJobId());
			responseStructure<Job> responseStructure = new responseStructure<>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Job added successfully!!");
			responseStructure.setData(jobDto);
			
			return new ResponseEntity<responseStructure<Job>> (responseStructure, HttpStatus.CREATED);
		}else {
			throw new EmployerNotFoundByIdException("Failed to add job!!!");
		}
	}
	
	public ResponseEntity<responseStructure<Job>> getJobById(long jobId) {
		Job job = jobDao.getJobById(jobId);
		if(job!=null) {
			jobDto.setJobId(job.getJobId());
			jobDto.setJobTitle(job.getJobTitle());
			jobDto.setJobDiscription(job.getJobDiscription());
			jobDto.setCompany(job.getCompany());
			jobDto.setSalary(job.getSalary());
			
			responseStructure<Job> responseStructure = new responseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Job Found..");
			responseStructure.setData(jobDto);
			return new ResponseEntity<responseStructure<Job>> (responseStructure, HttpStatus.FOUND);
		}else {
			throw new JobNotFoundByIdException("Failed to find job!!!");
		}
	}
}