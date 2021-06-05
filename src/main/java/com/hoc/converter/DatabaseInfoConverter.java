package com.hoc.converter;

import org.springframework.stereotype.Component;

import com.hoc.dto.DatabaseInfoDTO;
import com.hoc.entity.DatabaseInfoEntity;

@Component
public class DatabaseInfoConverter {
	public DatabaseInfoEntity toEntity(DatabaseInfoDTO dto) {
		DatabaseInfoEntity entity = new DatabaseInfoEntity();
		entity.setPort(dto.getPort());
		entity.setUsername(dto.getUsername());
		entity.setDatabaseType(dto.getDatabase_type());
		entity.setDatabaseName(dto.getDatabase_name());
		return entity;
	}
	
	public DatabaseInfoDTO toDTO(DatabaseInfoEntity entity) {
		DatabaseInfoDTO dto = new DatabaseInfoDTO();
		if(entity.getId() != null) {
			dto.setId(entity.getId());
		}
		dto.setPort(entity.getPort());
		dto.setUsername(entity.getUsername());
		dto.setDatabase_type(entity.getDatabaseType());
		dto.setDatabase_name(entity.getDatabaseName());
		dto.setTables(entity.getTables());
		dto.setCreated_date(entity.getCreated_date());
		dto.setCreated_by(entity.getCreated_by());
		dto.setModified_date(entity.getModified_date());
		dto.setModified_by(entity.getModified_by());
		return dto;
	}
	
	public DatabaseInfoDTO includeNewView(DatabaseInfoEntity entity) {
		DatabaseInfoDTO dto = toDTO(entity);
		if(entity.getId() != null) {
			dto.setId(entity.getId());
		}
//		dto.setNews(entity.getNews());
	
		return dto;
	}
	
	
	public DatabaseInfoEntity toEntity(DatabaseInfoDTO dto, DatabaseInfoEntity entity) {
		entity.setPort(dto.getPort());
		entity.setUsername(dto.getUsername());
		entity.setDatabaseType(dto.getDatabase_type());
		entity.setDatabaseName(dto.getDatabase_name());
		return entity;
	}
}
