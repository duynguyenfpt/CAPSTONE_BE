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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web_service.api.output.ListObjOutput;
import com.web_service.api.output.ObjectOuput;
import com.web_service.api.output.PagingOutput;
import com.web_service.dto.RequestDTO;
import com.web_service.services.IRequestService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class RequestAPI {
	@Autowired
	private IRequestService requestService;

	@GetMapping(value = "/api/requests")
	public ResponseEntity<ListObjOutput<RequestDTO>> showRequests(@RequestParam("page") int page,
								@RequestParam("limit") int limit) {
		
		ListObjOutput<RequestDTO> result = new ListObjOutput<RequestDTO>();
		Pageable pageable = new PageRequest(page - 1, limit);
		result.setData(requestService.findAll(pageable));
		int totalPage = (int) Math.ceil((double) (requestService.totalItem()) / limit);
		int totalItem = requestService.totalItem();
		result.setMetaData(new PagingOutput(totalPage, totalItem));
		result.setCode("200");

		return new ResponseEntity<ListObjOutput<RequestDTO>>(result, HttpStatus.OK);
	}
	
	@PutMapping(value = "/api/requests/{id}")
	public ResponseEntity<ObjectOuput<RequestDTO>> updateRequest(@RequestBody RequestDTO model, @PathVariable("id") long id) {
		model.setId(id);
		requestService.save(model);
		
		ObjectOuput<RequestDTO> result = new ObjectOuput<RequestDTO>();
		result.setCode("200");
		
		return new ResponseEntity<ObjectOuput<RequestDTO>>(result, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/api/requests/{id}")
	public ResponseEntity<ObjectOuput<RequestDTO>> deleteRequest(@PathVariable("id") long id) {
		requestService.delete(id);
		
		ObjectOuput<RequestDTO> result = new ObjectOuput<RequestDTO>();
		result.setCode("200");
		
		return new ResponseEntity<ObjectOuput<RequestDTO>>(result, HttpStatus.OK);

	}
}
