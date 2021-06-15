package com.web_service.services.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.web_service.converter.DatabaseInfoConverter;
import com.web_service.dto.DatabaseInfoDTO;
import com.web_service.entity.DatabaseInfoEntity;
import com.web_service.entity.ServerInfoEntity;
import com.web_service.repository.DatabaseInfoRepository;
import com.web_service.repository.ServerInfoRepository;
import com.web_service.services.IDatabaseInfoService;

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
		
		ServerInfoEntity serverInfoEntity = serverInfoRepository.findOne(databaseInfoDTO.getServerInforId());
		databaseInfoEntity.setServerInfo(serverInfoEntity);
		databaseInfoEntity = databaseInfoRepository.save(databaseInfoEntity);
		databaseInfoRepository.flush();
		
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

	@Override
	public ResponseEntity<Map<String,Object>> trackingConnection(DatabaseInfoDTO databaseInfoDTO) {
		ServerInfoEntity serverInfoEntity = serverInfoRepository.findOne(databaseInfoDTO.getServerInforId());
		String USER = databaseInfoDTO.getUsername();
		String PASS = databaseInfoDTO.getPassword();
		String HOST = serverInfoEntity.getServerHost();
		String PORT = databaseInfoDTO.getPort();
		String DATABASENAME = databaseInfoDTO.getDatabaseName();
		String URL = "";
		Connection conn;
		boolean trackingConnection;
		Map<String, Object> response = new LinkedHashMap<>();
		try {
			switch (databaseInfoDTO.getDatabaseType()) {
			case "mysql":
				URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASENAME;
				conn = DriverManager.getConnection(URL, USER, PASS);

				break;
			case "postgresql":
				URL = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASENAME;
				conn = DriverManager.getConnection(URL, USER, PASS);

				break;
			case "sql":
				URL = "jdbc:sqlserver://" + HOST + ":" + PORT + ";databaseName=" + DATABASENAME
						+ ";integratedSecurity=true";
				conn = DriverManager.getConnection(URL, USER, PASS);

				break;
			case "oracle":
				URL = "jdbc:oracal:thin:" + USER + "/" + PASS + "@" + HOST + ":" + PORT + ":" + DATABASENAME;
				conn = DriverManager.getConnection(URL);

				break;
			default:
				trackingConnection = false;
			}
			conn = DriverManager.getConnection(URL, USER, PASS);
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
