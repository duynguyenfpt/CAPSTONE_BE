package com.web_service.api;

import java.util.Map;

import javax.persistence.Cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web_service.api.output.ListObjOutput;
import com.web_service.api.output.ObjectOuput;
import com.web_service.api.output.PagingOutput;
import com.web_service.dto.CurrentTableSchemaDTO;
import com.web_service.dto.DatabaseInfoDTO;
import com.web_service.services.IDatabaseInfoService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
public class DatabaseInfoAPI {
	@Autowired
	private IDatabaseInfoService databaseInfoService;

	@GetMapping(value = "/api/database_infors")
	public ResponseEntity<ListObjOutput<DatabaseInfoDTO>> showDatabaseInfors(@RequestParam("page") int page,
								@RequestParam("limit") int limit, @RequestParam(required = false) String keyword) {
		
		if (keyword == null || keyword.isEmpty()) keyword = "";
		
		ListObjOutput<DatabaseInfoDTO> result = new ListObjOutput<DatabaseInfoDTO>();
		try {
			Pageable pageable = new PageRequest(page - 1, limit);
			result.setData(databaseInfoService.findAll(pageable, keyword));
			int totalPage = (int) Math.ceil((double) (databaseInfoService.totalItem()) / limit);
			int totalItem = databaseInfoService.totalItem();
			result.setMetaData(new PagingOutput(totalPage, totalItem));
			result.setCode("200");

			return new ResponseEntity<ListObjOutput<DatabaseInfoDTO>>(result, HttpStatus.OK);
		}catch (Exception e) {
			result.setCode("500");
			result.setMessage("Can not get data");
			
			return new ResponseEntity<ListObjOutput<DatabaseInfoDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping(value = "/api/database_infors/{id}")
	public ResponseEntity<ObjectOuput<DatabaseInfoDTO>> showDatabaseInfo(@PathVariable("id") long id) {
		ObjectOuput<DatabaseInfoDTO> result = new ObjectOuput<DatabaseInfoDTO>();
		try{
			DatabaseInfoDTO databaseInfoDTO =  databaseInfoService.getById(id);
			result.setData(databaseInfoDTO);
			result.setCode("200");
			return new ResponseEntity<ObjectOuput<DatabaseInfoDTO>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setMessage("Not found record");
			result.setCode("404");
			return new ResponseEntity<ObjectOuput<DatabaseInfoDTO>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setCode("500");
			result.setMessage("Can not get data");
			
			return new ResponseEntity<ObjectOuput<DatabaseInfoDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping(value = "/api/database_infors")
	public ResponseEntity<ObjectOuput<DatabaseInfoDTO>> createDatabaseInfo(@RequestBody DatabaseInfoDTO model) {
		ObjectOuput<DatabaseInfoDTO> result = new ObjectOuput<DatabaseInfoDTO>();
			result.setData(databaseInfoService.save(model));
			result.setCode("201");
			
			return new ResponseEntity<ObjectOuput<DatabaseInfoDTO>>(result, HttpStatus.CREATED);
		
		
	}

	@PutMapping(value = "/api/database_infors/{id}")
	public ResponseEntity<ObjectOuput<DatabaseInfoDTO>> updateDatabaseInfo(@RequestBody DatabaseInfoDTO model, @PathVariable("id") long id) {
		ObjectOuput<DatabaseInfoDTO> result = new ObjectOuput<DatabaseInfoDTO>();
		try {
			model.setId(id);
			databaseInfoService.save(model);
			result.setCode("200");
			
			return new ResponseEntity<ObjectOuput<DatabaseInfoDTO>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setMessage("Not found record");
			result.setCode("404");
			
			return new ResponseEntity<ObjectOuput<DatabaseInfoDTO>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setMessage("Can not update data");
			result.setCode("500");
			
			return new ResponseEntity<ObjectOuput<DatabaseInfoDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@DeleteMapping(value = "/api/database_infors/{id}")
	public ResponseEntity<ObjectOuput<DatabaseInfoDTO>> deleteDatabaseInfo(@PathVariable("id") long id) {
		ObjectOuput<DatabaseInfoDTO> result = new ObjectOuput<DatabaseInfoDTO>();

			databaseInfoService.delete(id);
			result.setCode("200");
			
			return new ResponseEntity<ObjectOuput<DatabaseInfoDTO>>(result, HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/api/database_infors/test_connection")
	public ResponseEntity<Map<String,Object>> checkingConnection(@RequestBody DatabaseInfoDTO model) {
		 return databaseInfoService.trackingConnection(model);
	}
}
