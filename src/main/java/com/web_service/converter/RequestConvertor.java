package com.web_service.converter;

import org.springframework.stereotype.Component;

import com.web_service.dto.RequestDTO;
import com.web_service.entity.RequestEntity;

@Component
public class RequestConvertor {
	public RequestEntity toEntity(RequestDTO dto) {
		RequestEntity entity = new RequestEntity();
		entity.setRequestType(dto.getRequestType());
		
		return entity;
	}
	
	public RequestDTO toDTO(RequestEntity entity) {
		AccountConvertor accountConvertor = new AccountConvertor();
		
		RequestDTO dto = new RequestDTO();
		if(entity.getId() != null) {
			dto.setId(entity.getId());
		}
		dto.setStatus(entity.getStatus());
		if(entity.getCreator() != null) {
			dto.setCreator(accountConvertor.toDTO(entity.getCreator()));
		}
		if(entity.getApprovedBy() != null) {
			dto.setApprovedBy(accountConvertor.toDTO(entity.getApprovedBy()));
		}
		
		dto.setRequestType(entity.getRequestType());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		return dto;
	}
	
	public RequestEntity toEntity(RequestDTO dto, RequestEntity entity) {
		entity.setRequestType(dto.getRequestType());
		
		return entity;
	}
}
