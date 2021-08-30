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
import com.web_service.dto.BooleanDTO;
import com.web_service.dto.DatabaseInfoDTO;
import com.web_service.dto.RightDTO;
import com.web_service.dto.ServerInfoDTO;
import com.web_service.services.IRightService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
public class RightAPI {

	@Autowired
	private IRightService rightService;

	@GetMapping(value = "/api/rights")
	public ResponseEntity<ListObjOutput<RightDTO>> showRights(@RequestParam("page") int page,
								@RequestParam("limit") int limit,
								@RequestParam(required = false) String keyword) {
		
		ListObjOutput<RightDTO> result = new ListObjOutput<RightDTO>();
		try {
			result.setData(rightService.findAll(keyword, page, limit));
			int totalItem = rightService.totalItemSearch(keyword);
			int totalPage = (int) Math.ceil((double) (totalItem) / limit);
			result.setMetaData(new PagingOutput(totalPage, totalItem));
			result.setCode("200");

			return new ResponseEntity<ListObjOutput<RightDTO>>(result, HttpStatus.OK);
		}catch (Exception e) {
			result.setMessage("Can not get data");
			result.setCode("500");
			
			return new ResponseEntity<ListObjOutput<RightDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/api/accounts/{account_id}/rights")
	public ResponseEntity<ListObjOutput<RightDTO>> showRightByAccountId(@RequestParam("page") int page,
								@RequestParam("limit") int limit, @PathVariable("account_id") long accountId) {
		
		ListObjOutput<RightDTO> result = new ListObjOutput<RightDTO>();
		try {
			Pageable pageable = new PageRequest(page - 1, limit);
			result.setData(rightService.findAllByAccountId(accountId, pageable));
			int totalPage = (int) Math.ceil((double) (rightService.countRightByAccountId(accountId)) / limit);
			int totalItem = rightService.countRightByAccountId(accountId);
			result.setMetaData(new PagingOutput(totalPage, totalItem));
			result.setCode("200");

			return new ResponseEntity<ListObjOutput<RightDTO>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setMessage("Not found record");
			result.setCode("404");
			
			return new ResponseEntity<ListObjOutput<RightDTO>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setMessage("Can not get data");
			result.setCode("500");
			
			return new ResponseEntity<ListObjOutput<RightDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/api/rights")
	public ResponseEntity<ObjectOuput<RightDTO>> createRight(@RequestBody RightDTO model) {
		ObjectOuput<RightDTO> result = new ObjectOuput<RightDTO>();
		try {
			result.setData(rightService.save(model));
			result.setMessage("Create permision successfully");
			result.setCode("201");
			
			return new ResponseEntity<ObjectOuput<RightDTO>>(result, HttpStatus.CREATED);
		}catch (DataIntegrityViolationException e) {
			result.setCode("422");
			result.setMessage("Code of permission exist");
			
			return new ResponseEntity<ObjectOuput<RightDTO>>(result, HttpStatus.UNPROCESSABLE_ENTITY);
		}catch (Exception e) {
			result.setCode("500");
			result.setMessage("Can not create permision");
			return new ResponseEntity<ObjectOuput<RightDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "/api/rights/{id}")
	public ResponseEntity<ObjectOuput<RightDTO>> updateRight(@RequestBody RightDTO model, @PathVariable("id") long id) {
		ObjectOuput<RightDTO> result = new ObjectOuput<RightDTO>();
		try {
			model.setId(id);
			rightService.save(model);
			result.setCode("200");
			
			return new ResponseEntity<ObjectOuput<RightDTO>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setMessage("Not found record");
			result.setCode("404");
			
			return new ResponseEntity<ObjectOuput<RightDTO>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setMessage("Can not update permision");
			result.setCode("500");
			
			return new ResponseEntity<ObjectOuput<RightDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@GetMapping(value = "/api/rights/{id}")
	public ResponseEntity<ObjectOuput<RightDTO>> showRight(@PathVariable("id") Long id) {
		ObjectOuput<RightDTO> result = new ObjectOuput<RightDTO>();
		try{
			RightDTO rightDTO =  rightService.getById(id);
			
			result.setMessage("get data successfull");
			result.setData(rightDTO);
			result.setCode("200");
			return new ResponseEntity<ObjectOuput<RightDTO>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setMessage("Not found record");
			result.setCode("404");
			return new ResponseEntity<ObjectOuput<RightDTO>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setCode("500");
			result.setMessage("Can not get data");
			
			return new ResponseEntity<ObjectOuput<RightDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/api/rights/{id}")
	public ResponseEntity<ObjectOuput<RightDTO>> deleteRight(@PathVariable("id") long id) {
		ObjectOuput<RightDTO> result = new ObjectOuput<RightDTO>();

		try {
			rightService.delete(id);
			result.setCode("200");
			
			return new ResponseEntity<ObjectOuput<RightDTO>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setMessage("Not found record");
			result.setCode("404");
			
			return new ResponseEntity<ObjectOuput<RightDTO>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setMessage("Can not delete data");
			result.setCode("500");
			
			return new ResponseEntity<ObjectOuput<RightDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/api/check_permission")
	public ResponseEntity<ObjectOuput<BooleanDTO>> checkPermission(@RequestBody RightDTO model) {
		ObjectOuput<BooleanDTO> result = new ObjectOuput<BooleanDTO>();
		try {
			boolean isPermission = rightService.checkPermission(model);
			BooleanDTO booleanDTO = new BooleanDTO();
			booleanDTO.setSuccess(isPermission);
			
			result.setData(booleanDTO);
			if(isPermission) {
				result.setMessage("Success");
			} else {
				result.setMessage("Permission denied");
			}
			result.setCode("200");
			
			return new ResponseEntity<ObjectOuput<BooleanDTO>>(result, HttpStatus.OK);
		}catch (Exception e) {
			result.setCode("500");
			result.setMessage("Can not check permision");
			return new ResponseEntity<ObjectOuput<BooleanDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
