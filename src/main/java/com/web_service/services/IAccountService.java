package com.web_service.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.web_service.dto.AccountDTO;

public interface IAccountService {
	List<AccountDTO> findAll(Pageable pageable);
	int totalItem();
	AccountDTO getById(long id);
}
