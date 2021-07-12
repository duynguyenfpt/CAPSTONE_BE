package com.web_service.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web_service.converter.JobConvertor;
import com.web_service.dto.JobDTO;
import com.web_service.entity.AccountEntity;
import com.web_service.entity.JobEntity;
import com.web_service.entity.RequestEntity;
import com.web_service.repository.AccountRepository;
import com.web_service.repository.JobRepository;
import com.web_service.repository.RequestRepository;
import com.web_service.services.IJobService;

@Service
public class JobService implements IJobService{
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private JobConvertor jobConvertor;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private RequestRepository requestRepository;
	
	@Override
	public List<JobDTO> findAll(Pageable pageable) {
		List<JobDTO> results = new ArrayList<>();
		List<JobEntity> entities = jobRepository.findAll(pageable).getContent();
		for (JobEntity item: entities) {
			JobDTO jobDTO = jobConvertor.toDTO(item);
			results.add(jobDTO);
		}
		return results;
	}

	@Override
	public int totalItem() {
		return (int) jobRepository.count();
	}

	@Override
	public JobDTO getById(long id) {
		JobEntity jobEntity = jobRepository.findOne(id);
		JobDTO jobDTO = jobConvertor.toDTO(jobEntity);
		return jobDTO;
	}

	@Override
	public JobDTO save(JobDTO jobDTO) {
		JobEntity jobEntity = new JobEntity();
		if (jobDTO.getId() != null) {
			JobEntity oldJobEntity = jobRepository.findOne(jobDTO.getId());
			jobEntity = jobConvertor.toEntity(jobDTO, oldJobEntity);
		} else {
			jobEntity = jobConvertor.toEntity(jobDTO);
		}
		
		AccountEntity executedBy =  accountRepository.findOne(jobDTO.getExecutedById());
		jobEntity.setExecutedBy(executedBy);
		
		RequestEntity requestEntity = requestRepository.findOne(jobDTO.getRequestId());
		jobEntity.setRequest(requestEntity);
			
		jobEntity = jobRepository.save(jobEntity);
		return jobConvertor.toDTO(jobEntity);
	}

	@Override
	public void delete(long id) {
		jobRepository.delete(id);
	}
}
