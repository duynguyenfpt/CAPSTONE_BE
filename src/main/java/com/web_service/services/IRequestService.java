package com.web_service.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.web_service.dto.RequestDTO;

public interface IRequestService {
	RequestDTO save(RequestDTO requestDTO);
	List<RequestDTO> findAll(Pageable pageable);
	int totalItem();
	RequestDTO getById(long id);
	void delete(long id);
	
}
