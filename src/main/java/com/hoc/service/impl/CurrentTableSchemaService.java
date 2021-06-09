package com.hoc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hoc.converter.CurrentTableSchemaConverter;
import com.hoc.converter.SchemaChangeHistoryConverter;
import com.hoc.dto.CurrentTableSchemaDTO;
import com.hoc.dto.SchemaChangeHistoryDTO;
import com.hoc.entity.CurrentTableSchemaEntity;
import com.hoc.entity.SchemaChangeHistoryEntity;
import com.hoc.repository.CurrentTableSchemaRepository;
import com.hoc.repository.SchemaChangeHistoryRepository;
import com.hoc.service.ICurrentTableSchemaService;

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
