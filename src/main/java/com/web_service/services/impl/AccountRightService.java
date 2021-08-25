package com.web_service.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

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
	@Transactional
	public List<AccountRightDTO> create(AccountRightDTO accountRightDTO) {
		List<AccountRightDTO> result = new ArrayList<AccountRightDTO>();
		for (long rightId : accountRightDTO.getRightIds()) {
			RightEntity rightEntity = rightRepository.findOne(rightId);
			AccountEntity accountEntity = accountRepository.findOne(accountRightDTO.getAccountId());
			//Check right and account is exist
			if(rightEntity != null && accountEntity != null) {
				AccountRightEntity accountRightEntity = new AccountRightEntity();
				accountRightEntity.setAccount(accountEntity);
				accountRightEntity.setRight(rightEntity);
				accountRightEntity = accountRightRepository.save(accountRightEntity);
				result.add(accountRightConverter.toDTO(accountRightEntity));
			}
		}	
		return result;
	}

	@Override
	@Transactional
	public void delete(Long accountId, Long[] rightIds) {
		for (Long id : rightIds) {
			accountRightRepository.deleteRight(accountId, id);
		}
	}
}
