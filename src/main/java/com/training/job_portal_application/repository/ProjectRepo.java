package com.training.job_portal_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.job_portal_application.entity.Project;

public interface ProjectRepo extends JpaRepository<Project, Long>{

}
