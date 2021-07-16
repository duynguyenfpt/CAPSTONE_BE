package com.web_service.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web_service.dto.AddColumnTableRequestDTO;
import com.web_service.entity.AddColumnDetailEntity;
import com.web_service.entity.AddColumnTableRequestEntity;
import com.web_service.entity.CurrentTableSchemaEntity;
import com.web_service.entity.RequestEntity;
import com.web_service.entity.TableEntity;
import com.web_service.repository.AddColumnDetailRepository;
import com.web_service.repository.AddColumnTableRequestRepository;
import com.web_service.repository.CurrentTableSchemaRepository;
import com.web_service.repository.RequestRepository;
import com.web_service.repository.TableRepository;
import com.web_service.services.IAddColumnTableRequestService;

@Service
public class AddColumnTableRequestService implements IAddColumnTableRequestService {
	
	@Autowired
	private AddColumnTableRequestRepository addColumnTableRequestRepository;
	
	@Autowired
	private AddColumnDetailRepository addColumnDetailRepository;
	
	@Autowired
	private CurrentTableSchemaRepository currentTableSchemaRepository ;
	
	@Autowired
	private TableRepository tableRepository ;
	
	@Autowired
	private RequestRepository requestRepository;
	
	@Override
	public AddColumnTableRequestEntity save(AddColumnTableRequestDTO addColumnTableRequestDTO) {
		AddColumnTableRequestEntity addColumnTableRequestEntity = new AddColumnTableRequestEntity();
		
		RequestEntity requestEntity = requestRepository.findOne(addColumnTableRequestDTO.getRequestTypeId());
		addColumnTableRequestEntity.setRequestAddColumn(requestEntity);
		
		TableEntity tableEntity = tableRepository.findOne(addColumnTableRequestDTO.getTableId());
		addColumnTableRequestEntity.setTableInfo(tableEntity);
		
		addColumnTableRequestEntity = addColumnTableRequestRepository.save(addColumnTableRequestEntity);
		
		for(Long rowId : addColumnTableRequestDTO.getRowIds()) {
			AddColumnDetailEntity addColumnDetailEntity = new AddColumnDetailEntity();
			addColumnDetailEntity.setAddColumnTableRequest(addColumnTableRequestEntity);
			
			CurrentTableSchemaEntity currentTableSchemaEntity = currentTableSchemaRepository.findOne(rowId);
			addColumnDetailEntity.setCurrentTableSchema(currentTableSchemaEntity);
			
			addColumnDetailRepository.save(addColumnDetailEntity);
			
		}
		
		return addColumnTableRequestEntity;
	}


}
