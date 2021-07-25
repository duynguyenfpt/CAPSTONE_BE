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
		entity.setCode(dto.getCode());
		entity.setRightName(dto.getRightName());
		return entity;
	}
	
	public RightDTO toDTO(RightEntity entity) {
		RightDTO dto = new RightDTO();

		dto.setId(entity.getId());
		dto.setCode(entity.getCode());
		dto.setRightName(entity.getRightName());
		return dto;
	}
	
	public RightEntity toEntity(RightDTO dto, RightEntity entity) {
		entity.setCode(dto.getCode());
		entity.setRightName(dto.getRightName());
		return entity;
	}
}
