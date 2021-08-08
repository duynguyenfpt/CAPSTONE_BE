package com.web_service.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web_service.api.output.ListObjOutput;
import com.web_service.api.output.ObjectOuput;
import com.web_service.api.output.PagingOutput;
import com.web_service.dto.JobDetailDTO;
import com.web_service.entity.mongo.JobLogEntity;
import com.web_service.services.IJobLogService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
public class JobLogAPI {
	@Autowired
	private IJobLogService jobLogService;
	
	@GetMapping(value = "/api/job_logs")
	public ResponseEntity<ListObjOutput<JobLogEntity>> getAllJobLog(@RequestParam("page") int page,
			@RequestParam("limit") int limit,
			@RequestParam(required = false) String host, 
			@RequestParam(required = false) String port,
			@RequestParam(required = false) String databaseName,
			@RequestParam(required = false) String tableName,
			@RequestParam(required = false) String requestType,
			@RequestParam(required = false) String status) {
		
		ListObjOutput<JobLogEntity> result = new ListObjOutput<JobLogEntity>();
		Pageable pageable = new PageRequest(page - 1, limit);
		result.setData(jobLogService.findAll(host, port, databaseName, tableName, requestType, status, pageable));
		int totalItem = jobLogService.totalItem(host, port, databaseName, tableName, requestType, status);
		int totalPage = (int) Math.ceil((double) (totalItem) / limit);
		result.setMetaData(new PagingOutput(totalPage, totalItem));
		result.setCode("200");

		return new ResponseEntity<ListObjOutput<JobLogEntity>>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/api/jobs/{job_id}/job_logs")
	public ResponseEntity<ListObjOutput<JobLogEntity>> getAllJobLogByJob(@RequestParam("page") int page,
			@RequestParam("limit") int limit,  @PathVariable("job_id") long jobId) {
		
		ListObjOutput<JobLogEntity> result = new ListObjOutput<JobLogEntity>();
		try {
			Pageable pageable = new PageRequest(page - 1, limit);
		
			result.setData(jobLogService.findAllByJobId(jobId, pageable));
			int totalPage = (int) Math.ceil((double) (jobLogService.totalItemByJobId(jobId)) / limit);
			int totalItem = jobLogService.totalItemByJobId(jobId);
			result.setMetaData(new PagingOutput(totalPage, totalItem));
			result.setCode("200");

			return new ResponseEntity<ListObjOutput<JobLogEntity>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setMessage("Not found record");
			result.setCode("404");
			
			return new ResponseEntity<ListObjOutput<JobLogEntity>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setMessage("Can not get data");
			result.setCode("500");
			
			return new ResponseEntity<ListObjOutput<JobLogEntity>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping(value = "/api/job_logs")
	public ResponseEntity<ObjectOuput<JobLogEntity>> createJobLog(@Valid @RequestBody JobLogEntity jobLog) {
		ObjectOuput<JobLogEntity> result = new ObjectOuput<JobLogEntity>();
		result.setData(jobLogService.save(jobLog));
		result.setCode("201");
		
		return new ResponseEntity<ObjectOuput<JobLogEntity>>(result, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/api/jobs/{job_id}/last_job_log")
	public ResponseEntity<ObjectOuput<JobLogEntity>> getLastJobLog(@PathVariable("job_id") long jobId) {
		
		ObjectOuput<JobLogEntity> result = new ObjectOuput<JobLogEntity>();
		try {			
			JobLogEntity lastJobLog = jobLogService.getLastJobLog(jobId);
			if(lastJobLog != null) {
				result.setData(lastJobLog);
				result.setMessage("Get data successfully");
				result.setCode("200");

				return new ResponseEntity<ObjectOuput<JobLogEntity>>(result, HttpStatus.OK);
			}else {
				result.setData(lastJobLog);
				result.setCode("200");
				result.setMessage("No value present");

				return new ResponseEntity<ObjectOuput<JobLogEntity>>(result, HttpStatus.OK);
			}
		}catch (NullPointerException e) {
			result.setMessage("Not found record");
			result.setCode("404");
			
			return new ResponseEntity<ObjectOuput<JobLogEntity>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setMessage("Can not get data");
			result.setCode("500");
			
			return new ResponseEntity<ObjectOuput<JobLogEntity>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
