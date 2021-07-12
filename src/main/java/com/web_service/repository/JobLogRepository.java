package com.web_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web_service.entity.JobLogEntity;

public interface JobLogRepository extends JpaRepository<JobLogEntity, Long> {

}
