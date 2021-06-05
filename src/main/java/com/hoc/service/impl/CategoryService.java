package com.hoc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hoc.converter.CategoryConverter;
import com.hoc.dto.CategoryDTO;
import com.hoc.entity.CategoryEntity;
import com.hoc.repository.CategoryRepository;
import com.hoc.repository.NewRepository;
import com.hoc.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService{
	
	@Autowired
	private NewRepository newRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CategoryConverter categoryConverter;

	@Override
	public CategoryDTO save(CategoryDTO categoryDTO) {
		CategoryEntity categoryEntity = new CategoryEntity();
		if (categoryDTO.getId() != null) {
			CategoryEntity oldCategoryEntity = categoryRepository.findOne(categoryDTO.getId());
			categoryEntity = categoryConverter.toEntity(categoryDTO, oldCategoryEntity);
		} else {
			categoryEntity = categoryConverter.toEntity(categoryDTO);
		}

		categoryEntity = categoryRepository.save(categoryEntity);
		return categoryConverter.toDTO(categoryEntity);
	}

	@Override
	public void delete(long[] ids) {
		for(long item: ids) {
			newRepository.delete(item);
		}
	}

	@Override
	public List<CategoryDTO> findAll(Pageable pageable) {
		List<CategoryDTO> results = new ArrayList<>();
		List<CategoryEntity> entities = categoryRepository.findAll(pageable).getContent();
		for (CategoryEntity item: entities) {
			CategoryDTO categoryDTO = categoryConverter.toDTO(item);
			results.add(categoryDTO);
		}
		return results;
	}

	@Override
	public int totalItem() {
		return (int) categoryRepository.count();
	}

	@Override
	public CategoryDTO getById(long id) {
		CategoryEntity categoryEntity = categoryRepository.findOne(id);
		CategoryDTO categoryDTO = categoryConverter.toDTO(categoryEntity);
		return categoryDTO;
	}

}
