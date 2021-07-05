package com.web_service.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web_service.converter.JobLogConvertor;
import com.web_service.dto.JobLogDTO;
import com.web_service.entity.JobEntity;
import com.web_service.entity.JobLogEntity;
import com.web_service.repository.JobLogRepository;
import com.web_service.repository.JobRepository;
import com.web_service.services.IJobLogService;

@Service
public class JobLogService implements IJobLogService{

	@Autowired
	private JobLogRepository jobLogRepository;
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private JobLogConvertor jobLogConvertor;
	
	
	@Override
	public List<JobLogDTO> findAll(Pageable pageable) {
		List<JobLogDTO> results = new ArrayList<>();
		List<JobLogEntity> entities = jobLogRepository.findAll(pageable).getContent();
		for (JobLogEntity item: entities) {
			JobLogDTO jobLogDTO = jobLogConvertor.toDTO(item);
			results.add(jobLogDTO);
		}
		return results;
	}

	@Override
	public int totalItem() {
		return (int) jobLogRepository.count();
	}

	@Override
	public JobLogDTO getById(long id) {
		JobLogEntity jobLogEntity = jobLogRepository.findOne(id);
		JobLogDTO jobLogDTO = jobLogConvertor.toDTO(jobLogEntity);
		return jobLogDTO;
	}

	@Override
	public JobLogDTO save(JobLogDTO jobLogDTO) {
		JobLogEntity jobLogEntity = new JobLogEntity();
		if (jobLogDTO.getId() != null) {
			JobLogEntity oldJobLogEntity = jobLogRepository.findOne(jobLogDTO.getId());
			jobLogEntity = jobLogConvertor.toEntity(jobLogDTO, oldJobLogEntity);
		}
		JobEntity job =  jobRepository.findOne(jobLogDTO.getJobId());
		jobLogEntity.setJob(job);
			
		jobLogEntity = jobLogRepository.save(jobLogEntity);
		return jobLogConvertor.toDTO(jobLogEntity);
	}

	@Override
	public void delete(long id) {
		jobLogRepository.delete(id);
	}
}
