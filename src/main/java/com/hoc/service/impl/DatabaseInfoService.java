package com.hoc.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hoc.converter.DatabaseInfoConverter;
import com.hoc.dto.DatabaseInfoDTO;
import com.hoc.entity.DatabaseInfoEntity;
import com.hoc.repository.DatabaseInfoRepository;
import com.hoc.service.IDatabaseInfoService;

@Service
public class DatabaseInfoService implements IDatabaseInfoService {
	
	@Autowired
	private DatabaseInfoRepository databaseInfoRepository;
	
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

		databaseInfoEntity = databaseInfoRepository.save(databaseInfoEntity);
		return databaseInfoConverter.toDTO(databaseInfoEntity);
	}

	@Override
	public void delete(long id) {
		databaseInfoRepository.delete(id);
	}

	@Override
	public List<DatabaseInfoDTO> findAll(Pageable pageable) {
		List<DatabaseInfoDTO> results = new ArrayList<>();
		List<DatabaseInfoEntity> entities = databaseInfoRepository.findAll(pageable).getContent();
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

	@Override
	public ResponseEntity<Map<String,Object>> trackingConnection(DatabaseInfoDTO databaseInfoDTO) {
		String URL = "jdbc:mysql://" + databaseInfoDTO.getHost() + ":" + databaseInfoDTO.getPort() + "/" + databaseInfoDTO.getDatabase_name();
		String USER = databaseInfoDTO.getUsername();
		String PASS = databaseInfoDTO.getPassword();
		boolean trackingConnection;
		Map<String, Object> response = new LinkedHashMap<>();
		
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			trackingConnection = true;
			conn.close();
		} catch (SQLException e) {
			trackingConnection = false;
		}
		response.put("success: ", trackingConnection);
		
		if(trackingConnection) {
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
		}
		
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
	}

}
