package com.web_service.converter;

import org.springframework.stereotype.Component;

import com.web_service.dto.ETLRequestDTO;
import com.web_service.entity.ETLEntity;

@Component
public class ETLRequestConverter {
	public ETLEntity toEntity(ETLRequestDTO dto) {
		ETLEntity entity = new ETLEntity();
		entity.setQuery(dto.getQuery());
		entity.setQueryType(dto.getQueryType());
		
		return entity;
	}
	
	public ETLRequestDTO toDTO(ETLEntity entity) {
		
		ETLRequestDTO dto = new ETLRequestDTO();
		if(entity.getId() != null) {
			dto.setId(entity.getId());
		}
		RequestConvertor requestConvertor = new RequestConvertor();
		dto.setQuery(entity.getQuery());
		dto.setQueryType(entity.getQueryType());
		dto.setRequest(requestConvertor.toDTO(entity.getRequest()));
		dto.setCreatedDate(entity.getCreatedDate());
		
		return dto;
	}
	
	public ETLEntity toEntity(ETLRequestDTO dto, ETLEntity entity) {
		entity.setQuery(dto.getQuery());
		entity.setQueryType(dto.getQueryType());
		
		return entity;
	}
}
