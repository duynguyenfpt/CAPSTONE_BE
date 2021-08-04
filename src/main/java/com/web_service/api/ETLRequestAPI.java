package com.web_service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.web_service.api.output.ObjectOuput;
import com.web_service.entity.ETLEntity;
import com.web_service.services.IETLService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
public class ETLRequestAPI {
	@Autowired
	private IETLService etlService;
	
	@GetMapping(value = "/api/request/{request_id}/etl_requests")
	public ResponseEntity<ObjectOuput<ETLEntity>> showJob(@PathVariable("request_id") Integer requestId) {
		ObjectOuput<ETLEntity> result = new ObjectOuput<ETLEntity>();

		try {
			ETLEntity etlEntity =  etlService.getResult(requestId);
			if(etlEntity != null) {
				result.setMessage("Excute query successfully");
				result.setData(etlEntity);
				result.setCode("200");
				
				return new ResponseEntity<ObjectOuput<ETLEntity>>(result, HttpStatus.OK);
			}else {
				result.setMessage("Executing query ...");
				result.setData(etlEntity);
				result.setCode("200");
				
				return new ResponseEntity<ObjectOuput<ETLEntity>>(result, HttpStatus.OK);
			}
		}catch (NullPointerException e) {
			result.setMessage("Not found record");
			result.setCode("404");
			
			return new ResponseEntity<ObjectOuput<ETLEntity>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setMessage("Can not get data");
			result.setCode("500");
			
			return new ResponseEntity<ObjectOuput<ETLEntity>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
}
