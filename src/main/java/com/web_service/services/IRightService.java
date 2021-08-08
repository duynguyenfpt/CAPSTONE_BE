package com.web_service.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.web_service.dto.RightDTO;

public interface IRightService {
	List<RightDTO> findAll(String keyword, int page, int limit);
	RightDTO save(RightDTO rightDTO);
	int totalItem();
	List<RightDTO> findAllByAccountId(long accountId, Pageable pageable);
	int countRightByAccountId(long accountId);
	int totalItemSearch(String keyword);
	RightDTO getById(Long id);
	void delete(Long id);
}
