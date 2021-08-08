package com.web_service.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.web_service.dto.AccountDTO;
import com.web_service.dto.ETLRequestDTO;
import com.web_service.dto.ShareETLRequestDTO;
import com.web_service.entity.AccountEntity;
import com.web_service.entity.ETLEntity;

public interface IETLService {
	String getResult(Long requestId);
	ETLRequestDTO save(ETLRequestDTO etlRequestDTO);
	void delete(long id);
	List<ETLRequestDTO> findAll(Pageable pageable);
	int totalItem();
	ETLRequestDTO getById(long id);
	void shareETLRequest(ShareETLRequestDTO shareETLRequestDTO);
	List<ETLRequestDTO> getShareETLRequest(Long accountId);
}
