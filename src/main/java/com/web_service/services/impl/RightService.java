package com.web_service.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web_service.converter.RightConverter;
import com.web_service.dto.RequestDTO;
import com.web_service.dto.RightDTO;
import com.web_service.dto.ServerInfoDTO;
import com.web_service.entity.RequestEntity;
import com.web_service.entity.RightEntity;
import com.web_service.entity.ServerInfoEntity;
import com.web_service.repository.RightRepository;
import com.web_service.services.IRightService;

@Service
public class RightService implements IRightService{
	@Autowired
	RightRepository rightRepository;
	
	@Autowired
	RightConverter rightConverter;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<RightDTO> findAll(String rightName, String code, int page, int limit) {
		String query = createSearchQuery(rightName, code);
		
		@SuppressWarnings("unchecked")
		List<RightEntity> rightEntities = em.createNativeQuery(query, RightEntity.class)
				.setFirstResult((page - 1) * limit)
				.setMaxResults(limit)
				.getResultList();
		List<RightDTO> results = new ArrayList<>();
		for (RightEntity item: rightEntities) {
			RightDTO rightDTO = rightConverter.toDTO(item);
			results.add(rightDTO);
		}
		return results;
	}
	
	@Override
	public int totalItemSearch(String rightName, String code) {
		String query = createSearchQuery(rightName, code);

		@SuppressWarnings("unchecked")
		List<RightEntity> rightEntities = em
				.createNativeQuery(query, RightEntity.class).getResultList();

		return rightEntities.size();
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
		return (int) rightRepository.countByAccountId(accountId);
	}
	
	@Override
	public RightDTO save(RightDTO rightDTO ) {
		RightEntity rightEntity = new RightEntity();
		if (rightDTO.getId() != null) {
			RightEntity oldRightEntity = rightRepository.findOne(rightDTO.getId());
			rightEntity = rightConverter.toEntity(rightDTO, oldRightEntity);
		} else {
			rightEntity = rightConverter.toEntity(rightDTO);
		}	

		rightEntity = rightRepository.save(rightEntity);
		return rightConverter.toDTO(rightEntity);
	}
	
	private String createSearchQuery(String rightName, String code) {
		if(rightName == null) rightName = "";
		String query = "select * from rights where LOWER(right_name) LIKE '%"
				+ rightName.toLowerCase() + "%'";
		if(code != null) {
			query += " and code LIKE '%"+ code +"%'";
		}

		return query;
	}
}
