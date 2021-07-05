package com.web_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web_service.entity.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, Long>{

}
