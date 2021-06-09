package com.hoc.converter;

import org.springframework.stereotype.Component;

import com.hoc.dto.CurrentTableSchemaDTO;
import com.hoc.dto.SchemaChangeHistoryDTO;
import com.hoc.entity.CurrentTableSchemaEntity;
import com.hoc.entity.SchemaChangeHistoryEntity;

@Component
public class CurrentTableSchemaConverter {
	public CurrentTableSchemaEntity toEntity(CurrentTableSchemaDTO dto) {
		CurrentTableSchemaEntity entity = new CurrentTableSchemaEntity();
		entity.setRow_name(dto.getRow_name());
		entity.setRow_type(dto.getRow_type());
		entity.setType_size(dto.getType_size());
		entity.setDefault_value(dto.getDefault_value());
		entity.setPossible_value(dto.getPossible_value());
		
		return entity;
	}
	
	public CurrentTableSchemaDTO toDTO(CurrentTableSchemaEntity entity) {
		CurrentTableSchemaDTO dto = new CurrentTableSchemaDTO();
		if(entity.getId() != null) {
			dto.setId(entity.getId());
		}
		dto.setRow_name(entity.getRow_name());
		dto.setRow_type(entity.getRow_type());
		dto.setType_size(entity.getType_size());
		dto.setDefault_value(entity.getDefault_value());
		dto.setPossible_value(entity.getPossible_value());
		dto.setCreated_date(entity.getCreated_date());
		dto.setCreated_by(entity.getCreated_by());
		dto.setModified_date(entity.getModified_date());
		dto.setModified_by(entity.getModified_by());
		return dto;
	}
	public CurrentTableSchemaEntity toEntity(CurrentTableSchemaDTO dto, CurrentTableSchemaEntity entity) {
		entity.setRow_name(dto.getRow_name());
		entity.setRow_type(dto.getRow_type());
		entity.setType_size(dto.getType_size());
		entity.setDefault_value(dto.getDefault_value());
		entity.setPossible_value(dto.getPossible_value());

		return entity;
	}
}
