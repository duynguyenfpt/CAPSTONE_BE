package com.web_service.converter;

import org.springframework.stereotype.Component;

import com.web_service.dto.CurrentTableSchemaDTO;
import com.web_service.entity.CurrentTableSchemaEntity;

@Component
public class CurrentTableSchemaConverter {
	public CurrentTableSchemaEntity toEntity(CurrentTableSchemaDTO dto) {
		CurrentTableSchemaEntity entity = new CurrentTableSchemaEntity();
		entity.setRowName(dto.getRowName());
		entity.setRowType(dto.getRowType());
		entity.setTypeSize(dto.getTypeSize());
		entity.setDefaultValue(dto.getDefaultValue());
		entity.setPossibleValue(dto.getPossibleValue());
		
		return entity;
	}
	
	public CurrentTableSchemaDTO toDTO(CurrentTableSchemaEntity entity) {
		CurrentTableSchemaDTO dto = new CurrentTableSchemaDTO();
		if(entity.getId() != null) {
			dto.setId(entity.getId());
		}
		dto.setRowName(entity.getRowName());
		dto.setRowType(entity.getRowType());
		dto.setTypeSize(entity.getTypeSize());
		dto.setDefaultValue(entity.getDefaultValue());
		dto.setPossibleValue(entity.getPossibleValue());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		return dto;
	}
	public CurrentTableSchemaEntity toEntity(CurrentTableSchemaDTO dto, CurrentTableSchemaEntity entity) {
		entity.setRowName(dto.getRowName());
		entity.setRowType(dto.getRowType());
		entity.setTypeSize(dto.getTypeSize());
		entity.setDefaultValue(dto.getDefaultValue());
		entity.setPossibleValue(dto.getPossibleValue());

		return entity;
	}
}
