package com.web_service.converter;

import org.springframework.stereotype.Component;

import com.web_service.dto.JobLogDTO;
import com.web_service.entity.JobLogEntity;

@Component
public class JobLogConvertor {
	public JobLogEntity toEntity(JobLogDTO dto) {
		JobLogEntity entity = new JobLogEntity();
		entity.setTimeCreated(dto.getTimeCreated());
		entity.setStatus(dto.getStatus());
		entity.setMessage(dto.getMessage());
		
		return entity;
	}
	
	public JobLogDTO toDTO(JobLogEntity entity) {
		
		JobLogDTO dto = new JobLogDTO();
		if(entity.getId() != null) {
			dto.setId(entity.getId());
		}
	
		dto.setTimeCreated(entity.getTimeCreated());
		dto.setStatus(entity.getStatus());
		dto.setMessage(entity.getMessage());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		return dto;
	}
	
	public JobLogEntity toEntity(JobLogDTO dto, JobLogEntity entity) {
		dto.setTimeCreated(entity.getTimeCreated());
		dto.setStatus(entity.getStatus());
		dto.setMessage(entity.getMessage());
		
		return entity;
	}
}
