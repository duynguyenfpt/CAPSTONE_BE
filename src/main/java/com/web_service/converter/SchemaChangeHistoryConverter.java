package com.web_service.converter;

import org.springframework.stereotype.Component;

import com.web_service.dto.SchemaChangeHistoryDTO;
import com.web_service.entity.SchemaChangeHistoryEntity;

@Component
public class SchemaChangeHistoryConverter {
	public SchemaChangeHistoryEntity toEntity(SchemaChangeHistoryDTO dto) {
		SchemaChangeHistoryEntity entity = new SchemaChangeHistoryEntity();
		entity.setChangeType(dto.getChangeType());
		entity.setNewValue(dto.getNewValue());
		entity.setFieldChange(dto.getFieldChange());
		entity.setOldValue(dto.getOldValue());
		
		return entity;
	}
	
	public SchemaChangeHistoryDTO toDTO(SchemaChangeHistoryEntity entity) {
		SchemaChangeHistoryDTO dto = new SchemaChangeHistoryDTO();
		if(entity.getId() != null) {
			dto.setId(entity.getId());
		}
		dto.setChangeType(entity.getChangeType());
		dto.setNewValue(entity.getNewValue());
		dto.setFieldChange(entity.getFieldChange());
		dto.setOldValue(entity.getOldValue());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		return dto;
	}
	public SchemaChangeHistoryEntity toEntity(SchemaChangeHistoryDTO dto, SchemaChangeHistoryEntity entity) {
		entity.setChangeType(dto.getChangeType());
		entity.setNewValue(dto.getNewValue());
		entity.setFieldChange(dto.getFieldChange());
		entity.setOldValue(dto.getOldValue());

		return entity;
	}
}
