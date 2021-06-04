package com.hoc.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hoc.dto.NewDTO;

public interface INewService {
	NewDTO save(NewDTO newDTO);
//	NewDTO update(NewDTO newDTO);
	void delete(long[] ids);
	List<NewDTO> findAll(Pageable pageable);
//	List<NewDTO> findAll();
	int totalItem();
	NewDTO getById(long id);
}
