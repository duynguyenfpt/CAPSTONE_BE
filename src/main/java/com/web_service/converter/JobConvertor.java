package com.web_service.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.web_service.dto.JobDTO;
import com.web_service.entity.JobEntity;

@Component
public class JobConvertor {
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public JobEntity toEntity(JobDTO dto) {
		JobEntity entity = new JobEntity();
		try {
			entity.setJobSchedule(dateFormat.parse(dto.getJobSchedule()));
		} catch (ParseException e) {

		}
		entity.setActive(dto.isActive());
		entity.setMaxRetry(dto.getMaxRetry());
		
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
			dto.setJobSchedule(dateFormat.format(entity.getJobSchedule()).toString());
		}

		if(entity.getExecutedBy() != null) {
			dto.setExcutedBy(accountConvertor.toDTO(entity.getExecutedBy()));
		}
		
		if(entity.getRequest() != null) {
			dto.setRequest(requestConvertor.toDTO(entity.getRequest()));
		}
		
		dto.setActive(entity.isActive());
		dto.setMaxRetry(entity.getMaxRetry());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		return dto;
	}
	
	public JobEntity toEntity(JobDTO dto, JobEntity entity) {
		try {
			entity.setJobSchedule(dateFormat.parse(dto.getJobSchedule()));
		} catch (ParseException e) {

		}
		entity.setActive(dto.isActive());
		entity.setMaxRetry(dto.getMaxRetry());
		
		return entity;
	}
}
