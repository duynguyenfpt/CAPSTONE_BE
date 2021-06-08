package com.hoc.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hoc.api.output.PagingOutput;
import com.hoc.api.output.SchemaChangeHistoryOutput;
import com.hoc.api.output.ServerOutput;
import com.hoc.dto.SchemaChangeHistoryDTO;
import com.hoc.dto.ServerInfoDTO;
import com.hoc.service.ISchemaChangeHistoryService;
import com.hoc.service.IServerInfoService;

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
