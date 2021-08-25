package com.web_service.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web_service.converter.RequestConvertor;
import com.web_service.dto.RequestDTO;
import com.web_service.entity.AddColumnDetailEntity;
import com.web_service.entity.AddColumnTableRequestEntity;
import com.web_service.entity.CurrentTableSchemaEntity;
import com.web_service.entity.RequestEntity;
import com.web_service.entity.SyncTableRequestEntity;
import com.web_service.entity.TableEntity;
import com.web_service.repository.AddColumnDetailRepository;
import com.web_service.repository.AddColumnTableRequestRepository;
import com.web_service.repository.CurrentTableSchemaRepository;
import com.web_service.repository.RequestRepository;
import com.web_service.repository.SyncTableRequestRepository;
import com.web_service.repository.TableRepository;
import com.web_service.services.IRequestService;

@Service
public class RequestService implements IRequestService {
	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private RequestConvertor requestConvertor;
		
	@Autowired
	private TableRepository tableRepository;
	
	@Autowired
	private SyncTableRequestRepository syncTableRequestRepository;
	
	@Autowired
	private AddColumnTableRequestRepository addColumnTableRequestRepository;
	
	@Autowired
	private CurrentTableSchemaRepository currentTableSchemaRepository;
	
	@Autowired
	private AddColumnDetailRepository addColumnDetailRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<RequestDTO> findAll(String requestType, String status, String approvedBy, int page, int limit) {
		//create search query
		String query = createSearchQuery(requestType, status, approvedBy);
		
		//get request
		@SuppressWarnings("unchecked")
		List<RequestEntity> requestEntities = em.createNativeQuery(query, RequestEntity.class)
				.setFirstResult((page - 1) * limit)
				.setMaxResults(limit)
				.getResultList();
		List<RequestDTO> results = new ArrayList<>();
		for (RequestEntity item: requestEntities) {
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
	@Transactional
	public RequestDTO create(RequestDTO requestDTO) {
		//create request
		RequestEntity requestEntity = new RequestEntity();
		requestEntity = requestConvertor.toEntity(requestDTO);
		requestEntity = requestRepository.save(requestEntity);
		
		if(requestEntity.getId() != null) {
			//create synctable request
			if(requestDTO.getRequestType().equals("SyncTable")) {			
				createSyncTableRequest(requestDTO, requestEntity);
			}
			if(requestDTO.getRequestType().equals("AddColumn")) {			
				createAddColumnRequest(requestDTO, requestEntity);
			} 
			
		}
		return requestConvertor.toDTO(requestEntity);
	}
	
	@Override
	@Transactional
	public RequestDTO update(RequestDTO requestDTO) {
		//update request
		RequestEntity requestEntity = new RequestEntity();
		RequestEntity oldreRequestEntity = requestRepository.findOne(requestDTO.getId());
		requestEntity = requestConvertor.toEntity(requestDTO, oldreRequestEntity);
	
		return requestConvertor.toDTO(requestEntity);
	}

	@Override
	public void delete(long id) {
		requestRepository.delete(id);
	}
	
	private void createSyncTableRequest(RequestDTO requestDTO, RequestEntity requestEntity) {
		//create synctable request
		SyncTableRequestEntity syncTableRequestEntity = new SyncTableRequestEntity();
		TableEntity tableEntity = getTable(requestDTO.getTableId());
		
		syncTableRequestEntity.setIsAll(requestDTO.isAll());
		syncTableRequestEntity.setFromDate(requestDTO.getFromDate());
		syncTableRequestEntity.setToDate(requestDTO.getToDate());
		syncTableRequestEntity.setRequest(requestEntity);
		syncTableRequestEntity.setTableInfo(tableEntity);
		syncTableRequestEntity.setIdentityId(requestDTO.getIdentityId());
		syncTableRequestEntity.setPartitionBy(requestDTO.getPartitionBy());
		
		syncTableRequestRepository.save(syncTableRequestEntity);
	}
	
	private TableEntity getTable(long id) {
		TableEntity tableEntity = tableRepository.findOne(id);
		
		return tableEntity;
	}
	
	private void createAddColumnRequest(RequestDTO requestDTO, RequestEntity requestEntity) {
		AddColumnTableRequestEntity addColumnTableRequestEntity = new AddColumnTableRequestEntity();
		
		addColumnTableRequestEntity.setRequestAddColumn(requestEntity);
		addColumnTableRequestEntity.setTableInfo(getTable(requestDTO.getTableId()));
		
		addColumnTableRequestEntity = addColumnTableRequestRepository.save(addColumnTableRequestEntity);
		
		for(Long rowId : requestDTO.getColumnIds()) {
			AddColumnDetailEntity addColumnDetailEntity = new AddColumnDetailEntity();
			addColumnDetailEntity.setAddColumnTableRequest(addColumnTableRequestEntity);
			
			CurrentTableSchemaEntity currentTableSchemaEntity = currentTableSchemaRepository.findOne(rowId);
			addColumnDetailEntity.setCurrentTableSchema(currentTableSchemaEntity);
			
			addColumnDetailRepository.save(addColumnDetailEntity);
			
		};
	}
	
	private String createSearchQuery(String requestType, String status, String approvedBy) {
		if(requestType == null) requestType = "";
		String query = "select * from request where LOWER(request_type) LIKE '%"
				+ requestType.toLowerCase() + "%' and deleted = false";
		if(status != null) {
			query += " and status LIKE '%"+ status +"%'";
		}
		
		if(approvedBy != null) {
			query += " and approved_by LIKE '%"+ approvedBy +"%'" ;
		}

		return query;
	}
	
	@Override
	public int totalItemSearch(String requestType, String status, String approvedBy) {
		String query = createSearchQuery(requestType, status, approvedBy);

		@SuppressWarnings("unchecked")
		List<RequestEntity> requestEntities = em
				.createNativeQuery(query, RequestEntity.class).getResultList();

		return requestEntities.size();
	}
}
