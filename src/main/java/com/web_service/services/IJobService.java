package com.web_service.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.web_service.dto.JobDTO;
import com.web_service.dto.JobDetailDTO;

public interface IJobService {
	JobDTO save(JobDTO jobDTO);
	List<JobDTO> findAll(String keyword, Pageable pageable);
	int totalItem(String keyword);
	JobDTO getById(long id);
	void delete(long id);
	List<JobDetailDTO> getJobDetail(long jobId);
	int totalItemJobDetails(long jobId);
}
