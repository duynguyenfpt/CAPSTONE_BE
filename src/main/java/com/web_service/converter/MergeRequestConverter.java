package com.web_service.converter;

import org.springframework.stereotype.Component;

import com.web_service.dto.MergeRequestDTO;
import com.web_service.entity.MergeRequestEntity;

@Component
public class MergeRequestConverter {
	public MergeRequestEntity toEntity(MergeRequestDTO dto) {
		MergeRequestEntity entity = new MergeRequestEntity();
		entity.setCurrentMetadata(dto.getCurrentMetadata());
		entity.setMergeTableName(dto.getMergeTableName());
		
		return entity;
	}
	
	public MergeRequestDTO toDTO(MergeRequestEntity entity) {
		
		MergeRequestDTO dto = new MergeRequestDTO();
		if(entity.getId() != null) {
			dto.setId(entity.getId());
		}
		RequestConvertor requestConvertor = new RequestConvertor();
		dto.setCurrentMetadata(entity.getCurrentMetadata());
		dto.setLatestMetadata(entity.getLatestMetadata());
		dto.setMergeTableName(entity.getMergeTableName());
		dto.setRequest(requestConvertor.toDTO(entity.getRequest()));
		
		return dto;
	}
	
	public MergeRequestEntity toEntity(MergeRequestDTO dto, MergeRequestEntity entity) {
		entity.setCurrentMetadata(dto.getCurrentMetadata());
		
		return entity;
	}
}
