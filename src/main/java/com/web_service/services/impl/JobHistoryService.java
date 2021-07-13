package com.web_service.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web_service.entity.mongo.JobHistoryEntity;
import com.web_service.repository.JobHistoryRepository;
import com.web_service.services.IJobHistoryService;

@Service
public class JobHistoryService implements IJobHistoryService{
	@Autowired
	JobHistoryRepository jobHistoryRepository;

	@Override
	public JobHistoryEntity save(JobHistoryEntity jobHistoryEntity) {

		jobHistoryEntity = jobHistoryRepository.save(jobHistoryEntity);
		return jobHistoryEntity;
	}
	
	@Override
	public int totalItemByJobId(long jobId) {
		return jobHistoryRepository.getByJobId(jobId).size();
	}

	@Override
	public List<JobHistoryEntity> findAllByJobId(long jobId, Pageable pageable) {
		List<JobHistoryEntity> entities = jobHistoryRepository.findByJobId(jobId, pageable).getContent();
		
		return entities;
	}
}
