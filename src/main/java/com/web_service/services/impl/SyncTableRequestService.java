
package com.web_service.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web_service.converter.SyncTableConverter;
import com.web_service.dto.ServerInfoDTO;
import com.web_service.dto.SyncTableRequestDTO;
import com.web_service.entity.RequestEntity;
import com.web_service.entity.ServerInfoEntity;
import com.web_service.entity.SyncTableRequestEntity;
import com.web_service.entity.TableEntity;
import com.web_service.repository.RequestRepository;
import com.web_service.repository.SyncTableRequestRepository;
import com.web_service.repository.TableRepository;
import com.web_service.services.ISyncTableRequestService;

@Service
public class SyncTableRequestService implements ISyncTableRequestService{
	@Autowired
	private SyncTableRequestRepository syncTableRequestRepository;
	
	@Autowired
	private SyncTableConverter syncTableConverter;

	@Override
	public List<SyncTableRequestDTO> findAll() {
		List<SyncTableRequestDTO> results = new ArrayList<>();
		List<SyncTableRequestEntity> entities = syncTableRequestRepository.findAll();
		for (SyncTableRequestEntity item: entities) {
			SyncTableRequestDTO syncTableRequestDTO = syncTableConverter.toDTO(item);
			results.add(syncTableRequestDTO);
		}
		return results;
	}
}
