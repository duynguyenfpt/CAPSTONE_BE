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
import com.hoc.api.output.TableOutput;
import com.hoc.dto.TableDTO;
import com.hoc.service.ITableService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TableAPI {
	@Autowired
	private ITableService tableService;

	@GetMapping(value = "api/tables")
	public TableOutput showTables(@RequestParam("page") int page,
								@RequestParam("limit") int limit) {
		TableOutput result = new TableOutput();
		Pageable pageable = new PageRequest(page - 1, limit);
		result.setData(tableService.findAll(pageable));
		int totalPage = (int) Math.ceil((double) (tableService.totalItem()) / limit);
		int totalItem = tableService.totalItem();
		result.setMeta(new PagingOutput(page, totalPage, totalItem));
		
		return result;
	}
	
	@GetMapping(value = "api/database_infors/{database_infor_id}/tables")
	public TableOutput showTablesByDatabaseInfor(@PathVariable("database_infor_id") int database_infor_id,
								@RequestParam("page") int page,
								@RequestParam("limit") int limit) {
		TableOutput result = new TableOutput();
		Pageable pageable = new PageRequest(page - 1, limit);
		result.setData(tableService.findByDatabaseInfoId(database_infor_id, pageable));
		int totalPage = (int) Math.ceil((double) (tableService.totalItem()) / limit);
		int totalItem = tableService.totalItem();
		result.setMeta(new PagingOutput(page, totalPage, totalItem));
		
		return result;
	}
	
	@GetMapping(value = "api/tables/{id}")
	public TableDTO showTable(@PathVariable("id") long id) {
		return  tableService.getById(id);
	}
	
	@PostMapping(value = "api/tables")
	public TableDTO createTable(@RequestBody TableDTO model) {
		return tableService.save(model);
	}

	@PutMapping(value = "api/tables/{id}")
	public TableDTO updateNew(@RequestBody TableDTO model, @PathVariable("id") long id) {
		model.setId(id);
		
		return tableService.save(model);
	}
	
	@DeleteMapping(value = "api/tables")
	public void deleteTable(@PathVariable("id") long id) {
		tableService.delete(id);
	}
}
