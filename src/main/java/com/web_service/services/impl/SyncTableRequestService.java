//package com.web_service.services.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.web_service.converter.SyncTableConverter;
//import com.web_service.dto.SyncTableRequestDTO;
//import com.web_service.entity.RequestEntity;
//import com.web_service.entity.SyncTableRequestEntity;
//import com.web_service.entity.TableEntity;
//import com.web_service.repository.RequestRepository;
//import com.web_service.repository.SyncTableRequestRepository;
//import com.web_service.repository.TableRepository;
//import com.web_service.services.ISyncTableRequestService;
//
//@Service
//public class SyncTableRequestService implements ISyncTableRequestService{
//	@Autowired
//	private SyncTableRequestRepository syncTableRequestRepository;
//	
//	@Autowired
//	private TableRepository tableRepository;
//	
//	@Autowired
//	private RequestRepository requestRepository;
//	
//	@Autowired
//	private SyncTableConverter syncTableConverter;
//
//	@Override
//	public SyncTableRequestDTO save(SyncTableRequestDTO syncTableRequestDTO) {
//		
//		SyncTableRequestEntity syncTableRequestEntity = new SyncTableRequestEntity();
//		if (syncTableRequestDTO.getId() != null) {
//			SyncTableRequestEntity oldSyncTableRequestEntity = syncTableRequestRepository.findOne(syncTableRequestDTO.getId());
//			syncTableRequestEntity = syncTableConverter.toEntity(syncTableRequestDTO, oldSyncTableRequestEntity);
//		} else {
//			syncTableRequestEntity = syncTableConverter.toEntity(syncTableRequestDTO);
//		}
//			
//		TableEntity tableEntity = tableRepository.findOne(syncTableRequestDTO.getTableId());
//		
//		syncTableRequestEntity.setRequest(requestEntity);
//		syncTableRequestEntity.setTableInfo(tableEntity);
//		
//		syncTableRequestEntity = syncTableRequestRepository.save(syncTableRequestEntity);
//		
//		return syncTableConverter.toDTO(syncTableRequestEntity);
//	}
//
//}
