package com.web_service.converter;

import org.springframework.stereotype.Component;

import com.web_service.dto.AccountDTO;
import com.web_service.entity.AccountEntity;

@Component
public class AccountConvertor {
	public AccountEntity toEntity(AccountDTO dto) {
		AccountEntity entity = new AccountEntity();
		entity.setUserName(dto.getUsername());
		entity.setEmail(dto.getEmail());
		entity.setPhone(dto.getPhone());
		entity.setRole(dto.getRole());
		
		return entity;
	}
	
	public AccountDTO toDTO(AccountEntity entity) {
		AccountDTO dto = new AccountDTO();
		if(entity.getId() != null) {
			dto.setId(entity.getId());
		}
		dto.setUsername(entity.getUserName());
		dto.setEmail(entity.getEmail());
		dto.setPhone(entity.getPhone());
		dto.setRole(entity.getRole());
		
		return dto;
	}
	public AccountEntity toEntity(AccountDTO dto, AccountEntity entity) {
		entity.setUserName(dto.getUsername());
		entity.setEmail(dto.getEmail());
		entity.setPhone(dto.getPhone());
		
		return entity;
	}
}
