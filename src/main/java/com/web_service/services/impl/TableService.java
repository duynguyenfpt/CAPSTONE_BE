package com.web_service.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web_service.converter.TableConverter;
import com.web_service.dto.TableDTO;
import com.web_service.entity.DatabaseInfoEntity;
import com.web_service.entity.TableEntity;
import com.web_service.repository.DatabaseInfoRepository;
import com.web_service.repository.TableRepository;
import com.web_service.services.ITableService;

@Service
public class TableService implements ITableService {
	@Autowired
	private TableRepository tableRepository;
	
	@Autowired
	private DatabaseInfoRepository databaseInfoRepository;
	
	@Autowired
	private TableConverter tableConverter;

	@Override
	public TableDTO save(TableDTO tableDTO) {
		TableEntity tableEntity = new TableEntity();
		if (tableDTO.getId() != null) {
			//update table infor
			TableEntity oldTableEntity = tableRepository.findOne(tableDTO.getId());
			tableEntity = tableConverter.toEntity(tableDTO, oldTableEntity);
		} else {
			//create table infor
			tableEntity = tableConverter.toEntity(tableDTO);
			DatabaseInfoEntity databaseInfoEntity = databaseInfoRepository.findOne(tableDTO.getDatabaseInforId());
			tableEntity.setDatabaseInfo(databaseInfoEntity);
		}
		tableEntity = tableRepository.save(tableEntity);
		return tableConverter.toDTO(tableEntity);
	}

	@Override
	public void delete(long id) {
		tableRepository.delete(id);		
	}

	@Override
	public List<TableDTO> findAll(String keyword, Pageable pageable) {
		List<TableDTO> results = new ArrayList<>();
		//get all table infor
		List<TableEntity> entities = tableRepository.search(keyword, pageable).getContent();
		for (TableEntity item: entities) {
			TableDTO tableDTO = tableConverter.toDTO(item);
			results.add(tableDTO);
		}
		return results;
	}

	@Override
	public int totalItem(String keyword) {
		return (int) tableRepository.countSearchTotalItem(keyword);
	}

	@Override
	public TableDTO getById(long id) {
		//get table by id
		TableEntity tableEntity = tableRepository.findOne(id);
		TableDTO tableDTO = tableConverter.toDTO(tableEntity);
		return tableDTO;
	}

	@Override
	public List<TableDTO> findByDatabaseInfoId(long databaseInfoId, Pageable pageable) {
		//get table by database
		List<TableDTO> results = new ArrayList<>();
		DatabaseInfoEntity databaseInfoEntity = databaseInfoRepository.findOne(databaseInfoId);
		List<TableEntity> entities = tableRepository.findByDatabaseInfo(databaseInfoEntity, pageable).getContent();
		for (TableEntity item: entities) {
			TableDTO tableDTO = tableConverter.toDTO(item);
			results.add(tableDTO);
		}
		return results;
	}
	
	@Override
	public int totalItemByDatabaseId(long databaseId) {
		DatabaseInfoEntity databaseInfoEntity = databaseInfoRepository.findOne(databaseId);
		
		return databaseInfoEntity.getTables().size();
	}
}
