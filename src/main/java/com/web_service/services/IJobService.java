package com.web_service.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.web_service.dto.JobDTO;
import com.web_service.dto.JobDetailDTO;

public interface IJobService {
	JobDTO save(JobDTO jobDTO);
	List<JobDTO> findAll(Pageable pageable);
	int totalItem();
	JobDTO getById(long id);
	void delete(long id);
	JobDetailDTO getJobDetail(long jobId);
	int totalItemJobDetails(long jobId);
}
