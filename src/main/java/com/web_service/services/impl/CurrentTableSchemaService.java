package com.web_service.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web_service.converter.CurrentTableSchemaConverter;
import com.web_service.dto.CurrentTableSchemaDTO;
import com.web_service.entity.CurrentTableSchemaEntity;
import com.web_service.repository.CurrentTableSchemaRepository;
import com.web_service.services.ICurrentTableSchemaService;

@Service
public class CurrentTableSchemaService implements ICurrentTableSchemaService {

	@Autowired
	private CurrentTableSchemaRepository currentTableSchemaRepository;
	
	@Autowired
	private CurrentTableSchemaConverter currentTableSchemaConverter;
	

	@Override
	public List<CurrentTableSchemaDTO> findAll(Pageable pageable, long tableId) {
		List<CurrentTableSchemaDTO> results = new ArrayList<>();
		List<CurrentTableSchemaEntity> entities = currentTableSchemaRepository.findByTableInfoId(tableId, pageable).getContent();
		for (CurrentTableSchemaEntity item: entities) {
			CurrentTableSchemaDTO currentTableSchemaDTO = currentTableSchemaConverter.toDTO(item);
			results.add(currentTableSchemaDTO);
		}
		return results;
	}

	@Override
	public int totalItem(long tableId) {
		return (int) currentTableSchemaRepository.countByTableInfoId(tableId).size();
	}

	@Override
	public CurrentTableSchemaDTO getById(long id) {
		CurrentTableSchemaEntity schemaChangeHistoryEntity = currentTableSchemaRepository.findOne(id);
		CurrentTableSchemaDTO schemaChangeHistoryDTO = currentTableSchemaConverter.toDTO(schemaChangeHistoryEntity);
		return schemaChangeHistoryDTO;
	}

}
