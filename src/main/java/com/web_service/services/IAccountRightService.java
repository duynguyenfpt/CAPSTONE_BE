package com.web_service.services;


import java.util.List;

import com.web_service.dto.AccountRightDTO;

public interface IAccountRightService {
	List<AccountRightDTO> create(AccountRightDTO accountRightDTO);
	void delete(Long accountId, Long[] rightIds);
}
