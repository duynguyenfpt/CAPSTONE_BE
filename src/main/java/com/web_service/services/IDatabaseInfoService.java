package com.web_service.services;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.web_service.dto.DatabaseInfoDTO;

public interface IDatabaseInfoService {
	DatabaseInfoDTO save(DatabaseInfoDTO databaseInfoDTO);
//	NewDTO update(NewDTO newDTO);
	void delete(long id);
	List<DatabaseInfoDTO> findAll(Pageable pageable, String keyword);
//	List<NewDTO> findAll();
	int totalItem(String keyword);
	DatabaseInfoDTO getById(long id);
	boolean trackingConnection(DatabaseInfoDTO databaseInfoDTO);
}
