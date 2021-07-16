package com.web_service.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.web_service.dto.RightDTO;

public interface IRightService {
	List<RightDTO> findAll(Pageable pageable);
	int totalItem();
	List<RightDTO> findAllByAccountId(long accountId, Pageable pageable);
	int countRightByAccountId(long accountId);
}
