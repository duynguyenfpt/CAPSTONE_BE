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
import com.web_service.dto.DatabaseInfoDTO;
import com.web_service.dto.JobDTO;
import com.web_service.dto.NoteDTO;
import com.web_service.services.IJobService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
public class JobAPI {
	@Autowired
	private IJobService jobService;

	@GetMapping(value = "/api/jobs")
	public ResponseEntity<ListObjOutput<JobDTO>> showJobs(@RequestParam("page") int page,
								@RequestParam("limit") int limit) {
		
		ListObjOutput<JobDTO> result = new ListObjOutput<JobDTO>();
			Pageable pageable = new PageRequest(page - 1, limit);
			result.setData(jobService.findAll(pageable));
			int totalPage = (int) Math.ceil((double) (jobService.totalItem()) / limit);
			int totalItem = jobService.totalItem();
			result.setMetaData(new PagingOutput(totalPage, totalItem));
			result.setCode("200");
			
			return new ResponseEntity<ListObjOutput<JobDTO>>(result, HttpStatus.OK);
		
	}
	
	@PutMapping(value = "/api/jobs/{id}")
	public ResponseEntity<ObjectOuput<JobDTO>> updateJob(@RequestBody JobDTO model, @PathVariable("id") long id) {
		ObjectOuput<JobDTO> result = new ObjectOuput<JobDTO>();
		try {
			model.setId(id);
			jobService.save(model);
			result.setCode("200");
			
			return new ResponseEntity<ObjectOuput<JobDTO>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setMessage("Not found record");
			result.setCode("404");
			
			return new ResponseEntity<ObjectOuput<JobDTO>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setMessage("Can not update data");
			result.setCode("500");
			
			return new ResponseEntity<ObjectOuput<JobDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@DeleteMapping(value = "/api/jobs/{id}")
	public ResponseEntity<ObjectOuput<JobDTO>> deleteJob(@PathVariable("id") long id) {
		ObjectOuput<JobDTO> result = new ObjectOuput<JobDTO>();
		try {
			jobService.delete(id);
			result.setCode("200");
			
			return new ResponseEntity<ObjectOuput<JobDTO>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setMessage("Not found record");
			result.setCode("404");
			
			return new ResponseEntity<ObjectOuput<JobDTO>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setMessage("Can not delete data");
			result.setCode("500");
			
			return new ResponseEntity<ObjectOuput<JobDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/api/jobs/{id}")
	public ResponseEntity<ObjectOuput<JobDTO>> showJob(@PathVariable("id") long id) {
		ObjectOuput<JobDTO> result = new ObjectOuput<JobDTO>();

		try {
			JobDTO jobDTO =  jobService.getById(id);
			result.setData(jobDTO);
			result.setCode("200");
			
			return new ResponseEntity<ObjectOuput<JobDTO>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setMessage("Not found record");
			result.setCode("404");
			
			return new ResponseEntity<ObjectOuput<JobDTO>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setMessage("Can not get data");
			result.setCode("500");
			
			return new ResponseEntity<ObjectOuput<JobDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping(value = "/api/jobs")
	public ResponseEntity<ObjectOuput<JobDTO>> createJob(@RequestBody JobDTO model) {
		ObjectOuput<JobDTO> result = new ObjectOuput<JobDTO>();
		try {
			result.setData(jobService.save(model));
			result.setCode("201");
			
			return new ResponseEntity<ObjectOuput<JobDTO>>(result, HttpStatus.CREATED);
		}catch (Exception e) {
			result.setMessage("Can not create data");
			result.setCode("500");
			
			return new ResponseEntity<ObjectOuput<JobDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
