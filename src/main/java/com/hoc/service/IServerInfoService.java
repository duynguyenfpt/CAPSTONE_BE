package com.hoc.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hoc.dto.DatabaseInfoDTO;

public interface IServerInfoService {
	DatabaseInfoDTO save(DatabaseInfoDTO databaseInfoDTO);
//	NewDTO update(NewDTO newDTO);
	void delete(long[] ids);
	List<DatabaseInfoDTO> findAll(Pageable pageable);
//	List<NewDTO> findAll();
	int totalItem();
	DatabaseInfoDTO getById(long id);
}
