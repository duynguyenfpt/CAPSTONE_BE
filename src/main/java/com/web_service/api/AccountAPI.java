package com.web_service.api;
import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.web_service.dto.AccountDTO;
import com.web_service.services.IAccountService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AccountAPI {
	@Autowired
	private IAccountService accountService;

	@GetMapping(value = "/api/accounts")
	public ResponseEntity<ListObjOutput<AccountDTO>> showServerInfors(@RequestParam("page") int page,
								@RequestParam("limit") int limit) {
		
		ListObjOutput<AccountDTO> result = new ListObjOutput<AccountDTO>();
		Pageable pageable = new PageRequest(page - 1, limit);
		result.setData(accountService.findAll(pageable));
		int totalPage = (int) Math.ceil((double) (accountService.totalItem()) / limit);
		int totalItem = accountService.totalItem();
		result.setMetaData(new PagingOutput(totalPage, totalItem));
		result.setCode("200");

		return new ResponseEntity<ListObjOutput<AccountDTO>>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/api/accounts/{id}")
	public ResponseEntity<ObjectOuput<AccountDTO>> showAccount(@PathVariable("id") long id) {
		ObjectOuput<AccountDTO> result = new ObjectOuput<AccountDTO>();
		try{
			AccountDTO accountDTO =  accountService.getById(id);
			result.setData(accountDTO);
			result.setCode("200");
			return new ResponseEntity<ObjectOuput<AccountDTO>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setMessage("Not found record");
			result.setCode("404");
			return new ResponseEntity<ObjectOuput<AccountDTO>>(result, HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping(value = "/api/accounts")
	public ResponseEntity<ObjectOuput<AccountDTO>> createAccount(@RequestBody AccountDTO model) {
		ObjectOuput<AccountDTO> result = new ObjectOuput<AccountDTO>();

		try {
			accountService.save(model);
			
			result.setCode("201");
			return new ResponseEntity<ObjectOuput<AccountDTO>>(result, HttpStatus.CREATED);	
		}catch (DataIntegrityViolationException e) {
			result.setCode("422");
			result.setMessage("user name or email exist");
			
			return new ResponseEntity<ObjectOuput<AccountDTO>>(result, HttpStatus.UNPROCESSABLE_ENTITY);	
		}	
			
	}
	
	@PutMapping(value = "/api/accounts/{id}")
	public ResponseEntity<ObjectOuput<AccountDTO>> updateAccount(@RequestBody AccountDTO model, @PathVariable("id") long id) {
		model.setId(id);
		accountService.save(model);
		
		ObjectOuput<AccountDTO> result = new ObjectOuput<AccountDTO>();
		result.setCode("200");
		
		return new ResponseEntity<ObjectOuput<AccountDTO>>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "/api/accounts/{id}/reset_password")
	public ResponseEntity<ObjectOuput<AccountDTO>> resetPassword(@PathVariable("id") long id) {
		ObjectOuput<AccountDTO> result = new ObjectOuput<AccountDTO>();
		try {
			accountService.resetPassword(id);
			
			result.setCode("200");
			result.setMessage("Reset password sucessfull");
			return new ResponseEntity<ObjectOuput<AccountDTO>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setCode("404");
			result.setMessage("Account not found");
			return new ResponseEntity<ObjectOuput<AccountDTO>>(result, HttpStatus.NOT_FOUND);
		}
		
	}
}
