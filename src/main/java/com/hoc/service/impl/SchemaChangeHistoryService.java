package com.hoc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hoc.converter.SchemaChangeHistoryConverter;
import com.hoc.dto.SchemaChangeHistoryDTO;
import com.hoc.entity.SchemaChangeHistoryEntity;
import com.hoc.repository.SchemaChangeHistoryRepository;
import com.hoc.service.ISchemaChangeHistoryService;

@Service
public class SchemaChangeHistoryService implements ISchemaChangeHistoryService {
	@Autowired
	private SchemaChangeHistoryRepository schemaChangeHistoryRepository;
	
	@Autowired
	private SchemaChangeHistoryConverter schemaChangeHistoryConverter;
	

	@Override
	public List<SchemaChangeHistoryDTO> findAll(Pageable pageable) {
		List<SchemaChangeHistoryDTO> results = new ArrayList<>();
		List<SchemaChangeHistoryEntity> entities = schemaChangeHistoryRepository.findAll(pageable).getContent();
		for (SchemaChangeHistoryEntity item: entities) {
			SchemaChangeHistoryDTO schemaChangeHistoryDTO = schemaChangeHistoryConverter.toDTO(item);
			results.add(schemaChangeHistoryDTO);
		}
		return results;
	}

	@Override
	public int totalItem() {
		return (int) schemaChangeHistoryRepository.count();
	}

	@Override
	public SchemaChangeHistoryDTO getById(long id) {
		SchemaChangeHistoryEntity schemaChangeHistoryEntity = schemaChangeHistoryRepository.findOne(id);
		SchemaChangeHistoryDTO schemaChangeHistoryDTO = schemaChangeHistoryConverter.toDTO(schemaChangeHistoryEntity);
		return schemaChangeHistoryDTO;
	}
}
