package com.web_service.converter;

import org.springframework.stereotype.Component;

import com.web_service.dto.RightDTO;
import com.web_service.dto.ServerInfoDTO;
import com.web_service.entity.RightEntity;
import com.web_service.entity.ServerInfoEntity;

@Component
public class RightConverter {
	public RightEntity toEntity(RightDTO dto) {
		RightEntity entity = new RightEntity();
		entity.setMethod(dto.getMethod());
		entity.setPath(dto.getPath());
		entity.setDescription(dto.getDescription());
		return entity;
	}
	
	public RightDTO toDTO(RightEntity entity) {
		RightDTO dto = new RightDTO();

		dto.setId(entity.getId());
		dto.setPath(entity.getPath());
		dto.setMethod(entity.getMethod());
		dto.setDescription(entity.getDescription());
		return dto;
	}
	
	public RightEntity toEntity(RightDTO dto, RightEntity entity) {
		entity.setPath(dto.getPath());
		entity.setMethod(dto.getMethod());
		entity.setDescription(dto.getDescription());
		return entity;
	}
}
