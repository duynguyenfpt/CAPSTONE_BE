package com.web_service.services;

import com.web_service.dto.AddColumnTableRequestDTO;
import com.web_service.entity.AddColumnTableRequestEntity;

public interface IAddColumnTableRequestService {
	AddColumnTableRequestEntity save(AddColumnTableRequestDTO addColumnTableRequestDTO);
//	NewDTO update(NewDTO newDTO);
}
