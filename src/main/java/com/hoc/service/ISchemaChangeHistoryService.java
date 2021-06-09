package com.hoc.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hoc.dto.SchemaChangeHistoryDTO;
import com.hoc.dto.ServerInfoDTO;

public interface ISchemaChangeHistoryService {
	List<SchemaChangeHistoryDTO> findAll(Pageable pageable);
	int totalItem();
	SchemaChangeHistoryDTO getById(long id);
}
