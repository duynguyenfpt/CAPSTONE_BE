package com.web_service.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.web_service.dto.JobLogDTO;

public interface IJobLogService {
	JobLogDTO save(JobLogDTO JobLogDTO);
	List<JobLogDTO> findAll(Pageable pageable);
	int totalItem();
	JobLogDTO getById(long id);
	void delete(long id);
}
