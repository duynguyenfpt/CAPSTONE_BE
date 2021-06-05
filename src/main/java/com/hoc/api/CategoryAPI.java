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

import com.hoc.api.output.CategoryOutput;
import com.hoc.dto.CategoryDTO;
import com.hoc.service.ICategoryService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CategoryAPI {
	@Autowired
	private ICategoryService categoryService;

	@GetMapping(value = "/category")
	public CategoryOutput showNews(@RequestParam("page") int page,
								@RequestParam("limit") int limit) {
		
		CategoryOutput result = new CategoryOutput();
		result.setPage(page);
		Pageable pageable = new PageRequest(page - 1, limit);
		result.setListResult(categoryService.findAll(pageable));
		result.setTotalPage((int) Math.ceil((double) (categoryService.totalItem()) / limit));
		result.setTotalItem(categoryService.totalItem());
//		List<NewDTO> result = newService.findAll();
		return result;
	}
	
	@GetMapping(value = "/category/{id}")
	public CategoryDTO showCategory(@PathVariable("id") long id) {
		return categoryService.getById(id);
	}
	
	@PostMapping(value = "/category")
	public CategoryDTO createCategory(@RequestBody CategoryDTO model) {
		return categoryService.save(model);
	}

	@PutMapping(value = "/category/{id}")
	public CategoryDTO updateCategory(@RequestBody CategoryDTO model, @PathVariable("id") long id) {
		model.setId(id);
		
		return categoryService.save(model);
	}
	
	@DeleteMapping(value = "/category")
	public void deleteCategory(@RequestBody long[] ids) {
		categoryService.delete(ids);
	}
}
