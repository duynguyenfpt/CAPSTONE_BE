package com.web_service.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web_service.converter.RightConverter;
import com.web_service.dto.RightDTO;
import com.web_service.entity.RightEntity;
import com.web_service.repository.RightRepository;
import com.web_service.services.IRightService;

@Service
public class RightService implements IRightService{
	@Autowired
	RightRepository rightRepository;
	
	@Autowired
	RightConverter rightConverter;
	
	@Override
	public List<RightDTO> findAll(Pageable pageable) {
		List<RightDTO> results = new ArrayList<>();
		List<RightEntity> entities = rightRepository.findAll(pageable).getContent();
		for (RightEntity item: entities) {
			RightDTO rightDTO = rightConverter.toDTO(item);
			results.add(rightDTO);
		}
		return results;
	}

	@Override
	public int totalItem() {
		return (int) rightRepository.count();
	}

	@Override
	public List<RightDTO> findAllByAccountId(long accountId, Pageable pageable) {
		List<RightDTO> results = new ArrayList<>();
		List<RightEntity> entities = rightRepository.findRightByAccountId(accountId, pageable).getContent();
		for (RightEntity item: entities) {
			RightDTO rightDTO = rightConverter.toDTO(item);
			results.add(rightDTO);
		}
		return results;
	}

	@Override
	public int countRightByAccountId(long accountId) {
		return (int) rightRepository.countByAccountId(accountId).size();
	}

}
