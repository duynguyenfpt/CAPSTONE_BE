package com.web_service.converter;

import org.springframework.stereotype.Component;

import com.web_service.dto.TableDTO;
import com.web_service.entity.TableEntity;

@Component
public class TableConverter {
	public TableEntity toEntity(TableDTO dto) {
		TableEntity entity = new TableEntity();
		entity.setTableName(dto.getTableName());
		entity.setDefaultKey(dto.getDefaultKey());
		return entity;
	}
	
	public TableDTO toDTO(TableEntity entity) {
		DatabaseInfoConverter databaseInfoConverter = new DatabaseInfoConverter();
		TableDTO dto = new TableDTO();
		if(entity.getId() != null) {
			dto.setId(entity.getId());
		}
		dto.setDefaultKey(entity.getDefaultKey());
		dto.setDatabaseInforId(entity.getDatabaseInfo().getId());
		dto.setTableName(entity.getTableName());
		dto.setCurrentTableSchemas(entity.getCurrentTableSchemaEntities());
		dto.setDatabaseInfo(databaseInfoConverter.toDTO(entity.getDatabaseInfo()));
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		return dto;
	}
	public TableEntity toEntity(TableDTO dto, TableEntity entity) {
		entity.setTableName(dto.getTableName());
		entity.setDefaultKey(dto.getDefaultKey());
		return entity;
	}
}
