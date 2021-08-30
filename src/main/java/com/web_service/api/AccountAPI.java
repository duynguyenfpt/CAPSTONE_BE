package com.web_service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web_service.services.IAccountService;
import com.sun.jersey.api.NotFoundException;
import com.web_service.api.output.ListObjOutput;
import com.web_service.api.output.ObjectOuput;
import com.web_service.api.output.PagingOutput;
import com.web_service.dto.AccountDTO;
import com.web_service.dto.AccountRightDTO;
import com.web_service.dto.ChangePasswordDTO;
import com.web_service.dto.JwtResponse;
import com.web_service.entity.AccountEntity;
import com.web_service.entity.JwtRequest;
import com.web_service.security.config.JwtTokenUtil;
import com.web_service.security.services.JwtUserDetailsService;



@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
public class AccountAPI {
	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil JwtTokenUtil;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@PostMapping(value = "/api/authenticate")
	public ResponseEntity<ObjectOuput<JwtResponse>> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
		ObjectOuput<JwtResponse> result = new ObjectOuput<JwtResponse>();

		try {
			AccountEntity accountEntity = accountService.findByUserNameEntity(authenticationRequest.getUsername());
			
			if(accountEntity == null) {
				result.setCode("401");
				result.setData(new JwtResponse(""));
				result.setMessage("Account not exist");
				
				return new ResponseEntity<ObjectOuput<JwtResponse>>(result, HttpStatus.UNAUTHORIZED);
			}else if(accountEntity.getActive() == false) {
				result.setCode("401");
				result.setData(new JwtResponse(""));
				result.setMessage("Account is deactive");
				
				return new ResponseEntity<ObjectOuput<JwtResponse>>(result, HttpStatus.UNAUTHORIZED);
			}else {
				authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
				
				final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
				final String token = JwtTokenUtil.generateToken(userDetails);
				
				result.setCode("200");
				result.setData(new JwtResponse(token));
				result.setMessage("Login success");
				
				return new ResponseEntity<ObjectOuput<JwtResponse>>(result, HttpStatus.OK);
			}
		} catch (Exception e) {
			result.setCode("401");
			result.setMessage("Login fail");
	
			return new ResponseEntity<ObjectOuput<JwtResponse>>(result, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping(value = "/api/me")
	public ResponseEntity<ObjectOuput<AccountDTO>> getCurrentUser() throws Exception {
		ObjectOuput<AccountDTO> result = new ObjectOuput<AccountDTO>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		AccountDTO accountDTO = accountService.findByUserName(auth.getName());
		result.setCode("200");
		result.setData(accountDTO);
		result.setMessage("Success");

		return new ResponseEntity<ObjectOuput<AccountDTO>>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "/api/register")
	public ResponseEntity<ObjectOuput<AccountEntity>> saveUser(@RequestBody AccountDTO account) throws Exception {
		ObjectOuput<AccountEntity> result = new ObjectOuput<AccountEntity>();
		try {
			userDetailsService.save(account);
			
			result.setCode("201");
			result.setMessage("success");
			
			return new ResponseEntity<ObjectOuput<AccountEntity>>(result, HttpStatus.CREATED);
		}catch (DataIntegrityViolationException e) {
			result.setCode("422");
			result.setMessage("User or Email exist");
			
			return new ResponseEntity<ObjectOuput<AccountEntity>>(result, HttpStatus.UNPROCESSABLE_ENTITY);
		}catch (Exception e) {
			result.setCode("500");
			result.setMessage("Can not create account");
			
			return new ResponseEntity<ObjectOuput<AccountEntity>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/api/accounts")
	public ResponseEntity<ListObjOutput<AccountDTO>> showAccount(@RequestParam("page") int page,
								@RequestParam("limit") int limit, @RequestParam(required = false) String keyword) {
		
		if (keyword == null || keyword.isEmpty()) keyword = "";

		ListObjOutput<AccountDTO> result = new ListObjOutput<AccountDTO>();
		Pageable pageable = new PageRequest(page - 1, limit);
		result.setData(accountService.findAll(keyword, pageable));
		int totalPage = (int) Math.ceil((double) (accountService.totalItem(keyword)) / limit);
		int totalItem = accountService.totalItem(keyword);
		result.setMetaData(new PagingOutput(totalPage, totalItem));
		result.setCode("200");

		return new ResponseEntity<ListObjOutput<AccountDTO>>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/api/list_account")
	public ResponseEntity<ListObjOutput<AccountDTO>> showAccountOnlyName(@RequestParam("page") int page,
								@RequestParam("limit") int limit) {
		
		ListObjOutput<AccountDTO> result = new ListObjOutput<AccountDTO>();
		Pageable pageable = new PageRequest(page - 1, limit);
		result.setData(accountService.getAccountActive(pageable));
		int totalPage = (int) Math.ceil((double) (accountService.totalItemAccountActive()) / limit);
		int totalItem = accountService.totalItemAccountActive();
		result.setMetaData(new PagingOutput(totalPage, totalItem));
		result.setCode("200");

		return new ResponseEntity<ListObjOutput<AccountDTO>>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "/api/accounts/{id}/reset_password")
	public ResponseEntity<ObjectOuput<AccountDTO>> resetPassword(@PathVariable("id") long id) {
		ObjectOuput<AccountDTO> result = new ObjectOuput<AccountDTO>();
		try {
			userDetailsService.resetPassword(id);
			result.setCode("200");
			result.setMessage("Reset password success");
			
			return new ResponseEntity<ObjectOuput<AccountDTO>>(result, HttpStatus.OK);
		}catch (Exception e) {
			result.setCode("500");
			result.setMessage("Can not reset password");
			
			return new ResponseEntity<ObjectOuput<AccountDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/api/change_password")
	public ResponseEntity<ObjectOuput<String>> changePassword(@RequestBody ChangePasswordDTO dto) throws Exception {
		ObjectOuput<String> result = new ObjectOuput<String>();
		try {
			String message = userDetailsService.changePassword(dto.getOldPassword(), dto.getNewPassword());
			
			if(message.equals("success")) {
				result.setCode("200");
				result.setMessage("Change password successfully");
				
				return new ResponseEntity<ObjectOuput<String>>(result, HttpStatus.OK);
			}else {
				result.setCode("400");
				result.setMessage("Invalid password");
				
				return new ResponseEntity<ObjectOuput<String>>(result, HttpStatus.BAD_REQUEST);
			}
		}catch (Exception e) {
			result.setCode("500");
			result.setMessage("Can not change password");
			
			return new ResponseEntity<ObjectOuput<String>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/api/accounts")
	public ResponseEntity<ObjectOuput<AccountEntity>> saveAccountWithRights(@RequestBody AccountRightDTO account) throws Exception {
		ObjectOuput<AccountEntity> result = new ObjectOuput<AccountEntity>();
		try {
			accountService.createAccountWithRights(account);
			
			result.setCode("201");
			result.setMessage("success");
			
			return new ResponseEntity<ObjectOuput<AccountEntity>>(result, HttpStatus.CREATED);
		}catch (DataIntegrityViolationException e) {
			result.setCode("422");
			result.setMessage("User or Email exist");
			
			return new ResponseEntity<ObjectOuput<AccountEntity>>(result, HttpStatus.UNPROCESSABLE_ENTITY);
		}catch (Exception e) {
			result.setCode("500");
			result.setMessage("Can not create account");
			
			return new ResponseEntity<ObjectOuput<AccountEntity>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "/api/accounts/{id}")
	public ResponseEntity<ObjectOuput<AccountEntity>> updateAccount(@RequestBody AccountDTO model, @PathVariable("id") long id) throws Exception {
		ObjectOuput<AccountEntity> result = new ObjectOuput<AccountEntity>();
		try {
			model.setId(id);
			accountService.save(model);
			
			result.setCode("200");
			result.setMessage("success");
			
			return new ResponseEntity<ObjectOuput<AccountEntity>>(result, HttpStatus.CREATED);
		}catch (NotFoundException e) {
			result.setCode("404");
			result.setMessage("Not found account");
			
			return new ResponseEntity<ObjectOuput<AccountEntity>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setCode("500");
			result.setMessage("Can not create account");
			
			return new ResponseEntity<ObjectOuput<AccountEntity>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
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
		}catch (Exception e) {
			result.setCode("500");
			result.setMessage("Can not get data");
			
			return new ResponseEntity<ObjectOuput<AccountDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/api/accounts/forgot_password")
	public ResponseEntity<ObjectOuput<String>> forgotPassword(@RequestParam("username") String username) {
		ObjectOuput<String> result = new ObjectOuput<String>();
		try{
			accountService.forgotPassword(username);
			result.setData("Success");
			result.setCode("200");
			
			return new ResponseEntity<ObjectOuput<String>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setMessage("Not found user");
			result.setCode("404");
			
			return new ResponseEntity<ObjectOuput<String>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setCode("500");
			result.setMessage("Can not send email");
			
			return new ResponseEntity<ObjectOuput<String>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
