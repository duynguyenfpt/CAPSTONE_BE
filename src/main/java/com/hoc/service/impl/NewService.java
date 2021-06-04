package com.hoc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hoc.converter.NewConverter;
import com.hoc.dto.NewDTO;
import com.hoc.entity.CategoryEntity;
import com.hoc.entity.NewEntity;
import com.hoc.repository.CategoryRepository;
import com.hoc.repository.NewRepository;
import com.hoc.service.INewService;

@Service
public class NewService implements INewService {
	
	@Autowired
	private NewRepository newRepository;
	
	@Autowired
	private CategoryRepository CategoryRepository;
	
	@Autowired
	private NewConverter newConverter;

	@Override
	public NewDTO save(NewDTO newDTO) {
		NewEntity newEntity = new NewEntity();
		if (newDTO.getId() != null) {
			NewEntity oldNewEntity = newRepository.findOne(newDTO.getId());
			newEntity = newConverter.toEntity(newDTO, oldNewEntity);
		} else {
			newEntity = newConverter.toEntity(newDTO);
		}
		CategoryEntity categoryEntity = CategoryRepository.findOneByCode(newDTO.getCategoryCode());
		newEntity.setCategory(categoryEntity);
		newEntity = newRepository.save(newEntity);
		return newConverter.toDTO(newEntity);
	}

	@Override
	public void delete(long[] ids) {
		for(long item: ids) {
			newRepository.delete(item);
		}
		
	}

	@Override
	public List<NewDTO> findAll(Pageable pageable) {
		List<NewDTO> results = new ArrayList<>();
		List<NewEntity> entities = newRepository.findAll(pageable).getContent();
		for (NewEntity item: entities) {
			NewDTO newDTO = newConverter.toDTO(item);
			results.add(newDTO);
		}
		return results;
	}

	@Override
	public int totalItem() {
		return (int) newRepository.count();
	}

	@Override
	public NewDTO getById(long id) {
		NewEntity newEntity = newRepository.findOne(id);
		NewDTO newDTO = newConverter.toDTO(newEntity);
		return newDTO;
	}
	

//	@Override
//	public NewDTO update(NewDTO newDTO) {
//		NewEntity oldNewEntity = newRepository.findOne(newDTO.getId());
//		NewEntity newEntity = newConverter.toEntity(newDTO, oldNewEntity);
//		CategoryEntity categoryEntity = CategoryRepository.findOneByCode(newDTO.getCategoryCode());
//		newEntity.setCategory(categoryEntity);
//		newEntity = newRepository.save(newEntity);
//		return newConverter.toDTO(newEntity);
//	}
//	
}
