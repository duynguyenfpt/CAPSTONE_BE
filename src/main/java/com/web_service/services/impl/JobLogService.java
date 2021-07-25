package com.web_service.services.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Service;

import com.web_service.entity.mongo.JobLogEntity;
import com.web_service.repository.JobLogRepository;
import com.web_service.services.IJobLogService;

@Service
public class JobLogService implements IJobLogService{

	@Autowired
	private JobLogRepository jobLogRepository;
	
	@Override
	public JobLogEntity save(JobLogEntity jobLogEntity) {
		jobLogEntity = jobLogRepository.save(jobLogEntity);
		return jobLogEntity;
	}

	@Override
	public List<JobLogEntity> findAll(Pageable pageable) {
		List<JobLogEntity> entities = jobLogRepository.findAll(pageable).getContent();
		
		return entities;
	}

	@Override
	public int totalItem() {
		return (int) jobLogRepository.count();
	}

	@Override
	public List<JobLogEntity> findAllByJobId(long jobId, Pageable pageable) {
		List<JobLogEntity> entities = jobLogRepository.findByJobId(jobId, pageable).getContent();
		
		return entities;
	}

	@Override
	public int totalItemByJobId(long jobId) {
		return jobLogRepository.getAllJobLogByJobId(jobId).size();
	}

	@Override
	public JobLogEntity getLastJobLog(long jobId) {
		List<JobLogEntity> entities = jobLogRepository.getAllJobLogByJobId(jobId);
		
		JobLogEntity lastJobLog = entities.stream().filter(e -> e.getStatus() != "processing")
										  .max(Comparator.comparing(JobLogEntity::getCreatedAt)).get();
		
		return lastJobLog;
	}
}
