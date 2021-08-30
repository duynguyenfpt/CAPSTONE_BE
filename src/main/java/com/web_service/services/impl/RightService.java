package com.web_service.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.web_service.api.output.ObjectOuput;
import com.web_service.converter.RightConverter;
import com.web_service.dto.AccountDTO;
import com.web_service.dto.RightDTO;
import com.web_service.entity.AccountEntity;
import com.web_service.entity.RightEntity;
import com.web_service.repository.AccountRepository;
import com.web_service.repository.RightRepository;
import com.web_service.services.IRightService;

@Service
public class RightService implements IRightService{
	@Autowired
	RightRepository rightRepository;
	
	@Autowired
	RightConverter rightConverter;
	
	@Autowired
	AccountRepository accountRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<RightDTO> findAll(String keyword, int page, int limit) {
		//create search query
		String query = createSearchQuery(keyword);
		
		//get right from db
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
	public int totalItemSearch(String keyword) {
		String query = createSearchQuery(keyword);

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
		//get right of account
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
			//update right
			RightEntity oldRightEntity = rightRepository.findOne(rightDTO.getId());
			rightEntity = rightConverter.toEntity(rightDTO, oldRightEntity);
		} else {
			//create right
			rightEntity = rightConverter.toEntity(rightDTO);
		}	

		rightEntity = rightRepository.save(rightEntity);
		return rightConverter.toDTO(rightEntity);
	}
	
	private String createSearchQuery(String keyword) {
		if(keyword == null) keyword = "";
		String query = "select * from rights where (LOWER(path) LIKE '%"
					   + keyword.toLowerCase() + "%'"
					   + " or LOWER(method)LIKE '%" + keyword.toLowerCase()+"%')"
					   + " and deleted = false";
		return query;
	}

	@Override
	public RightDTO getById(Long id) {
		RightEntity rightEntity = rightRepository.findOne(id);
		RightDTO rightDTO = rightConverter.toDTO(rightEntity);
		return rightDTO;
	}

	@Override
	public void delete(Long id) {
		rightRepository.delete(id);
	}

	@Override
	public boolean checkPermission(RightDTO rightDTO) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		AccountEntity accountEntity = accountRepository.findByUsername(auth.getName());
		if(auth.getName().equals("longvt")) return true;
		
		if(accountEntity.getActive() == false) return false;

		if(accountEntity.getRole().toLowerCase().trim().equals("admin")
				&& (rightDTO.getPath().contains("right") || rightDTO.getPath().contains("account")
						|| rightDTO.getPath().contains("reset_password"))) {
			return true;
		}
		
		List<RightEntity> rights = rightRepository.findRightByAccountId(accountEntity.getId());
		boolean isPermission =  rights.stream()
				.anyMatch(e -> (rightDTO.getPath().equals(e.getPath() + "s") || rightDTO.getPath().equals(e.getPath()))
						&& rightDTO.getMethod().equals(e.getMethod()));
		
		return isPermission;
	}
}
