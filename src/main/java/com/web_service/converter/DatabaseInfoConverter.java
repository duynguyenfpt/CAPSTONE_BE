package com.web_service.converter;

import org.springframework.stereotype.Component;

import com.web_service.dto.DatabaseInfoDTO;
import com.web_service.entity.DatabaseInfoEntity;

@Component
public class DatabaseInfoConverter {
	public DatabaseInfoEntity toEntity(DatabaseInfoDTO dto) {
		DatabaseInfoEntity entity = new DatabaseInfoEntity();
		entity.setPort(dto.getPort());
		entity.setUsername(dto.getUsername());
		entity.setPassword(dto.getPassword());
		entity.setDatabaseType(dto.getDatabaseType());
		entity.setDatabaseName(dto.getDatabaseName());
		entity.setSid(dto.getSid());
		entity.setAlias(dto.getAlias());
		return entity;
	}
	
	public DatabaseInfoDTO toDTO(DatabaseInfoEntity entity) {
		DatabaseInfoDTO dto = new DatabaseInfoDTO();
		if(entity.getId() != null) {
			dto.setId(entity.getId());
		}
		ServerInfoConverter serverInfoConverter = new ServerInfoConverter();
		dto.setPort(entity.getPort());
		dto.setUsername(entity.getUsername());
		dto.setPassword(entity.getPassword());
		dto.setDatabaseType(entity.getDatabaseType());
		dto.setDatabaseName(entity.getDatabaseName());
		dto.setTables(entity.getTables());
		dto.setServerInfor(serverInfoConverter.toDTODefault(entity.getServerInfo()));
		dto.setSid(entity.getSid());
		dto.setAlias(entity.getAlias());
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
		entity.setPassword(dto.getPassword());
		entity.setSid(dto.getSid());
		entity.setDatabaseType(dto.getDatabaseType());
		entity.setDatabaseName(dto.getDatabaseName());
		entity.setAlias(dto.getAlias());
		return entity;
	}
}
