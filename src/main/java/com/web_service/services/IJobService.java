package com.web_service.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.web_service.dto.JobDTO;

public interface IJobService {
	JobDTO save(JobDTO jobDTO);
	List<JobDTO> findAll(Pageable pageable);
	int totalItem();
	JobDTO getById(long id);
	void delete(long id);
}
