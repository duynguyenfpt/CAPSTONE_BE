package com.web_service.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web_service.converter.AccountConvertor;
import com.web_service.dto.AccountDTO;

import com.web_service.entity.AccountEntity;
import com.web_service.repository.AccountRepository;
import com.web_service.services.IAccountService;

@Service
public class AccountService implements IAccountService {
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountConvertor accountConvertor;
	
	@Override
	public List<AccountDTO> findAll(Pageable pageable) {
		List<AccountDTO> results = new ArrayList<>();
		List<AccountEntity> entities = accountRepository.findAll(pageable).getContent();
		for (AccountEntity item: entities) {
			AccountDTO accountDTO = accountConvertor.toDTO(item);
			results.add(accountDTO);
		}
		return results;
	}

	@Override
	public int totalItem() {
		return (int) accountRepository.count();
	}

	@Override
	public AccountDTO getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
