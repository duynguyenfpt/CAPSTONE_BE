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

import com.hoc.api.output.NewOutput;
import com.hoc.dto.NewDTO;
import com.hoc.service.INewService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class NewAPI {
	
	@Autowired
	private INewService newService;

//	@GetMapping(value = "/new")
////	public NewOutput showNews(@RequestParam("page") int page,
////								@RequestParam("limit") int limit) {
////		NewOutput result = new NewOutput();
////		result.setPage(page);
////		Pageable pageable = new PageRequest(page - 1, limit);
////		result.setListResult(newService.findAll(pageable));
////		result.setTotalPage((int) Math.ceil((double) (newService.totalItem()) / limit));
////		result.setTotalItem(newService.totalItem());
//////		List<NewDTO> result = newService.findAll();
////		return result;
////	}
	
	@GetMapping(value = "/new/{id}")
	public NewDTO showNew(@PathVariable("id") long id) {
		return newService.getById(id);
	}
	
	@PostMapping(value = "/new")
	public NewDTO createNew(@RequestBody NewDTO model) {
		return newService.save(model);
	}

	@PutMapping(value = "/new/{id}")
	public NewDTO updateNew(@RequestBody NewDTO model, @PathVariable("id") long id) {
		model.setId(id);
		
		return newService.save(model);
	}
	
	@DeleteMapping(value = "/new")
	public void deleteNew(@RequestBody long[] ids) {
		newService.delete(ids);
	}
	
}
