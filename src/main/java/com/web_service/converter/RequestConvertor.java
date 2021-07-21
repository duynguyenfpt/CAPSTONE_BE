package com.web_service.converter;

import org.springframework.stereotype.Component;

import com.web_service.dto.RequestDTO;
import com.web_service.entity.RequestEntity;

@Component
public class RequestConvertor {
	public RequestEntity toEntity(RequestDTO dto) {
		RequestEntity entity = new RequestEntity();
		entity.setRequestType(dto.getRequestType());
		entity.setStatus(dto.getStatus());
		entity.setApprovedBy(dto.getApprovedBy());
		
		return entity;
	}
	
	public RequestDTO toDTO(RequestEntity entity) {
		
		RequestDTO dto = new RequestDTO();
		if(entity.getId() != null) {
			dto.setId(entity.getId());
		}
		dto.setStatus(entity.getStatus());
		dto.setApprovedBy(entity.getApprovedBy());
		dto.setRequestType(entity.getRequestType());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		return dto;
	}
	
	public RequestEntity toEntity(RequestDTO dto, RequestEntity entity) {
		entity.setStatus(dto.getStatus());
		entity.setApprovedBy(dto.getApprovedBy());
		
		return entity;
	}
}
