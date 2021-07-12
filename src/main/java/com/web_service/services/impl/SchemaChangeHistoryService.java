package com.web_service.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web_service.converter.SchemaChangeHistoryConverter;
import com.web_service.dto.SchemaChangeHistoryDTO;
import com.web_service.entity.SchemaChangeHistoryEntity;
import com.web_service.repository.SchemaChangeHistoryRepository;
import com.web_service.services.ISchemaChangeHistoryService;

@Service
public class SchemaChangeHistoryService implements ISchemaChangeHistoryService {
	@Autowired
	private SchemaChangeHistoryRepository schemaChangeHistoryRepository;

	@Autowired
	private SchemaChangeHistoryConverter schemaChangeHistoryConverter;

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<SchemaChangeHistoryDTO> findAll(Pageable pageable) {
		List<SchemaChangeHistoryDTO> results = new ArrayList<>();
		List<SchemaChangeHistoryEntity> entities = schemaChangeHistoryRepository.findAll(pageable).getContent();
		for (SchemaChangeHistoryEntity item : entities) {
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

	public List<SchemaChangeHistoryDTO> search(Long tableId, String changeType, int page, int limit) {
		if (changeType == null) changeType = "";
		String query = "select * from schema_change_histories where LOWER(change_type) LIKE '%"
				+ changeType.toLowerCase() + "%'";

		if (tableId != null) {
			query += " and table_info_id = " + tableId;
		}

		List<SchemaChangeHistoryEntity> schemaChangeHistoryEntities = em
				.createNativeQuery(query, SchemaChangeHistoryEntity.class)
				.setFirstResult((page - 1) * limit)
				.setMaxResults(limit)
				.getResultList();
		List<SchemaChangeHistoryDTO> results = new ArrayList<>();
		for (SchemaChangeHistoryEntity item : schemaChangeHistoryEntities) {
			SchemaChangeHistoryDTO schemaChangeHistoryDTO = schemaChangeHistoryConverter.toDTO(item);
			results.add(schemaChangeHistoryDTO);
		}

		return results;
	}

	@Override
	public int totalItemSearch(Long tableId, String changeType) {
		if (changeType == null) changeType = "";
		String query = "select * from schema_change_histories where LOWER(change_type) LIKE '%"
				+ changeType.toLowerCase() + "%'";

		if (tableId != null) {
			query += " and table_info_id = " + tableId;
		}

		List<SchemaChangeHistoryEntity> schemaChangeHistoryEntities = em
				.createNativeQuery(query, SchemaChangeHistoryEntity.class).getResultList();

		return schemaChangeHistoryEntities.size();
	}
}
