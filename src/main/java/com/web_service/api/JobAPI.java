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
import com.web_service.dto.JobDTO;
import com.web_service.services.IJobService;

@CrossOrigin(origins = "*", maxAge = 3600)
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
		model.setId(id);
		jobService.save(model);
		
		ObjectOuput<JobDTO> result = new ObjectOuput<JobDTO>();
		result.setCode("200");
		
		return new ResponseEntity<ObjectOuput<JobDTO>>(result, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/api/jobs/{id}")
	public ResponseEntity<ObjectOuput<JobDTO>> deleteJob(@PathVariable("id") long id) {
		jobService.delete(id);
		
		ObjectOuput<JobDTO> result = new ObjectOuput<JobDTO>();
		result.setCode("200");
		
		return new ResponseEntity<ObjectOuput<JobDTO>>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/api/jobs/{id}")
	public ResponseEntity<ObjectOuput<JobDTO>> showJob(@PathVariable("id") long id) {
		JobDTO jobDTO =  jobService.getById(id);
		ObjectOuput<JobDTO> result = new ObjectOuput<JobDTO>();
		result.setData(jobDTO);
		result.setCode("200");
		
		return new ResponseEntity<ObjectOuput<JobDTO>>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "/api/jobs")
	public ResponseEntity<ObjectOuput<JobDTO>> createJob(@RequestBody JobDTO model) {
		ObjectOuput<JobDTO> result = new ObjectOuput<JobDTO>();
		result.setData(jobService.save(model));
		result.setCode("201");
		
		return new ResponseEntity<ObjectOuput<JobDTO>>(result, HttpStatus.CREATED);
	}
}
