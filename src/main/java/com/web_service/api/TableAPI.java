package com.web_service.api;

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
import com.web_service.dto.TableDTO;
import com.web_service.services.ITableService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TableAPI {
	@Autowired
	private ITableService tableService;

	@GetMapping(value = "api/tables")
	public ResponseEntity<ListObjOutput<TableDTO>> showTables(@RequestParam("page") int page,
								@RequestParam("limit") int limit) {
		ListObjOutput<TableDTO> result = new ListObjOutput<TableDTO>();
		Pageable pageable = new PageRequest(page - 1, limit);
		result.setData(tableService.findAll(pageable));
		int totalPage = (int) Math.ceil((double) (tableService.totalItem()) / limit);
		int totalItem = tableService.totalItem();
		result.setMetaData(new PagingOutput(totalPage, totalItem));
		result.setCode("200");
		
		return new ResponseEntity<ListObjOutput<TableDTO>>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "api/database_infors/{database_infor_id}/tables")
	public ResponseEntity<ListObjOutput<TableDTO>> showTablesByDatabaseInfor(@PathVariable("database_infor_id") long database_infor_id,
								@RequestParam("page") int page,
								@RequestParam("limit") int limit) {
		ListObjOutput<TableDTO> result = new ListObjOutput<TableDTO>();
		Pageable pageable = new PageRequest(page - 1, limit);
		result.setData(tableService.findByDatabaseInfoId(database_infor_id, pageable));
		int totalPage = (int) Math.ceil((double) (tableService.totalItemByDatabaseId(database_infor_id)) / limit);
		int totalItem = tableService.totalItemByDatabaseId(database_infor_id);
		result.setMetaData(new PagingOutput(totalPage, totalItem));
		
		return new ResponseEntity<ListObjOutput<TableDTO>>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "api/tables/{id}")
	public ResponseEntity<ObjectOuput<TableDTO>> showTable(@PathVariable("id") long id) {
		TableDTO tableDTO =  tableService.getById(id);
		ObjectOuput<TableDTO> result = new ObjectOuput<TableDTO>();
		result.setData(tableDTO);
		result.setCode("200");
		
		return new ResponseEntity<ObjectOuput<TableDTO>>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "api/tables")
	public ResponseEntity<ObjectOuput<TableDTO>> createTable(@RequestBody TableDTO model) {
		tableService.save(model);
		
		ObjectOuput<TableDTO> result = new ObjectOuput<TableDTO>();
		result.setCode("201");
		
		return new ResponseEntity<ObjectOuput<TableDTO>>(result, HttpStatus.CREATED);				
	}

	@PutMapping(value = "api/tables/{id}")
	public ResponseEntity<ObjectOuput<TableDTO>> updateNew(@RequestBody TableDTO model, @PathVariable("id") long id) {
		model.setId(id);
		tableService.save(model);
		
		ObjectOuput<TableDTO> result = new ObjectOuput<TableDTO>();
		result.setCode("200");
		
		return new ResponseEntity<ObjectOuput<TableDTO>>(result, HttpStatus.OK);		
	}
	
	@DeleteMapping(value = "api/tables/{id}")
	public ResponseEntity<ObjectOuput<TableDTO>> deleteTable(@PathVariable("id") long id) {
		
		tableService.delete(id);
		
		ObjectOuput<TableDTO> result = new ObjectOuput<TableDTO>();
		result.setCode("200");
		
		return new ResponseEntity<ObjectOuput<TableDTO>>(result, HttpStatus.OK);
	}
}
