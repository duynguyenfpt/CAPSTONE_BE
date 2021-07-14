package com.web_service.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web_service.converter.AccountRightConverter;
import com.web_service.dto.AccountRightDTO;
import com.web_service.entity.AccountEntity;
import com.web_service.entity.AccountRightEntity;
import com.web_service.entity.RightEntity;
import com.web_service.repository.AccountRepository;
import com.web_service.repository.AccountRightRepository;
import com.web_service.repository.RightRepository;
import com.web_service.services.IAccountRightService;

@Service
public class AccountRightService implements IAccountRightService{
	@Autowired
	AccountRightRepository accountRightRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	AccountRightConverter accountRightConverter;
	
	@Autowired
	RightRepository rightRepository;

	@Override
	public AccountRightDTO create(AccountRightDTO accountRightDTO) {
		AccountRightEntity accountRightEntity = new AccountRightEntity();
		
		RightEntity rightEntity = rightRepository.findOne(accountRightDTO.getRightId());
		AccountEntity accountEntity = accountRepository.findOne(accountRightDTO.getAccountId());
		
		if(rightEntity != null && accountEntity != null) {
			accountRightEntity.setAccount(accountEntity);
			accountRightEntity.setRight(rightEntity);
			accountRightEntity = accountRightRepository.save(accountRightEntity);
		}
		return accountRightConverter.toDTO(accountRightEntity);		
	}

	@Override
	public void delete(long id) {
		accountRightRepository.delete(id);
	}

}
