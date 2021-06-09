package com.hoc.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hoc.dto.CurrentTableSchemaDTO;
import com.hoc.dto.SchemaChangeHistoryDTO;

public interface ICurrentTableSchemaService {
	List<CurrentTableSchemaDTO> findAll(Pageable pageable);
	int totalItem();
	CurrentTableSchemaDTO getById(long id);
}
