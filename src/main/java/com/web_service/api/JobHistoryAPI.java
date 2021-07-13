package com.web_service.api;

import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.MongoDatabase;
import com.web_service.api.output.ListObjOutput;
import com.web_service.api.output.ObjectOuput;
import com.web_service.api.output.PagingOutput;
import com.web_service.entity.mongo.JobHistoryEntity;
import com.web_service.services.IJobHistoryService;

@CrossOrigin
@RestController
public class JobHistoryAPI {
	@Autowired
	private IJobHistoryService jobHistoryService;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@GetMapping(value = "/api/jobs/{job_id}/job_history")
	public ResponseEntity<ListObjOutput<JobHistoryEntity>> getAllNotesByRequest(@RequestParam("page") int page,
			@RequestParam("limit") int limit,  @PathVariable("job_id") long jobId) {
		
		ListObjOutput<JobHistoryEntity> result = new ListObjOutput<JobHistoryEntity>();
		Pageable pageable = new PageRequest(page - 1, limit, new Sort(new Order(Direction.DESC, "timestamp")));
		
		
		result.setData(jobHistoryService.findAllByJobId(jobId, pageable));
		int totalPage = (int) Math.ceil((double) (jobHistoryService.totalItemByJobId(jobId)) / limit);
		int totalItem = jobHistoryService.totalItemByJobId(jobId);
		result.setMetaData(new PagingOutput(totalPage, totalItem));
		result.setCode("200");

		return new ResponseEntity<ListObjOutput<JobHistoryEntity>>(result, HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/api/job_history")
	public ResponseEntity<ObjectOuput<JobHistoryEntity>> createNote(@Valid @RequestBody JobHistoryEntity note) {
		ObjectOuput<JobHistoryEntity> result = new ObjectOuput<JobHistoryEntity>();
		result.setData(jobHistoryService.save(note));
		result.setCode("201");
		
		return new ResponseEntity<ObjectOuput<JobHistoryEntity>>(result, HttpStatus.CREATED);
	}
	
//	@GetMapping(value = "/api/job_history_all")
//	public ResponseEntity<ListObjOutput<Document>> getAllNotesByRequest() {
//		
//		ListObjOutput<Document> result = new ListObjOutput<Document>();
//		
//		List<Document> data =  mongoTemplate.findAll(Document.class, "job_history");
//		result.setData(data);
//		result.setCode("200");
//
//		return new ResponseEntity<ListObjOutput<Document>>(result, HttpStatus.OK);
//	}
}
