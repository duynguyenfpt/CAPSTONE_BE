package com.web_service.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.web_service.dto.AccountDTO;
import com.web_service.dto.AccountRightDTO;
import com.web_service.dto.ServerInfoDTO;
import com.web_service.entity.AccountEntity;

public interface IAccountService {
	List<AccountDTO> findAll(String keyword, Pageable pageable);
	int totalItem(String keyword);
	AccountDTO getById(long id);
	AccountDTO save(AccountDTO accountDTO);
	void resetPassword(long id);
	AccountDTO findByUserName(String username);
	void createAccountWithRights(AccountRightDTO accountRightDTO);
	void forgotPassword(String username);
	AccountEntity findByUserNameEntity(String username);
	List<AccountDTO> getAccountActive(Pageable pageable);
	int totalItemAccountActive();
}
