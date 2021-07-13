package com.web_service.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.web_service.entity.mongo.JobHistoryEntity;

public interface IJobHistoryService {
	JobHistoryEntity save(JobHistoryEntity jobHistoryEntity);
	List<JobHistoryEntity> findAllByJobId(long jobId, Pageable pageable);
	int totalItemByJobId(long jobId);
}
