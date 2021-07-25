package com.web_service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web_service.api.output.ListObjOutput;
import com.web_service.api.output.ObjectOuput;
import com.web_service.dto.AccountRightDTO;
import com.web_service.services.IAccountRightService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
public class AccountRightAPI {
	@Autowired
	IAccountRightService accountRightService;
	
	@PostMapping(value = "/api/account_rights")
	public ResponseEntity<ListObjOutput<AccountRightDTO>> createAccountRight(@RequestBody AccountRightDTO model) {
		ListObjOutput<AccountRightDTO> result = new ListObjOutput<AccountRightDTO>();
		try {
			result.setData(accountRightService.create(model));
			result.setCode("201");
			result.setMessage("Create permision for user successfully");
			
			return new ResponseEntity<ListObjOutput<AccountRightDTO>>(result, HttpStatus.CREATED);
		}catch (NullPointerException e) {
			result.setCode("404");
			result.setMessage("Right or account not exist");
			return new ResponseEntity<ListObjOutput<AccountRightDTO>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setCode("500");
			
			result.setMessage("Can not create permision for user");
			return new ResponseEntity<ListObjOutput<AccountRightDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/api/account_rights")
	public ResponseEntity<ObjectOuput<AccountRightDTO>> deleteAccountRight(@RequestBody Long[] ids) {
		ObjectOuput<AccountRightDTO> result = new ObjectOuput<AccountRightDTO>();
		try {
			accountRightService.delete(ids);
			result.setCode("200");
			
			return new ResponseEntity<ObjectOuput<AccountRightDTO>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setCode("404");
			result.setMessage("Not found");
			return new ResponseEntity<ObjectOuput<AccountRightDTO>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setCode("500");
			
			result.setMessage("Can not delete permision");
			return new ResponseEntity<ObjectOuput<AccountRightDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
