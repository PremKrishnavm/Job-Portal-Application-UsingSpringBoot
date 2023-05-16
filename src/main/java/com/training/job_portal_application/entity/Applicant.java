package com.training.job_portal_application.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Applicant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long applicatId;
	private String applicantName;
	private String applicantEmail;
	private String applicantPassword;
	private long applicantPhNo;
	
	@OneToMany(mappedBy = "applicant")
	@JsonIgnore
	private List<JobApplication> jobApplications;
	
	@ManyToOne
	@JoinColumn
	private Resume resume;
}
