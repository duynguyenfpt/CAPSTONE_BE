package com.web_service.services;


import com.web_service.dto.AccountRightDTO;

public interface IAccountRightService {
	AccountRightDTO create(AccountRightDTO accountRightDTO);
	void delete(long id);
}
