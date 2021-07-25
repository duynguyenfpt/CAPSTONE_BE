package com.web_service.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.web_service.dto.RightDTO;
import com.web_service.dto.ServerInfoDTO;

public interface IRightService {
	List<RightDTO> findAll(Pageable pageable);
	RightDTO save(RightDTO rightDTO);
	int totalItem();
	List<RightDTO> findAllByAccountId(long accountId, Pageable pageable);
	int countRightByAccountId(long accountId);
}
