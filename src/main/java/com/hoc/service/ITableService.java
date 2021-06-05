package com.hoc.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hoc.dto.TableDTO;

public interface ITableService {
	TableDTO save(TableDTO tableDTO);
	void delete(long id);
	List<TableDTO> findAll(Pageable pageable);
	int totalItem();
	TableDTO getById(long id);
	List<TableDTO> findByDatabaseInfoId(long databaseInfoId, Pageable pageable);

}
