package com.web_service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web_service.api.output.ListObjOutput;
import com.web_service.api.output.ObjectOuput;
import com.web_service.api.output.PagingOutput;
import com.web_service.dto.DatabaseInfoDTO;
import com.web_service.dto.JobDTO;
import com.web_service.dto.SchemaChangeHistoryDTO;
import com.web_service.services.ISchemaChangeHistoryService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
public class SchemaChangeHistoryAPI {
	@Autowired
	private ISchemaChangeHistoryService schemaChangeHistoryService;

	@GetMapping(value = "/api/schema_change_history")
	public ResponseEntity<ListObjOutput<SchemaChangeHistoryDTO>> showSchemaChangeHistory(@RequestParam("page") int page,
								@RequestParam("limit") int limit) {
		
		ListObjOutput<SchemaChangeHistoryDTO> result = new ListObjOutput<SchemaChangeHistoryDTO>();
		try {
			Pageable pageable = new PageRequest(page - 1, limit);
			result.setData(schemaChangeHistoryService.findAll(pageable));
			int totalPage = (int) Math.ceil((double) (schemaChangeHistoryService.totalItem()) / limit);
			int totalItem = schemaChangeHistoryService.totalItem();
			result.setMetaData(new PagingOutput(totalPage, totalItem));
			result.setCode("200");

			return new ResponseEntity<ListObjOutput<SchemaChangeHistoryDTO>>(result, HttpStatus.OK);
		}catch (Exception e) {
			result.setMessage("Can not get data");
			result.setCode("500");
			
			return new ResponseEntity<ListObjOutput<SchemaChangeHistoryDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/api/schema_change_history/search")
	public ResponseEntity<ListObjOutput<SchemaChangeHistoryDTO>> searchSchemaChangeHistory(@RequestParam("page") int page,
								@RequestParam("limit") int limit, @RequestParam(required = false) Long tableId,
								@RequestParam(required = false) Long databaseInfoId,
								@RequestParam(required = false) String typeChange) {
		
		ListObjOutput<SchemaChangeHistoryDTO> result = new ListObjOutput<SchemaChangeHistoryDTO>();
		try {
			result.setData(schemaChangeHistoryService.search(databaseInfoId, tableId, typeChange, page, limit));
			int totalPage = (int) Math.ceil((double) (schemaChangeHistoryService.totalItemSearch(databaseInfoId, tableId, typeChange)) / limit);
			int totalItem = schemaChangeHistoryService.totalItemSearch(databaseInfoId, tableId, typeChange);
			result.setMetaData(new PagingOutput(totalPage, totalItem));
			result.setCode("200");

			return new ResponseEntity<ListObjOutput<SchemaChangeHistoryDTO>>(result, HttpStatus.OK);
		}catch (Exception e) {
			result.setMessage("Can not get data");
			result.setCode("500");
			
			return new ResponseEntity<ListObjOutput<SchemaChangeHistoryDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping(value = "/api/schema_change_history/{id}")
	public ResponseEntity<ObjectOuput<SchemaChangeHistoryDTO>> showServerInfo(@PathVariable("id") long id) {
		ObjectOuput<SchemaChangeHistoryDTO> result = new ObjectOuput<SchemaChangeHistoryDTO>();
		try {
			SchemaChangeHistoryDTO schemaChangeHistoryDTO =  schemaChangeHistoryService.getById(id);
			result.setData(schemaChangeHistoryDTO);
			result.setCode("200");
			
			return new ResponseEntity<ObjectOuput<SchemaChangeHistoryDTO>>(result, HttpStatus.OK);	
		}catch (NullPointerException e) {
			result.setMessage("Not found record");
			result.setCode("404");
			
			return new ResponseEntity<ObjectOuput<SchemaChangeHistoryDTO>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setMessage("Can not get data");
			result.setCode("500");
			
			return new ResponseEntity<ObjectOuput<SchemaChangeHistoryDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
}
