package com.web_service.converter;

import org.springframework.stereotype.Component;

import com.web_service.dto.TableDTO;
import com.web_service.entity.TableEntity;

@Component
public class TableConverter {
	public TableEntity toEntity(TableDTO dto) {
		TableEntity entity = new TableEntity();
		entity.setTableName(dto.getTable_name());
		return entity;
	}
	
	public TableDTO toDTO(TableEntity entity) {
		TableDTO dto = new TableDTO();
		if(entity.getId() != null) {
			dto.setId(entity.getId());
		}
		dto.setDatabase_infor_id(entity.getDatabaseInfo().getId());
		dto.setTable_name(entity.getTableName());
		dto.setCurrent_table_schemas(entity.getCurrentTableSchemaEntities());
		dto.setCreated_date(entity.getCreated_date());
		dto.setCreated_by(entity.getCreated_by());
		dto.setModified_date(entity.getModified_date());
		dto.setModified_by(entity.getModified_by());
		return dto;
	}
	public TableEntity toEntity(TableDTO dto, TableEntity entity) {
		entity.setTableName(dto.getTable_name());
		return entity;
	}
}
