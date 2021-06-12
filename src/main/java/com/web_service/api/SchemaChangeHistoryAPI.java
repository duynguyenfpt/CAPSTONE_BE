package com.web_service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web_service.api.output.PagingOutput;
import com.web_service.api.output.SchemaChangeHistoryOutput;
import com.web_service.dto.SchemaChangeHistoryDTO;
import com.web_service.services.ISchemaChangeHistoryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SchemaChangeHistoryAPI {
	@Autowired
	private ISchemaChangeHistoryService schemaChangeHistoryService;

	@GetMapping(value = "/api/schema_change_history")
	public SchemaChangeHistoryOutput showSchemaChangeHistory(@RequestParam("page") int page,
								@RequestParam("limit") int limit) {
		
		SchemaChangeHistoryOutput result = new SchemaChangeHistoryOutput();
		Pageable pageable = new PageRequest(page - 1, limit);
		result.setData(schemaChangeHistoryService.findAll(pageable));
		int totalPage = (int) Math.ceil((double) (schemaChangeHistoryService.totalItem()) / limit);
		int totalItem = schemaChangeHistoryService.totalItem();
		result.setMeta(new PagingOutput(page, totalPage, totalItem));

		return result;
	}
	
	@GetMapping(value = "/api/schema_change_history/{id}")
	public SchemaChangeHistoryDTO showServerInfo(@PathVariable("id") long id) {
		return schemaChangeHistoryService.getById(id);
	}


}
