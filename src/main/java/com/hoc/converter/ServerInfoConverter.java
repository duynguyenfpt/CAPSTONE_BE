package com.hoc.converter;

import org.springframework.stereotype.Component;

import com.hoc.dto.ServerInfoDTO;
import com.hoc.entity.ServerInfoEntity;

@Component
public class ServerInfoConverter {
	public ServerInfoEntity toEntity(ServerInfoDTO dto) {
		ServerInfoEntity entity = new ServerInfoEntity();
		entity.setServer_domain(dto.getServer_domain());
		entity.setServer_host(dto.getServer_host());
		return entity;
	}
	
	public ServerInfoDTO toDTODefault(ServerInfoEntity entity) {
		ServerInfoDTO dto = new ServerInfoDTO();

		dto.setId(entity.getId());
		dto.setServer_domain(entity.getServer_domain());
		dto.setServer_host(entity.getServer_host());
		return dto;
	}
	
	public ServerInfoDTO toDTO(ServerInfoEntity entity) {
		ServerInfoDTO dto = new ServerInfoDTO();
		if(entity.getId() != null) {
			dto.setId(entity.getId());
		}
		dto.setServer_domain(entity.getServer_domain());
		dto.setServer_host(entity.getServer_host());
		dto.setCreated_date(entity.getCreated_date());
		dto.setCreated_by(entity.getCreated_by());
		dto.setModified_date(entity.getModified_date());
		dto.setModified_by(entity.getModified_by());
		return dto;
	}
	public ServerInfoEntity toEntity(ServerInfoDTO dto, ServerInfoEntity entity) {
		entity.setServer_domain(dto.getServer_domain());
		entity.setServer_host(dto.getServer_host());
		return entity;
	}
}
