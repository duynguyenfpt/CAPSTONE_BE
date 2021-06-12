package com.web_service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web_service.api.output.CurrentTableSchemaOutput;
import com.web_service.api.output.PagingOutput;
import com.web_service.dto.CurrentTableSchemaDTO;
import com.web_service.services.ICurrentTableSchemaService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CurrentTableSchemaAPI {
	@Autowired
	private ICurrentTableSchemaService currentTableSchemaService;

	@GetMapping(value = "/api/tables/{table_id}/current_table_schemas")
	public CurrentTableSchemaOutput showCurrentTableSchema(@RequestParam("page") int page,
								@RequestParam("limit") int limit,
								@PathVariable("table_id") long tableId) {
		
		CurrentTableSchemaOutput result = new CurrentTableSchemaOutput();
		Pageable pageable = new PageRequest(page - 1, limit);
		result.setData(currentTableSchemaService.findAll(pageable, tableId));
		int totalPage = (int) Math.ceil((double) (currentTableSchemaService.totalItem(tableId)) / limit);
		int totalItem = currentTableSchemaService.totalItem(tableId);
		result.setMeta(new PagingOutput(page, totalPage, totalItem));

		return result;
	}
	
	@GetMapping(value = "/api/current_table_schemas/{id}")
	public CurrentTableSchemaDTO showCurrentTableSchema(@PathVariable("id") long id) {
		return currentTableSchemaService.getById(id);
	}
}
