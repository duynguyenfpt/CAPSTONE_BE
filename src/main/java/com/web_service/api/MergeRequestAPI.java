package com.web_service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import com.web_service.dto.MergeRequestDTO;
import com.web_service.services.IMergeRequestService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
public class MergeRequestAPI {
	@Autowired
	private IMergeRequestService mergeRequestService;
	
	
	@GetMapping(value = "/api/merge_requests")
	public ResponseEntity<ListObjOutput<MergeRequestDTO>> showMergeRequests(@RequestParam("page") int page,
								@RequestParam("limit") int limit) {
		
		ListObjOutput<MergeRequestDTO> result = new ListObjOutput<MergeRequestDTO>();
		try {
			Pageable pageable = new PageRequest(page - 1, limit);
			result.setData(mergeRequestService.findAll(pageable));
			int totalPage = (int) Math.ceil((double) (mergeRequestService.totalItem()) / limit);
			int totalItem = mergeRequestService.totalItem();
			result.setMetaData(new PagingOutput(totalPage, totalItem));
			result.setCode("200");
			result.setMessage("Get data successfully");
			
			return new ResponseEntity<ListObjOutput<MergeRequestDTO>>(result, HttpStatus.OK);
		}catch (Exception e) {
			result.setCode("500");
			result.setMessage("Can not get data");
			
			return new ResponseEntity<ListObjOutput<MergeRequestDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping(value = "/api/requests/{request_id}/merge_requests")
	public ResponseEntity<ObjectOuput<MergeRequestDTO>> showMergeRequest(@PathVariable("request_id") long requestId) {
		ObjectOuput<MergeRequestDTO> result = new ObjectOuput<MergeRequestDTO>();
		try{
			MergeRequestDTO mergeRequestDTO = mergeRequestService.getByRequestId(requestId);
			result.setData(mergeRequestDTO);
			result.setCode("200");
			result.setMessage("Get data successfully");
			
			return new ResponseEntity<ObjectOuput<MergeRequestDTO>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setMessage("Not found record");
			result.setCode("404");
			
			return new ResponseEntity<ObjectOuput<MergeRequestDTO>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setCode("500");
			result.setMessage("Can not get data");
			
			return new ResponseEntity<ObjectOuput<MergeRequestDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
		
	@PostMapping(value = "/api/merge_requests")
	public ResponseEntity<ObjectOuput<MergeRequestDTO>> createMergeRequest(@RequestBody MergeRequestDTO model) {
		ObjectOuput<MergeRequestDTO> result = new ObjectOuput<MergeRequestDTO>();
		try {
			result.setData(mergeRequestService.save(model));
			result.setMessage("Create merge request successfully");
			result.setCode("201");
			
			return new ResponseEntity<ObjectOuput<MergeRequestDTO>>(result, HttpStatus.CREATED);
		}catch (DataIntegrityViolationException e) {
			result.setMessage("Table name can not empty or exist");
			result.setCode("422");
			
			return new ResponseEntity<ObjectOuput<MergeRequestDTO>>(result, HttpStatus.UNPROCESSABLE_ENTITY);
		}catch (Exception e) {
			result.setCode("500");
			result.setMessage("Can not create etl request");
			return new ResponseEntity<ObjectOuput<MergeRequestDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "/api/requests/{request_id}/merge_requests")
	public ResponseEntity<ObjectOuput<MergeRequestDTO>> updateMergeRequest(@RequestBody MergeRequestDTO model, @PathVariable("request_id") long requestId) {
		ObjectOuput<MergeRequestDTO> result = new ObjectOuput<MergeRequestDTO>();
		try {
			model.setRequestId(requestId);
			mergeRequestService.update(model);
			result.setCode("200");
			result.setMessage("Update data successfully");
			
			return new ResponseEntity<ObjectOuput<MergeRequestDTO>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setMessage("Not found record");
			result.setCode("404");
			
			return new ResponseEntity<ObjectOuput<MergeRequestDTO>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setMessage("Can not update data");
			result.setCode("500");
			
			return new ResponseEntity<ObjectOuput<MergeRequestDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@DeleteMapping(value = "/api/merge_requests/{id}")
	public ResponseEntity<ObjectOuput<MergeRequestDTO>> deleteMergeRequest(@PathVariable("id") long id) {
		ObjectOuput<MergeRequestDTO> result = new ObjectOuput<MergeRequestDTO>();
		try {
			mergeRequestService.delete(id);
			result.setCode("200");
			
			return new ResponseEntity<ObjectOuput<MergeRequestDTO>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setMessage("Not found record");
			result.setCode("404");
			
			return new ResponseEntity<ObjectOuput<MergeRequestDTO>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setMessage("Can not delete data");
			result.setCode("500");
			
			return new ResponseEntity<ObjectOuput<MergeRequestDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
