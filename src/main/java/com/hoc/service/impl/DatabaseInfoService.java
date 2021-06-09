package com.hoc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hoc.converter.DatabaseInfoConverter;
import com.hoc.dto.DatabaseInfoDTO;
import com.hoc.entity.DatabaseInfoEntity;
import com.hoc.entity.ServerInfoEntity;
import com.hoc.repository.DatabaseInfoRepository;
import com.hoc.repository.ServerInfoRepository;
import com.hoc.service.IDatabaseInfoService;

@Service
public class DatabaseInfoService implements IDatabaseInfoService {
	
	@Autowired
	private DatabaseInfoRepository databaseInfoRepository;
	
	
	@Autowired
	private ServerInfoRepository serverInfoRepository;
	
	@Autowired
	private DatabaseInfoConverter databaseInfoConverter;

	@Override
	public DatabaseInfoDTO save(DatabaseInfoDTO databaseInfoDTO ) {
		DatabaseInfoEntity databaseInfoEntity = new DatabaseInfoEntity();
		if (databaseInfoDTO.getId() != null) {
			DatabaseInfoEntity oldDatabaseInfoEntity = databaseInfoRepository.findOne(databaseInfoDTO.getId());
			databaseInfoEntity = databaseInfoConverter.toEntity(databaseInfoDTO, oldDatabaseInfoEntity);
		} else {
			databaseInfoEntity = databaseInfoConverter.toEntity(databaseInfoDTO);
		}
		
		ServerInfoEntity serverInfoEntity = serverInfoRepository.findOne(databaseInfoDTO.getServer_infor_id());
		databaseInfoEntity.setServerInfo(serverInfoEntity);
		databaseInfoEntity = databaseInfoRepository.save(databaseInfoEntity);
		return databaseInfoConverter.toDTO(databaseInfoEntity);
	}

	@Override
	public void delete(long id) {
		databaseInfoRepository.delete(id);
	}

	@Override
	public List<DatabaseInfoDTO> findAll(Pageable pageable, String keyword) {
		List<DatabaseInfoDTO> results = new ArrayList<>();
		List<DatabaseInfoEntity> entities;
		if(keyword.isEmpty()) {
			entities = databaseInfoRepository.findAll(pageable).getContent();
		}else {
//			entities = databaseInfoRepository.findAll(pageable).getContent();
			entities = databaseInfoRepository.search(keyword, pageable).getContent();
		}
		for (DatabaseInfoEntity item: entities) {
			DatabaseInfoDTO databaseInfoDTO = databaseInfoConverter.toDTO(item);
			results.add(databaseInfoDTO);
		}
		return results;
	}

	@Override
	public int totalItem() {
		return (int) databaseInfoRepository.count();
	}

	@Override
	public DatabaseInfoDTO getById(long id) {
		DatabaseInfoEntity databaseInfoEntity = databaseInfoRepository.findOne(id);
		DatabaseInfoDTO databaseInfoDTO = databaseInfoConverter.toDTO(databaseInfoEntity);
		return databaseInfoDTO;
	}

}
