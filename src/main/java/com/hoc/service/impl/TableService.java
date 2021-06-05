package com.hoc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hoc.converter.TableConverter;
import com.hoc.dto.TableDTO;
import com.hoc.entity.DatabaseInfoEntity;
import com.hoc.entity.TableEntity;
import com.hoc.repository.DatabaseInfoRepository;
import com.hoc.repository.TableRepository;
import com.hoc.service.ITableService;

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
			TableEntity oldTableEntity = tableRepository.findOne(tableDTO.getId());
			tableEntity = tableConverter.toEntity(tableDTO, oldTableEntity);
		} else {
			tableEntity = tableConverter.toEntity(tableDTO);
		}
		DatabaseInfoEntity databaseInfoEntity = databaseInfoRepository.findOne(tableDTO.getDatabase_infor_id());
		tableEntity.setDatabase_info(databaseInfoEntity);
		tableEntity = tableRepository.save(tableEntity);
		return tableConverter.toDTO(tableEntity);
	}

	@Override
	public void delete(long[] ids) {
		for(long item: ids) {
			tableRepository.delete(item);
		}
		
	}

	@Override
	public List<TableDTO> findAll(Pageable pageable) {
		List<TableDTO> results = new ArrayList<>();
		List<TableEntity> entities = tableRepository.findAll(pageable).getContent();
		for (TableEntity item: entities) {
			TableDTO tableDTO = tableConverter.toDTO(item);
			results.add(tableDTO);
		}
		return results;
	}

	@Override
	public int totalItem() {
		return (int) tableRepository.count();
	}

	@Override
	public TableDTO getById(long id) {
		TableEntity tableEntity = tableRepository.findOne(id);
		TableDTO tableDTO = tableConverter.toDTO(tableEntity);
		return tableDTO;
	}
}
