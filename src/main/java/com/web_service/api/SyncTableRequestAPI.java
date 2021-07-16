package com.web_service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web_service.api.output.ListObjOutput;
import com.web_service.api.output.ObjectOuput;
import com.web_service.api.output.PagingOutput;
import com.web_service.dto.ServerInfoDTO;
import com.web_service.dto.SyncTableRequestDTO;
import com.web_service.services.ISyncTableRequestService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SyncTableRequestAPI {
	@Autowired
	private ISyncTableRequestService syncTableRequestService;
	
	@GetMapping(value = "/api/sync_table_requests")
	public ResponseEntity<ListObjOutput<SyncTableRequestDTO>> showServerInfors() {
		
		ListObjOutput<SyncTableRequestDTO> result = new ListObjOutput<SyncTableRequestDTO>();
		result.setData(syncTableRequestService.findAll());
		result.setCode("200");

		return new ResponseEntity<ListObjOutput<SyncTableRequestDTO>>(result, HttpStatus.OK);
	}

}
