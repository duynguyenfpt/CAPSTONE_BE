package com.web_service.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.web_service.dto.JobDTO;
import com.web_service.entity.JobEntity;

@Component
public class JobConvertor {

	public JobEntity toEntity(JobDTO dto) {
		JobEntity entity = new JobEntity();
		entity.setJobSchedule(dto.getJobSchedule());
		entity.setActive(dto.isActive());
		entity.setMaxRetries(dto.getMaxRetries());
		if(dto.getStatus() != null) {
			entity.setStatus(dto.getStatus());
		}
		if(dto.getNumberRetries() != null) {
			entity.setNumberRetries(dto.getNumberRetries());
		}
		return entity;
	}
	
	public JobDTO toDTO(JobEntity entity) {
		AccountConvertor accountConvertor = new AccountConvertor();
		RequestConvertor requestConvertor = new RequestConvertor();
		
		JobDTO dto = new JobDTO();
		if(entity.getId() != null) {
			dto.setId(entity.getId());
		}
		if(entity.getJobSchedule() != null) {
			dto.setJobSchedule(entity.getJobSchedule());
		}

		if(entity.getExecutedBy() != null) {
			dto.setExcutedBy(accountConvertor.toDTO(entity.getExecutedBy()));
		}
		
		if(entity.getRequest() != null) {
			dto.setRequest(requestConvertor.toDTO(entity.getRequest()));
		}
		
		dto.setActive(entity.isActive());
		dto.setMaxRetries(entity.getMaxRetries());
		dto.setStatus(entity.getStatus());
		dto.setNumberRetries(entity.getNumberRetries());;
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		return dto;
	}
	
	public JobEntity toEntity(JobDTO dto, JobEntity entity) {
		entity.setJobSchedule(dto.getJobSchedule());
		if(dto.getNumberRetries() != null) {
			entity.setNumberRetries(dto.getNumberRetries());
		}
		entity.setActive(dto.isActive());
		entity.setMaxRetries(dto.getMaxRetries());
		if(dto.getStatus() != null) {
			entity.setStatus(dto.getStatus());
		}
		
		return entity;
	}
}
