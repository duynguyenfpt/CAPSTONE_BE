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
import com.web_service.dto.JobDetailDTO;
import com.web_service.dto.NoteDTO;
import com.web_service.services.IJobService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
public class JobAPI {
	@Autowired
	private IJobService jobService;

	@GetMapping(value = "/api/jobs")
	public ResponseEntity<ListObjOutput<JobDTO>> showJobs(@RequestParam("page") int page,
								@RequestParam("limit") int limit, @RequestParam(required = false) String keyword) {
		
		ListObjOutput<JobDTO> result = new ListObjOutput<JobDTO>();
		if(keyword == null) keyword = "";
			Pageable pageable = new PageRequest(page - 1, limit);
			result.setData(jobService.findAll(keyword, pageable));
			int totalPage = (int) Math.ceil((double) (jobService.totalItem(keyword)) / limit);
			int totalItem = jobService.totalItem(keyword);
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
	
	@GetMapping(value = "/api/jobs/{job_id}/job_detail")
	public ResponseEntity<ListObjOutput<JobDetailDTO>> showJobDetail(@PathVariable("job_id") long jobId) {
		ListObjOutput<JobDetailDTO> result = new ListObjOutput<JobDetailDTO>();
		try {
			result.setData(jobService.getJobDetail(jobId));
			result.setCode("200");
			result.setMessage("Get job detail sucessfully");

			return new ResponseEntity<ListObjOutput<JobDetailDTO>>(result, HttpStatus.OK);
		}catch (Exception e) {
			result.setCode("500");
			result.setMessage("Can not get job detail");

			return new ResponseEntity<ListObjOutput<JobDetailDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/api/jobs/{job_id}/reset_job")
	public ResponseEntity<ObjectOuput<JobDTO>> resetJob(@PathVariable("job_id") Long jobId) {
		ObjectOuput<JobDTO> result = new ObjectOuput<JobDTO>();
		try {
			jobService.resetJob(jobId);
			result.setCode("200");
			
			return new ResponseEntity<ObjectOuput<JobDTO>>(result, HttpStatus.CREATED);
		}catch (NullPointerException e) {
			result.setMessage("Not found record");
			result.setCode("404");
			
			return new ResponseEntity<ObjectOuput<JobDTO>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setMessage("Can not create data");
			result.setCode("500");
			
			return new ResponseEntity<ObjectOuput<JobDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
