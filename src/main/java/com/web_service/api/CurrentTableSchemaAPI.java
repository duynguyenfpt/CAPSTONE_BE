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
import com.web_service.dto.CurrentTableSchemaDTO;
import com.web_service.services.ICurrentTableSchemaService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
public class CurrentTableSchemaAPI {
	@Autowired
	private ICurrentTableSchemaService currentTableSchemaService;

	@GetMapping(value = "/api/tables/{table_id}/current_table_schemas")
	public ResponseEntity<ListObjOutput<CurrentTableSchemaDTO>> showCurrentTableSchema(@RequestParam("page") int page,
								@RequestParam("limit") int limit,
								@PathVariable("table_id") long tableId) {
		
		ListObjOutput<CurrentTableSchemaDTO> result = new ListObjOutput<CurrentTableSchemaDTO>();
		Pageable pageable = new PageRequest(page - 1, limit);
		result.setData(currentTableSchemaService.findAll(pageable, tableId));
		int totalPage = (int) Math.ceil((double) (currentTableSchemaService.totalItem(tableId)) / limit);
		int totalItem = currentTableSchemaService.totalItem(tableId);
		result.setMetaData(new PagingOutput(totalPage, totalItem));
		result.setCode("200");

		return new ResponseEntity<ListObjOutput<CurrentTableSchemaDTO>>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/api/current_table_schemas/{id}")
	public ResponseEntity<ObjectOuput<CurrentTableSchemaDTO>> showCurrentTableSchema(@PathVariable("id") long id) {
		CurrentTableSchemaDTO currentTableSchemaDTO  = null;
		ObjectOuput<CurrentTableSchemaDTO> result =  new ObjectOuput<CurrentTableSchemaDTO>();
		try {
			currentTableSchemaDTO =  currentTableSchemaService.getById(id);
			result =  new ObjectOuput<CurrentTableSchemaDTO>();
			result.setData(currentTableSchemaDTO);
			result.setCode("200");
			return new ResponseEntity<ObjectOuput<CurrentTableSchemaDTO>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setCode("404");
			result.setMessage("Not found record");
			return new ResponseEntity<ObjectOuput<CurrentTableSchemaDTO>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setCode("500");
			result.setMessage("Can not get data");
			return new ResponseEntity<ObjectOuput<CurrentTableSchemaDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
}
