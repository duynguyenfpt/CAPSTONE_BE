package com.web_service.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.web_service.entity.mongo.JobLogEntity;

public interface IJobLogService {
	JobLogEntity save(JobLogEntity jobLogEntity);
	List<JobLogEntity> findAll(String host, String port, String databaseName, String tableName,
			String requestType, String status, Pageable pageable);
	int totalItem(String host, String port, String databaseName, String tableName,
			String requestType, String status);
	List<JobLogEntity> findAllByJobId(long jobId, Pageable pageable);
	int totalItemByJobId(long jobId);
	JobLogEntity getLastJobLog(long jobId);
}