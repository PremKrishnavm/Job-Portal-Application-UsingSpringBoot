package com.training.job_portal_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.job_portal_application.entity.Employer;

public interface EmployerRepo extends JpaRepository<Employer, Long>{

}
