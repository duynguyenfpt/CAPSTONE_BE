package com.web_service.services.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
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
			//Update database infor
			DatabaseInfoEntity oldDatabaseInfoEntity = databaseInfoRepository.findOne(databaseInfoDTO.getId());
			databaseInfoEntity = databaseInfoConverter.toEntity(databaseInfoDTO, oldDatabaseInfoEntity);
		} else {
			//Create database infor
			boolean trackingConnection = trackingConnection(databaseInfoDTO);
			
			//If connection success to create
			if(trackingConnection) {
				databaseInfoEntity = databaseInfoConverter.toEntity(databaseInfoDTO);
			}else {
				return null;
			}
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
			//Get all database
			entities = databaseInfoRepository.findAll(pageable).getContent();
		}else {
			//Search database
			entities = databaseInfoRepository.search(keyword, pageable).getContent();
		}
		for (DatabaseInfoEntity item: entities) {
			DatabaseInfoDTO databaseInfoDTO = databaseInfoConverter.toDTO(item);
			results.add(databaseInfoDTO);
		}
		return results;
	}

	@Override
	public int totalItem(String keyword) {
		if(keyword.isEmpty()) {
			return (int) databaseInfoRepository.count();
		}else {
			return databaseInfoRepository.search(keyword);
		}
	}

	@Override
	public DatabaseInfoDTO getById(long id) {
		//Get database infor by id
		DatabaseInfoEntity databaseInfoEntity = databaseInfoRepository.findOne(id);
		DatabaseInfoDTO databaseInfoDTO = databaseInfoConverter.toDTO(databaseInfoEntity);
		return databaseInfoDTO;
	}

	@Override
	public boolean trackingConnection(DatabaseInfoDTO databaseInfoDTO) {
		ServerInfoEntity serverInfoEntity = serverInfoRepository.findOne(databaseInfoDTO.getServerInforId());
		String USER = databaseInfoDTO.getUsername();
		String PASS = databaseInfoDTO.getPassword();
		String HOST = serverInfoEntity.getServerHost();
		String PORT = databaseInfoDTO.getPort();
		String DATABASENAME = databaseInfoDTO.getDatabaseName();
		String SID = databaseInfoDTO.getSid();
		String URL = "";
		Connection conn = null;
		boolean trackingConnection;
		try {
			switch (databaseInfoDTO.getDatabaseType()) {
			case "mysql":
				//check connection with mysql
				URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASENAME +"?useSSL=false&serverTimezone=UTC";
				conn = DriverManager.getConnection(URL, USER, PASS);

				break;
			case "postgresql":
				//check connection with postgresql
				URL = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASENAME;
				conn = DriverManager.getConnection(URL, USER, PASS);

				break;
			case "oracle":
				//check connection with oracle
				Class.forName("oracle.jdbc.driver.OracleDriver");
	            conn = DriverManager.getConnection(String.format("jdbc:oracle:thin:%s/%s@%s:%s:%s", USER, PASS, HOST, PORT, SID));

				break;
			default:
				
				trackingConnection = false;
			}
			
			if(conn == null) {
				trackingConnection = false;
			}else {
				trackingConnection = true;
			}
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			trackingConnection = false;
		} catch (Exception e) {
			trackingConnection = false;
		} finally {
			
		}
		
		return trackingConnection;
	}

}
