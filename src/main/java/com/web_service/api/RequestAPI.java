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
import com.web_service.dto.NoteDTO;
import com.web_service.dto.RequestDTO;
import com.web_service.services.IRequestService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
public class RequestAPI {
	@Autowired
	private IRequestService requestService;

	@GetMapping(value = "/api/requests")
	public ResponseEntity<ListObjOutput<RequestDTO>> showRequests(@RequestParam("page") int page,
								@RequestParam("limit") int limit) {
		
		ListObjOutput<RequestDTO> result = new ListObjOutput<RequestDTO>();
		try {
			Pageable pageable = new PageRequest(page - 1, limit);
			result.setData(requestService.findAll(pageable));
			int totalPage = (int) Math.ceil((double) (requestService.totalItem()) / limit);
			int totalItem = requestService.totalItem();
			result.setMetaData(new PagingOutput(totalPage, totalItem));
			result.setCode("200");

			return new ResponseEntity<ListObjOutput<RequestDTO>>(result, HttpStatus.OK);
		}catch (Exception e) {
			result.setMessage("Can not get data");
			result.setCode("500");
			
			return new ResponseEntity<ListObjOutput<RequestDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PutMapping(value = "/api/requests/{id}")
	public ResponseEntity<ObjectOuput<RequestDTO>> updateRequest(@RequestBody RequestDTO model, @PathVariable("id") long id) {
		ObjectOuput<RequestDTO> result = new ObjectOuput<RequestDTO>();
//		try {
			model.setId(id);
			requestService.update(model);		
			result.setCode("200");
			
			return new ResponseEntity<ObjectOuput<RequestDTO>>(result, HttpStatus.OK);
//		}catch (NullPointerException e) {
//			result.setMessage("Not found record");
//			result.setCode("404");
//			
//			return new ResponseEntity<ObjectOuput<RequestDTO>>(result, HttpStatus.NOT_FOUND);
//		}catch (Exception e) {
//			result.setMessage("Can not update data");
//			result.setCode("500");
//			
//			return new ResponseEntity<ObjectOuput<RequestDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
		
	}
	
	@DeleteMapping(value = "/api/requests/{id}")
	public ResponseEntity<ObjectOuput<RequestDTO>> deleteRequest(@PathVariable("id") long id) {
		ObjectOuput<RequestDTO> result = new ObjectOuput<RequestDTO>();
		try {
			requestService.delete(id);
			result.setCode("200");
			
			return new ResponseEntity<ObjectOuput<RequestDTO>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setMessage("Not found record");
			result.setCode("404");
			
			return new ResponseEntity<ObjectOuput<RequestDTO>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setMessage("Can not delete data");
			result.setCode("500");
			
			return new ResponseEntity<ObjectOuput<RequestDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping(value = "/api/requests/{id}")
	public ResponseEntity<ObjectOuput<RequestDTO>> showRequest(@PathVariable("id") long id) {
		ObjectOuput<RequestDTO> result = new ObjectOuput<RequestDTO>();
		try {
			RequestDTO requestDTO =  requestService.getById(id);
			result.setData(requestDTO);
			result.setCode("200");
			
			return new ResponseEntity<ObjectOuput<RequestDTO>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setMessage("Not found record");
			result.setCode("404");
			
			return new ResponseEntity<ObjectOuput<RequestDTO>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setMessage("Can not delete data");
			result.setCode("500");
			
			return new ResponseEntity<ObjectOuput<RequestDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/api/requests")
	public ResponseEntity<ObjectOuput<RequestDTO>> createRequest(@RequestBody RequestDTO model) {
		ObjectOuput<RequestDTO> result = new ObjectOuput<RequestDTO>();
		try {
			result.setData(requestService.create(model));
			result.setCode("201");
			
			return new ResponseEntity<ObjectOuput<RequestDTO>>(result, HttpStatus.CREATED);
		}catch (Exception e) {
			result.setMessage("Can not delete data");
			result.setCode("500");
			
			return new ResponseEntity<ObjectOuput<RequestDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
}
