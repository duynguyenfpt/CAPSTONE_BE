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
	EntityManager em;

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

	@Override
	public List<SchemaChangeHistoryDTO> search(Long tableId, String changeType, int page, int limit) {
		String query = "select * from schema_change_histories where ";
//		        "where (table_info_id is null or table_info_id = ?1 ) " +
//		        "and (change_type is null or change_type LIKE %?2% ) " +
		if (tableId != null) {
			query += "table_info_id = " + tableId;
		}

		if (changeType != null) {
			if (tableId != null)
				query += " and ";
			query += "change_type LIKE" + changeType;
		}

		List<SchemaChangeHistoryEntity> schemaChangeHistoryEntities = em
				.createNativeQuery(query, SchemaChangeHistoryEntity.class).setFirstResult(limit)
				.setMaxResults(page * limit).getResultList();
		List<SchemaChangeHistoryDTO> results = new ArrayList<>();
		for (SchemaChangeHistoryEntity item : schemaChangeHistoryEntities) {
			SchemaChangeHistoryDTO schemaChangeHistoryDTO = schemaChangeHistoryConverter.toDTO(item);
			results.add(schemaChangeHistoryDTO);
		}

		return results;
	}

	@Override
	public int totalItemSearch(Long tableId, String changeType) {
		String query = "select * from schema_change_histories where ";

		if (tableId != null) {
			query += "table_info_id = " + tableId;
		}

		if (changeType != null) {
			if (tableId != null)
				query += " and ";
			query += "change_type LIKE" + changeType;
		}

		List<SchemaChangeHistoryEntity> schemaChangeHistoryEntities = em
				.createNativeQuery(query, SchemaChangeHistoryEntity.class)
				.getResultList();
		
		return schemaChangeHistoryEntities.size();
	}
}
