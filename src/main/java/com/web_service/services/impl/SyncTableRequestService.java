package com.web_service.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web_service.converter.SyncTableConverter;
import com.web_service.dto.SyncTableRequestDTO;
import com.web_service.entity.SyncTableRequestEntity;
import com.web_service.repository.SyncTableRequestRepository;
import com.web_service.services.ISyncTableRequest;

@Service
public class SyncTableRequestService implements ISyncTableRequest{
	@Autowired
	private SyncTableRequestRepository syncTableRequestRepository;
	
	@Autowired
	private SyncTableConverter syncTableConverter;

	@Override
	public SyncTableRequestDTO save(SyncTableRequestDTO syncTableRequestDTO) {
		
		SyncTableRequestEntity syncTableRequestEntity = new SyncTableRequestEntity();
		if (syncTableRequestDTO.getId() != null) {
			SyncTableRequestEntity oldSyncTableRequestEntity = syncTableRequestRepository.findOne(syncTableRequestDTO.getId());
			syncTableRequestEntity = syncTableConverter.toEntity(syncTableRequestDTO, oldSyncTableRequestEntity);
		} else {
			syncTableRequestEntity = syncTableConverter.toEntity(syncTableRequestDTO);
		}

		syncTableRequestEntity = syncTableRequestRepository.save(syncTableRequestEntity);
		
		return syncTableConverter.toDTO(syncTableRequestEntity);
	}

}
