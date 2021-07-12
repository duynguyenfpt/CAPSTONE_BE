//package com.web_service.api;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.web_service.api.output.ObjectOuput;
//import com.web_service.dto.SyncTableRequestDTO;
//import com.web_service.services.ISyncTableRequestService;
//
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//public class SyncTableRequestAPI {
//	@Autowired
//	private ISyncTableRequestService syncTableRequest;
//	
//	@PostMapping(value = "/api/sync_table_requests")
//	public ResponseEntity<ObjectOuput<SyncTableRequestDTO>> createSyncTableRequest(@RequestBody SyncTableRequestDTO model) {
//		syncTableRequest.save(model);
//		
//		ObjectOuput<SyncTableRequestDTO> result = new ObjectOuput<SyncTableRequestDTO>();
//		result.setCode("201");
//		
//		return new ResponseEntity<ObjectOuput<SyncTableRequestDTO>>(result, HttpStatus.CREATED);		
//	}
//
//}
