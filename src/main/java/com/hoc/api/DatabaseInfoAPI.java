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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hoc.api.output.DatabaseInfoOutput;
import com.hoc.api.output.PagingOutput;
import com.hoc.dto.DatabaseInfoDTO;
import com.hoc.service.IDatabaseInfoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class DatabaseInfoAPI {
	@Autowired
	private IDatabaseInfoService databaseInfoService;

	@GetMapping(value = "/api/database_infors/")
	public DatabaseInfoOutput showDatabaseInfors(@RequestParam("page") int page,
								@RequestParam("limit") int limit) {
		
		DatabaseInfoOutput result = new DatabaseInfoOutput();
		Pageable pageable = new PageRequest(page - 1, limit);
		result.setData(databaseInfoService.findAll(pageable));
		int totalPage = (int) Math.ceil((double) (databaseInfoService.totalItem()) / limit);
		int totalItem = databaseInfoService.totalItem();
		result.setMeta(new PagingOutput(page, totalPage, totalItem));

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
	
	@DeleteMapping(value = "/api/database_infors")
	public void deleteDatabaseInfo(@RequestBody long[] ids) {
		databaseInfoService.delete(ids);
	}
}
