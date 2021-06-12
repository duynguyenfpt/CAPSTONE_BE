package com.web_service.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import com.web_service.api.output.DatabaseInfoOutput;
import com.web_service.api.output.PagingOutput;
import com.web_service.dto.DatabaseInfoDTO;
import com.web_service.services.IDatabaseInfoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class DatabaseInfoAPI {
	@Autowired
	private IDatabaseInfoService databaseInfoService;

	@GetMapping(value = "/api/database_infors")
	public DatabaseInfoOutput showDatabaseInfors(@RequestParam("page") int page,
								@RequestParam("limit") int limit, @RequestParam(required = false) String keyword) {
		
		if (keyword == null || keyword.isEmpty()) keyword = "";
		
		DatabaseInfoOutput result = new DatabaseInfoOutput();
		Pageable pageable = new PageRequest(page - 1, limit);
		result.setData(databaseInfoService.findAll(pageable, keyword));
		int totalPage = (int) Math.ceil((double) (databaseInfoService.totalItem()) / limit);
		int totalItem = databaseInfoService.totalItem();
		result.setMeta(new PagingOutput(totalPage, totalItem));

		return result;
	}
	
	@GetMapping(value = "/api/database_infors/{id}")
	public DatabaseInfoDTO showDatabaseInfo(@PathVariable("id") long id) {
		return databaseInfoService.getById(id);
	}
	
	@PostMapping(value = "/api/database_infors")
	public DatabaseInfoDTO createDatabaseInfo(@RequestBody DatabaseInfoDTO model) {
		return databaseInfoService.save(model);
	}

	@PutMapping(value = "/api/database_infors/{id}")
	public DatabaseInfoDTO updateDatabaseInfo(@RequestBody DatabaseInfoDTO model, @PathVariable("id") long id) {
		model.setId(id);
		
		return databaseInfoService.save(model);
	}
	
	@DeleteMapping(value = "/api/database_infors/{id}")
	public void deleteDatabaseInfo(@PathVariable("id") long id) {
		databaseInfoService.delete(id);
	}
	
	@PostMapping(value = "/api/database_infors/test_connection")
	public ResponseEntity<Map<String,Object>> checkingConnection(@RequestBody DatabaseInfoDTO model) {
		 return databaseInfoService.trackingConnection(model);
	}
}