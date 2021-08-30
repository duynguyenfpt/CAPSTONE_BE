package com.web_service.api;

import java.util.Map;


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
import com.web_service.dto.AliasDTO;
import com.web_service.dto.BooleanDTO;
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
			int totalPage = (int) Math.ceil((double) (databaseInfoService.totalItem(keyword)) / limit);
			int totalItem = databaseInfoService.totalItem(keyword);
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
			DatabaseInfoDTO databaseInfoDTO = databaseInfoService.getById(id);
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
		try {
			boolean isExistAlias = databaseInfoService.isExistAlias(model);
			
			if(isExistAlias) {
				result.setMessage("Alias exist");
				result.setCode("422");
				
				return new ResponseEntity<ObjectOuput<DatabaseInfoDTO>>(result, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			
			boolean checkExistPort = databaseInfoService.checkExistPort(model);
			
			if(checkExistPort) {
				result.setMessage("Port exist");
				result.setCode("422");
				
				return new ResponseEntity<ObjectOuput<DatabaseInfoDTO>>(result, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			
			DatabaseInfoDTO databaseInfoDTO = databaseInfoService.save(model);
			if(databaseInfoDTO != null) {
				result.setData(databaseInfoDTO);
				result.setMessage("Create database info successfully");
				result.setCode("201");
				
				return new ResponseEntity<ObjectOuput<DatabaseInfoDTO>>(result, HttpStatus.CREATED);
			} else {
				result.setData(databaseInfoDTO);
				result.setMessage("Database not exist");
				result.setCode("400");
				
				return new ResponseEntity<ObjectOuput<DatabaseInfoDTO>>(result, HttpStatus.BAD_REQUEST);
			}
			
		}catch (Exception e) {
			result.setCode("500");
			result.setMessage("Can not create database info");
			return new ResponseEntity<ObjectOuput<DatabaseInfoDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "/api/database_infors/{id}")
	public ResponseEntity<ObjectOuput<DatabaseInfoDTO>> updateDatabaseInfo(@RequestBody DatabaseInfoDTO model, @PathVariable("id") long id) {
		ObjectOuput<DatabaseInfoDTO> result = new ObjectOuput<DatabaseInfoDTO>();
		try {
			model.setId(id);
			
			boolean isExistAlias = databaseInfoService.isExistAlias(model);
			
			if(isExistAlias) {
				result.setMessage("Alias exist");
				result.setCode("422");
				
				return new ResponseEntity<ObjectOuput<DatabaseInfoDTO>>(result, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			
			boolean checkExistPort = databaseInfoService.checkExistPort(model);
			
			if(checkExistPort) {
				result.setMessage("Port exist");
				result.setCode("422");
				
				return new ResponseEntity<ObjectOuput<DatabaseInfoDTO>>(result, HttpStatus.UNPROCESSABLE_ENTITY);
			} else {
				databaseInfoService.save(model);
				result.setCode("200");
				result.setMessage("Update database info successfully");
				
				return new ResponseEntity<ObjectOuput<DatabaseInfoDTO>>(result, HttpStatus.OK);
			}
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
		try {
			databaseInfoService.delete(id);
			result.setCode("200");
			
			return new ResponseEntity<ObjectOuput<DatabaseInfoDTO>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setMessage("Not found record");
			result.setCode("404");
			
			return new ResponseEntity<ObjectOuput<DatabaseInfoDTO>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setMessage("Can not delete data");
			result.setCode("500");
			
			return new ResponseEntity<ObjectOuput<DatabaseInfoDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/api/database_infors/test_connection")
	public ResponseEntity<ObjectOuput<BooleanDTO>> checkingConnection(@RequestBody DatabaseInfoDTO model) {
		ObjectOuput<BooleanDTO> result = new ObjectOuput<BooleanDTO>();
		try {
			BooleanDTO booleanDTO = new BooleanDTO();
			
			booleanDTO.setSuccess(databaseInfoService.trackingConnection(model));
			result.setCode("200");
			result.setData(booleanDTO);
			result.setMessage("Tracking connection successfully");
			
			return new ResponseEntity<ObjectOuput<BooleanDTO>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setMessage("Not found server");
			result.setCode("404");
			
			return new ResponseEntity<ObjectOuput<BooleanDTO>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setMessage("Can not tracking connection");
			result.setCode("500");
			
			return new ResponseEntity<ObjectOuput<BooleanDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/api/database_infors/get_alias")
	public ResponseEntity<ObjectOuput<AliasDTO>> getAlias(@RequestBody DatabaseInfoDTO model) {
		ObjectOuput<AliasDTO> result = new ObjectOuput<AliasDTO>();
		try {
			AliasDTO aliasDTO = new AliasDTO();
			
			aliasDTO.setAlias(databaseInfoService.getAlias(model));
			result.setCode("200");
			result.setData(aliasDTO);
			result.setMessage("Get data successfully");
			
			return new ResponseEntity<ObjectOuput<AliasDTO>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setMessage("Not found server");
			result.setCode("404");
			
			return new ResponseEntity<ObjectOuput<AliasDTO>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setMessage("Can not get data");
			result.setCode("500");
			
			return new ResponseEntity<ObjectOuput<AliasDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
