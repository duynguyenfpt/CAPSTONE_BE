package com.web_service.services;

import java.util.List;
import com.web_service.dto.SyncTableRequestDTO;

public interface ISyncTableRequestService {
	List<SyncTableRequestDTO> findAll();

}
