package com.web_service.converter;

import org.springframework.stereotype.Component;

import com.web_service.dto.AccountRightDTO;
import com.web_service.dto.ServerInfoDTO;
import com.web_service.entity.AccountRightEntity;
import com.web_service.entity.ServerInfoEntity;

@Component
public class AccountRightConverter {

	
	public AccountRightDTO toDTO(AccountRightEntity entity) {
		AccountRightDTO dto = new AccountRightDTO();
		if(entity.getId() != null) {
			dto.setId(entity.getId());
		}
		AccountConvertor accountConvertor = new AccountConvertor();
		RightConverter rightConverter = new RightConverter();
		
		dto.setAccount(accountConvertor.toDTO(entity.getAccount()));
		dto.setRight(rightConverter.toDTO(entity.getRight()));
		
		return dto;
	}
	public ServerInfoEntity toEntity(ServerInfoDTO dto, ServerInfoEntity entity) {
		entity.setServerDomain(dto.getServerDomain());
		entity.setServerHost(dto.getServerHost());
		return entity;
	}
}
