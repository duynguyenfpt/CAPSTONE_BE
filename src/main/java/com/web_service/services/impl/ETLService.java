package com.web_service.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.web_service.converter.ETLRequestConverter;
import com.web_service.dto.AccountDTO;
import com.web_service.dto.ContentETLRequestDTO;
import com.web_service.dto.DatabaseInfoDTO;
import com.web_service.dto.ETLRequestDTO;
import com.web_service.dto.ShareETLRequestDTO;
import com.web_service.entity.AccountEntity;
import com.web_service.entity.DatabaseInfoEntity;
import com.web_service.entity.ETLEntity;
import com.web_service.entity.JobEntity;
import com.web_service.entity.RequestEntity;
import com.web_service.entity.ServerInfoEntity;
import com.web_service.hdfs.HdfsService;
import com.web_service.repository.AccountRepository;
import com.web_service.repository.ETLRequestRepository;
import com.web_service.repository.JobRepository;
import com.web_service.repository.RequestRepository;
import com.web_service.services.IETLService;

@Service
public class ETLService implements IETLService{
	@Autowired
	ETLRequestRepository etlRequestRepository;
	
	@Autowired
	ETLRequestConverter etlRequestConverter;
	
	@Autowired
	RequestRepository requestRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	JobRepository jobRepository;

	@Override
	public ContentETLRequestDTO getResult(Long requestId) {
		HdfsService hdfsService = new HdfsService();

		ContentETLRequestDTO contentETLRequestDTO = new ContentETLRequestDTO();
		ETLEntity etlEntity = etlRequestRepository.findByRequestId(requestId);
		contentETLRequestDTO.setContent("");
		
		if(etlEntity.getStatus().equals("pending")){
			contentETLRequestDTO.setContent("Executing query ...");
		}
		
		if(etlEntity.getStatus().equals("failed")){
			contentETLRequestDTO.setContent(etlEntity.getMessageFail());
		}
		
		if(etlEntity.getStatus().equals("successed")){
			if(etlEntity.getResultPath() == null || etlEntity.getResultPath().isEmpty()) {
				return contentETLRequestDTO;
			}
			
			try {
				String content = hdfsService.readFileFromHDFS(etlEntity.getResultPath());
				if(content != null) contentETLRequestDTO.setContent(content);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		contentETLRequestDTO.setStatus(etlEntity.getStatus());
		
		return contentETLRequestDTO;
	}

	@Override
	@Transactional
	public ETLRequestDTO save(ETLRequestDTO etlRequestDTO) {
		ETLEntity etlEntity = new ETLEntity();
		if (etlRequestDTO.getId() != null) {
			ETLEntity oldEtlEntity = etlRequestRepository.findOne(etlRequestDTO.getId());
			etlEntity = etlRequestConverter.toEntity(etlRequestDTO, oldEtlEntity);
		} else {
			final int MAX_RETRIES = 10;
			
			RequestEntity requestEntity = new RequestEntity();
			requestEntity.setRequestType("ETLRequest");
			requestEntity =  requestRepository.save(requestEntity);
			
			JobEntity jobEntity = new JobEntity();
			jobEntity.setMaxRetries(MAX_RETRIES);
			jobEntity.setRequest(requestEntity);
			jobEntity.setExecutedBy(getCurrentAccount());
			jobEntity.setActive(true);
			jobRepository.save(jobEntity);
			
			etlEntity = etlRequestConverter.toEntity(etlRequestDTO);
			etlEntity.setRequest(requestEntity);
		}
		etlEntity = etlRequestRepository.save(etlEntity);
		return etlRequestConverter.toDTO(etlEntity);
	}

	@Override
	public void delete(long id) {
		etlRequestRepository.delete(id);
	}

	@Override
	public List<ETLRequestDTO> findAll(Pageable pageable) {
		List<ETLRequestDTO> results = new ArrayList<>();
		List<ETLEntity> entities;
		entities = etlRequestRepository.findAll(pageable).getContent();
		
		for (ETLEntity item: entities) {
			ETLRequestDTO etlRequestDTO = etlRequestConverter.toDTO(item);
			results.add(etlRequestDTO);
		}
		return results;
	}

	@Override
	public int totalItem() {
		return (int) etlRequestRepository.count();
	}

	@Override
	public ETLRequestDTO getById(long id) {
		ETLEntity etlEntity = etlRequestRepository.findOne(id);
		ETLRequestDTO etlRequestDTO = etlRequestConverter.toDTO(etlEntity);
		return etlRequestDTO;
	}

	@Override
	public void shareETLRequest(ShareETLRequestDTO shareETLRequestDTO) {
		ETLEntity etlEntity = etlRequestRepository.findOne(shareETLRequestDTO.getRequestId());
		
		for (Long accountId : shareETLRequestDTO.getAccountIds()) {
			AccountEntity accountEntity = accountRepository.findOne(accountId);
			
			etlEntity.getAccounts().add(accountEntity);
		}
		etlRequestRepository.save(etlEntity);
	}

	@Override
	public List<ETLRequestDTO> getShareETLRequest(Long accountId) {
		List<ETLRequestDTO> results = new ArrayList<>();
		AccountEntity accountEntity = accountRepository.findOne(accountId);
		List<ETLEntity> entities = accountEntity.getEtlRequest();
		
		for (ETLEntity item: entities) {
			ETLRequestDTO etlRequestDTO = etlRequestConverter.toDTO(item);
			results.add(etlRequestDTO);
		}
		return results;
	}
	
	private AccountEntity getCurrentAccount() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		AccountEntity accountEntity = accountRepository.findByUsername(auth.getName());
		
		return accountEntity;
	}

	@Override
	public boolean downloadCSV(Long requestId) {
		HdfsService hdfsService = new HdfsService();
		ETLEntity etlEntity = etlRequestRepository.findByRequestId(requestId);
		
		if(etlEntity.getResultPath() == null || etlEntity.getResultPath().isEmpty()) {
			return false;
		}
		try {
			hdfsService.getFile(etlEntity.getResultPath());
		} catch (IOException e) {
			return false;
		}
		
		return true;
	}
}
