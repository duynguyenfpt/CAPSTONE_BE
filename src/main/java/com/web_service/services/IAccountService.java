package com.web_service.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.web_service.dto.AccountDTO;
import com.web_service.dto.ServerInfoDTO;

public interface IAccountService {
	List<AccountDTO> findAll(Pageable pageable);
	int totalItem();
	AccountDTO getById(long id);
	AccountDTO save(AccountDTO accountDTO);
	void resetPassword(long id);
	AccountDTO findByUserName(String username);
}
