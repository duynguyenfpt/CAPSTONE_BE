package com.web_service.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.web_service.dto.RequestDTO;

public interface IRequestService {
	RequestDTO create(RequestDTO requestDTO);
	RequestDTO update(RequestDTO requestDTO);
	List<RequestDTO> findAll(String requestType, String status, String approvedBy, int page, int limit);
	int totalItem();
	RequestDTO getById(long id);
	void delete(long id);
	int totalItemSearch(String requestType, String status, String approvedBy);
}
