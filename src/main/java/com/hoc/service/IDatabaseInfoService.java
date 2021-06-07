package com.hoc.service;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.hoc.dto.DatabaseInfoDTO;

public interface IDatabaseInfoService {
	DatabaseInfoDTO save(DatabaseInfoDTO databaseInfoDTO);
//	NewDTO update(NewDTO newDTO);
	void delete(long id);
	List<DatabaseInfoDTO> findAll(Pageable pageable);
//	List<NewDTO> findAll();
	int totalItem();
	DatabaseInfoDTO getById(long id);
	ResponseEntity<Map<String,Object>> trackingConnection(DatabaseInfoDTO databaseInfoDTO);
}
