package com.web_service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web_service.api.output.ObjectOuput;
import com.web_service.dto.AddColumnTableRequestDTO;
import com.web_service.entity.AddColumnTableRequestEntity;
import com.web_service.services.IAddColumnTableRequestService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
public class AddColumTableRequestAPI {
	@Autowired
	private IAddColumnTableRequestService addColumnTableRequestService;
	
	@PostMapping(value = "/api/add_column_table_request")
	public ResponseEntity<ObjectOuput<AddColumnTableRequestEntity>> createAddColumnTableRequest(@RequestBody AddColumnTableRequestDTO model) {
		ObjectOuput<AddColumnTableRequestEntity> result = new ObjectOuput<AddColumnTableRequestEntity>();
		result.setData(addColumnTableRequestService.save(model));
		result.setCode("201");
		
		return new ResponseEntity<ObjectOuput<AddColumnTableRequestEntity>>(result, HttpStatus.CREATED);
	}
}
