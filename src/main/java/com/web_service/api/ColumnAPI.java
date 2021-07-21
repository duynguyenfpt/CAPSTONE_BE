package com.web_service.api;

import java.util.ArrayList;
import java.util.List;

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
import com.web_service.api.output.PagingOutput;
import com.web_service.dto.CurrentTableSchemaDTO;
import com.web_service.services.IColumnService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
public class ColumnAPI {
	@Autowired
	IColumnService columnService;
	
	@GetMapping(value = "/api/tables/{table_id}/columns")
	public ResponseEntity<ListObjOutput<String>> showCurrentTableSchema(@PathVariable("table_id") long tableId) {
		
		ListObjOutput<String> result = new ListObjOutput<String>();
		try {
			List<String> columns =  columnService.getColumnByTable(tableId);
			
			result.setData(columns);
			result.setCode("200");
			result.setMessage("Get column successfully");
			return new ResponseEntity<ListObjOutput<String>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setCode("404");
			result.setMessage("Not found");
			return new ResponseEntity<ListObjOutput<String>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setCode("500");
			result.setMessage("Can not get columns");
			return new ResponseEntity<ListObjOutput<String>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
}
