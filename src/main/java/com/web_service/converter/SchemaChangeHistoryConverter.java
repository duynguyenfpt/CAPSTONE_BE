package com.web_service.converter;

import org.springframework.stereotype.Component;

import com.web_service.dto.SchemaChangeHistoryDTO;
import com.web_service.entity.SchemaChangeHistoryEntity;

@Component
public class SchemaChangeHistoryConverter {
	public SchemaChangeHistoryEntity toEntity(SchemaChangeHistoryDTO dto) {
		SchemaChangeHistoryEntity entity = new SchemaChangeHistoryEntity();
		entity.setChange_type(dto.getChange_type());
		entity.setNew_value(dto.getNew_value());
		entity.setField_change(dto.getField_change());
		entity.setOld_value(dto.getOld_value());
		
		return entity;
	}
	
	public SchemaChangeHistoryDTO toDTO(SchemaChangeHistoryEntity entity) {
		SchemaChangeHistoryDTO dto = new SchemaChangeHistoryDTO();
		if(entity.getId() != null) {
			dto.setId(entity.getId());
		}
		dto.setChange_type(entity.getChange_type());
		dto.setNew_value(entity.getNew_value());
		dto.setField_change(entity.getField_change());
		dto.setOld_value(entity.getOld_value());
		dto.setCreated_date(entity.getCreated_date());
		dto.setCreated_by(entity.getCreated_by());
		dto.setModified_date(entity.getModified_date());
		dto.setModified_by(entity.getModified_by());
		return dto;
	}
	public SchemaChangeHistoryEntity toEntity(SchemaChangeHistoryDTO dto, SchemaChangeHistoryEntity entity) {
		entity.setChange_type(dto.getChange_type());
		entity.setNew_value(dto.getNew_value());
		entity.setField_change(dto.getField_change());
		entity.setOld_value(dto.getOld_value());

		return entity;
	}
}
