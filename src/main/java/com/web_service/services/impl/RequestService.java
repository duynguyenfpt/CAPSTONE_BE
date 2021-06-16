package com.web_service.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web_service.converter.RequestConvertor;
import com.web_service.dto.RequestDTO;
import com.web_service.entity.AccountEntity;
import com.web_service.entity.DatabaseInfoEntity;
import com.web_service.entity.RequestEntity;
import com.web_service.entity.ServerInfoEntity;
import com.web_service.repository.AccountRepository;
import com.web_service.repository.RequestRepository;
import com.web_service.services.IRequestService;

@Service
public class RequestService implements IRequestService {
	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private RequestConvertor requestConvertor;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public List<RequestDTO> findAll(Pageable pageable) {
		List<RequestDTO> results = new ArrayList<>();
		List<RequestEntity> entities = requestRepository.findAll(pageable).getContent();
		for (RequestEntity item: entities) {
			RequestDTO requestDTO = requestConvertor.toDTO(item);
			results.add(requestDTO);
		}
		return results;
	}

	@Override
	public int totalItem() {
		return (int) requestRepository.count();
	}

	@Override
	public RequestDTO getById(long id) {
		RequestEntity requestEntity = requestRepository.findOne(id);
		RequestDTO requestDTO = requestConvertor.toDTO(requestEntity);
		return requestDTO;
	}

	@Override
	public RequestDTO save(RequestDTO requestDTO) {
		RequestEntity requestEntity = new RequestEntity();
		if (requestDTO.getId() != null) {
			RequestEntity oldreRequestEntity = requestRepository.findOne(requestDTO.getId());
			requestEntity = requestConvertor.toEntity(requestDTO, oldreRequestEntity);
			
			if(requestDTO.getApprovedById() != null) {
				AccountEntity approvedBy =  accountRepository.findOne(requestDTO.getApprovedById());
				requestEntity.setApprovedBy(approvedBy);
			}
		} else {
			requestEntity = requestConvertor.toEntity(requestDTO);
			
			if(requestDTO.getCreatorId() != null) {
				AccountEntity creator =  accountRepository.findOne(requestDTO.getCreatorId());
				requestEntity.setCreator(creator);
			}
			
		}
			
		requestEntity = requestRepository.save(requestEntity);
		return requestConvertor.toDTO(requestEntity);
	}

	@Override
	public void delete(long id) {
		requestRepository.delete(id);
	}

}
