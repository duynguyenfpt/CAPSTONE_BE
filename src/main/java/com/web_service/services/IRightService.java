package com.web_service.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.web_service.dto.RightDTO;
import com.web_service.dto.ServerInfoDTO;

public interface IRightService {
	List<RightDTO> findAll(String rightName, String code, int page, int limit);
	RightDTO save(RightDTO rightDTO);
	int totalItem();
	List<RightDTO> findAllByAccountId(long accountId, Pageable pageable);
	int countRightByAccountId(long accountId);
	int totalItemSearch(String rightName, String code);
}
