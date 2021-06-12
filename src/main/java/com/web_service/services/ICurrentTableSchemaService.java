package com.web_service.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.web_service.dto.CurrentTableSchemaDTO;

public interface ICurrentTableSchemaService {
	List<CurrentTableSchemaDTO> findAll(Pageable pageable, long tableId);
	int totalItem(long tableId);
	CurrentTableSchemaDTO getById(long id);
}
