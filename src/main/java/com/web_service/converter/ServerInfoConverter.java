package com.web_service.converter;

import org.springframework.stereotype.Component;

import com.web_service.dto.ServerInfoDTO;
import com.web_service.entity.ServerInfoEntity;

@Component
public class ServerInfoConverter {
	public ServerInfoEntity toEntity(ServerInfoDTO dto) {
		ServerInfoEntity entity = new ServerInfoEntity();
		entity.setServerDomain(dto.getServerDomain());
		entity.setServerHost(dto.getServerHost());
		return entity;
	}
	
	public ServerInfoDTO toDTODefault(ServerInfoEntity entity) {
		ServerInfoDTO dto = new ServerInfoDTO();

		dto.setId(entity.getId());
		dto.setServerDomain(entity.getServerDomain());
		dto.setServerHost(entity.getServerHost());
		return dto;
	}
	
	public ServerInfoDTO toDTO(ServerInfoEntity entity) {
		ServerInfoDTO dto = new ServerInfoDTO();
		if(entity.getId() != null) {
			dto.setId(entity.getId());
		}
		dto.setServerDomain(entity.getServerDomain());
		dto.setServerHost(entity.getServerHost());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setModifiedBy(entity.getModifiedBy());
		return dto;
	}
	public ServerInfoEntity toEntity(ServerInfoDTO dto, ServerInfoEntity entity) {
		entity.setServerDomain(dto.getServerDomain());
		entity.setServerHost(dto.getServerHost());
		return entity;
	}
}
