package com.web_service.converter;

import org.springframework.stereotype.Component;

import com.web_service.dto.SyncTableRequestDTO;
import com.web_service.entity.SyncTableRequestEntity;

@Component
public class SyncTableConverter {
	public SyncTableRequestEntity toEntity(SyncTableRequestDTO dto) {
		SyncTableRequestEntity entity = new SyncTableRequestEntity();
		entity.setIsAll(dto.isAll());
		entity.setFromDate(dto.getFromDate());
		entity.setToDate(dto.getToDate());
		entity.setPartitionBy(dto.getPartitionBy());
		entity.setIdentityId(dto.getIdentityId());
		
		return entity;
	}
	
	public SyncTableRequestDTO toDTODefault(SyncTableRequestEntity entity) {
		SyncTableRequestDTO dto = new SyncTableRequestDTO();

		dto.setIsAll(entity.isAll());
		dto.setFromDate(entity.getFromDate());
		dto.setToDate(entity.getToDate());
		dto.setPartitionBy(entity.getPartitionBy());
		dto.setIdentityId(entity.getIdentityId());
		dto.setIsProcess(entity.getIsProcess());
		
		return dto;
	}
	
	public SyncTableRequestDTO toDTO(SyncTableRequestEntity entity) {
		SyncTableRequestDTO dto = new SyncTableRequestDTO();
		if(entity.getId() != null) {
			dto.setId(entity.getId());
		}
		dto.setIsAll(entity.isAll());
		dto.setFromDate(entity.getFromDate());
		dto.setToDate(entity.getToDate());
		dto.setPartitionBy(entity.getPartitionBy());
		dto.setIdentityId(entity.getIdentityId());
		dto.setMessage(entity.getMessage());
		return dto;
	}
	public SyncTableRequestEntity toEntity(SyncTableRequestDTO dto, SyncTableRequestEntity entity) {
		dto.setIsAll(entity.isAll());
		dto.setFromDate(entity.getFromDate());
		dto.setToDate(entity.getToDate());
		dto.setPartitionBy(entity.getPartitionBy());
		dto.setIdentityId(entity.getIdentityId());
		
		return entity;
	}
}
