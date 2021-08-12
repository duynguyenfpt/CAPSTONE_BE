package com.web_service.api;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ResourceUtils;
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
import com.web_service.dto.AccountDTO;
import com.web_service.dto.ContentETLRequestDTO;
import com.web_service.dto.ETLRequestDTO;
import com.web_service.dto.ShareETLRequestDTO;
import com.web_service.repository.ETLRequestRepository;
import com.web_service.services.IAccountService;
import com.web_service.services.IETLService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
public class ETLRequestAPI {
	@Autowired
	private IETLService etlService;
	
	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private ETLRequestRepository ETLRequestRepository;
	
	@GetMapping(value = "/api/etl_requests")
	public ResponseEntity<ListObjOutput<ETLRequestDTO>> showETLRequests(@RequestParam("page") int page,
								@RequestParam("limit") int limit) {
		
		ListObjOutput<ETLRequestDTO> result = new ListObjOutput<ETLRequestDTO>();
		try {
			Pageable pageable = new PageRequest(page - 1, limit);
			result.setData(etlService.findAll(pageable));
			int totalPage = (int) Math.ceil((double) (etlService.totalItem()) / limit);
			int totalItem = etlService.totalItem();
			result.setMetaData(new PagingOutput(totalPage, totalItem));
			result.setCode("200");

			return new ResponseEntity<ListObjOutput<ETLRequestDTO>>(result, HttpStatus.OK);
		}catch (Exception e) {
			result.setCode("500");
			result.setMessage("Can not get data");
			
			return new ResponseEntity<ListObjOutput<ETLRequestDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping(value = "/api/etl_request/{id}")
	public ResponseEntity<ObjectOuput<ETLRequestDTO>> showETLRequest(@PathVariable("id") long id) {
		ObjectOuput<ETLRequestDTO> result = new ObjectOuput<ETLRequestDTO>();
		try{
			ETLRequestDTO etlRequestDTO = etlService.getById(id);
			result.setData(etlRequestDTO);
			result.setCode("200");
			return new ResponseEntity<ObjectOuput<ETLRequestDTO>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setMessage("Not found record");
			result.setCode("404");
			return new ResponseEntity<ObjectOuput<ETLRequestDTO>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setCode("500");
			result.setMessage("Can not get data");
			
			return new ResponseEntity<ObjectOuput<ETLRequestDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/api/request/{request_id}/etl_requests")
	public ResponseEntity<ObjectOuput<ContentETLRequestDTO>> showJob(@PathVariable("request_id") Long requestId) {
		ObjectOuput<ContentETLRequestDTO> result = new ObjectOuput<ContentETLRequestDTO>();

		try {
			ContentETLRequestDTO contentETLRequestDTO = etlService.getResult(requestId);
			result.setData(contentETLRequestDTO);
			result.setMessage("Get data successfully");
			result.setCode("200");
				
			return new ResponseEntity<ObjectOuput<ContentETLRequestDTO>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setMessage("Not found record");
			result.setCode("404");
			
			return new ResponseEntity<ObjectOuput<ContentETLRequestDTO>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setMessage("Can not get data");
			result.setCode("500");
			
			return new ResponseEntity<ObjectOuput<ContentETLRequestDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@PostMapping(value = "/api/etl_requests")
	public ResponseEntity<ObjectOuput<ETLRequestDTO>> createETLRequest(@RequestBody ETLRequestDTO model) {
		ObjectOuput<ETLRequestDTO> result = new ObjectOuput<ETLRequestDTO>();
		try {
			result.setData(etlService.save(model));
			result.setMessage("Create ETL request successfully");
			result.setCode("201");
			
			return new ResponseEntity<ObjectOuput<ETLRequestDTO>>(result, HttpStatus.CREATED);
		}catch (DataIntegrityViolationException e) {
			result.setData(etlService.save(model));
			result.setMessage("Request id exist");
			result.setCode("422");
			
			return new ResponseEntity<ObjectOuput<ETLRequestDTO>>(result, HttpStatus.UNPROCESSABLE_ENTITY);
		}catch (Exception e) {
			result.setCode("500");
			result.setMessage("Can not create etl request");
			return new ResponseEntity<ObjectOuput<ETLRequestDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "/api/etl_requests/{id}")
	public ResponseEntity<ObjectOuput<ETLRequestDTO>> updateEtlRequest(@RequestBody ETLRequestDTO model, @PathVariable("id") long id) {
		ObjectOuput<ETLRequestDTO> result = new ObjectOuput<ETLRequestDTO>();
		try {
			model.setId(id);
			etlService.save(model);
			result.setCode("200");
			
			return new ResponseEntity<ObjectOuput<ETLRequestDTO>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setMessage("Not found record");
			result.setCode("404");
			
			return new ResponseEntity<ObjectOuput<ETLRequestDTO>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setMessage("Can not update data");
			result.setCode("500");
			
			return new ResponseEntity<ObjectOuput<ETLRequestDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@DeleteMapping(value = "/api/etl_requests/{id}")
	public ResponseEntity<ObjectOuput<ETLRequestDTO>> deleteETLRequest(@PathVariable("id") long id) {
		ObjectOuput<ETLRequestDTO> result = new ObjectOuput<ETLRequestDTO>();
		try {
			etlService.delete(id);
			result.setCode("200");
			
			return new ResponseEntity<ObjectOuput<ETLRequestDTO>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setMessage("Not found record");
			result.setCode("404");
			
			return new ResponseEntity<ObjectOuput<ETLRequestDTO>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setMessage("Can not delete data");
			result.setCode("500");
			
			return new ResponseEntity<ObjectOuput<ETLRequestDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/api/etl_requests/share")
	public ResponseEntity<ObjectOuput<String>> shareETLRequest(@RequestBody ShareETLRequestDTO model) {
		ObjectOuput<String> result = new ObjectOuput<String>();
		try {
			etlService.shareETLRequest(model);
			result.setMessage("Share ETL request successfully");
			result.setCode("201");
			
			return new ResponseEntity<ObjectOuput<String>>(result, HttpStatus.CREATED);
		}catch (DataIntegrityViolationException e) {
			
			result.setMessage("Can not share request for the same account");
			result.setCode("422");
			
			return new ResponseEntity<ObjectOuput<String>>(result, HttpStatus.UNPROCESSABLE_ENTITY);
		}catch (Exception e) {
			
			result.setCode("500");
			result.setMessage("Can not share etl request");
			return new ResponseEntity<ObjectOuput<String>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/api/etl_request/share")
	public ResponseEntity<ListObjOutput<ETLRequestDTO>> getShareETLRequest() {
		ListObjOutput<ETLRequestDTO> result = new ListObjOutput<ETLRequestDTO>();
		try{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			AccountDTO accountDTO = accountService.findByUserName(auth.getName());
			
			result.setData(etlService.getShareETLRequest(accountDTO.getId()));
			result.setCode("200");
			result.setMessage("Get list etl request successfully");
			
			return new ResponseEntity<ListObjOutput<ETLRequestDTO>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setMessage("Not found record");
			result.setCode("404");
			return new ResponseEntity<ListObjOutput<ETLRequestDTO>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setCode("500");
			result.setMessage("Can not get data");
			
			return new ResponseEntity<ListObjOutput<ETLRequestDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/api/requests/{request_id}/etl_request/dowload_csv")
	public ResponseEntity<Object> downloadCSV(@PathVariable("request_id") Long requestId) throws IOException {
		HttpHeaders responseHeader = new HttpHeaders();
		try {
			boolean isSuccess =  etlService.downloadCSV(requestId);
			if(isSuccess) {
				String fileName = "/home/public/data.txt";
				File file = new File(fileName);
				InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
//				byte[] data = FileUtils.readFileToByteArray(file);
				responseHeader.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				// Thiết lập thông tin trả về
				responseHeader.set("Content-disposition", "attachment; filename=" + file.getName());
				responseHeader.setContentLength(file.length());
				responseHeader.setContentType(MediaType.parseMediaType("application/txt"));
				responseHeader.setPragma("no-cache");
				responseHeader.setExpires(0);
//				InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(data));
//				InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
				ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(responseHeader).body(resource);
				return responseEntity;
			}else {
				ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(responseHeader).body(null);
				return responseEntity;
			}
		}catch (Exception e) {
			ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(responseHeader).body(null);
			return responseEntity;
		}
	}
}
