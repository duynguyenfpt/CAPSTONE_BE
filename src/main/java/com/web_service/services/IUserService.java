package com.web_service.services;

import com.web_service.dto.AccountDTO;

public interface IUserService {
	AccountDTO save(AccountDTO newDTO);
	void delete(long[] ids);
	int totalItem();
	AccountDTO getById(long id);
}
