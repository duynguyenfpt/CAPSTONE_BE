package com.web_service.services;

import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;

import com.web_service.dto.ContentETLRequestDTO;
import com.web_service.dto.ETLRequestDTO;
import com.web_service.dto.ShareETLRequestDTO;

public interface IETLService {
	ContentETLRequestDTO getResult(Long requestId);
	ETLRequestDTO save(ETLRequestDTO etlRequestDTO);
	void delete(long id);
	List<ETLRequestDTO> findAll(Pageable pageable);
	int totalItem();
	ETLRequestDTO getById(long id);
	void shareETLRequest(ShareETLRequestDTO shareETLRequestDTO);
	List<ETLRequestDTO> getShareETLRequest(Long accountId);
	boolean downloadCSV(Long requestId);
}
