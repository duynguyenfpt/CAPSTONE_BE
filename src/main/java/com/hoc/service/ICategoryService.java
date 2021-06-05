package com.hoc.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hoc.dto.CategoryDTO;

public interface ICategoryService {
	CategoryDTO save(CategoryDTO categoryDTO);
//	NewDTO update(NewDTO newDTO);
	void delete(long[] ids);
	List<CategoryDTO> findAll(Pageable pageable);
//	List<NewDTO> findAll();
	int totalItem();
	CategoryDTO getById(long id);
}
