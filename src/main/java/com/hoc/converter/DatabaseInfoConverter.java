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
		entity.setDatabaseType(dto.getDatabaseType());
		entity.setDatabaseName(dto.getDatabaseName());
		return entity;
	}
	
	public DatabaseInfoDTO toDTO(DatabaseInfoEntity entity) {
		DatabaseInfoDTO dto = new DatabaseInfoDTO();
		if(entity.getId() != null) {
			dto.setId(entity.getId());
		}
		dto.setPort(entity.getPort());
		dto.setUsername(entity.getUsername());
		dto.setDatabaseType(entity.getDatabaseName());
		dto.setDatabaseName(entity.getDatabaseName());
		dto.setTables(entity.getTables());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setModifiedBy(entity.getModifiedBy());
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
		entity.setDatabaseType(dto.getDatabaseType());
		entity.setDatabaseName(dto.getDatabaseName());
		return entity;
	}
}
