package com.web_service.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.web_service.dto.TableDTO;

public interface ITableService {
	TableDTO save(TableDTO tableDTO);
	void delete(long id);
	List<TableDTO> findAll(String keyword, Pageable pageable);
	int totalItem(String keyword);
	TableDTO getById(long id);
	List<TableDTO> findByDatabaseInfoId(long databaseInfoId, Pageable pageable);
	int totalItemByDatabaseId(long databaseId);

}
