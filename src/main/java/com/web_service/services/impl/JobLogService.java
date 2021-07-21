package com.web_service.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
		return jobLogRepository.getAllNoteByJobId(jobId).size();
	}
	
	
	
}
