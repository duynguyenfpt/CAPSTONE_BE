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
import com.web_service.dto.SchemaChangeHistoryDTO;
import com.web_service.dto.ServerInfoDTO;
import com.web_service.services.IServerInfoService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
public class ServerInfoAPI {
	@Autowired
	private IServerInfoService serverInfoService;

	@GetMapping(value = "/api/server_infors")
	public ResponseEntity<ListObjOutput<ServerInfoDTO>> showServerInfors(@RequestParam("page") int page,
								@RequestParam("limit") int limit, @RequestParam(required = false) String keyword) {
		
		ListObjOutput<ServerInfoDTO> result = new ListObjOutput<ServerInfoDTO>();
		try {
			Pageable pageable = new PageRequest(page - 1, limit);
			result.setData(serverInfoService.findAll(keyword, pageable));
			int totalPage = (int) Math.ceil((double) (serverInfoService.totalItem(keyword)) / limit);
			int totalItem = serverInfoService.totalItem(keyword);
			result.setMetaData(new PagingOutput(totalPage, totalItem));
			result.setCode("200");
			result.setMessage("Get database info successfully");

			return new ResponseEntity<ListObjOutput<ServerInfoDTO>>(result, HttpStatus.OK);
		}catch (Exception e) {
			result.setMessage("Can not get data");
			result.setCode("500");
			
			return new ResponseEntity<ListObjOutput<ServerInfoDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/api/server_infors/{id}")
	public ResponseEntity<ObjectOuput<ServerInfoDTO>> showServerInfo(@PathVariable("id") long id) {
		ObjectOuput<ServerInfoDTO> result = new ObjectOuput<ServerInfoDTO>();
		try {
			ServerInfoDTO serverInfoDTO =  serverInfoService.getById(id);
			result.setData(serverInfoDTO);
			result.setCode("200");
			
			return new ResponseEntity<ObjectOuput<ServerInfoDTO>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setMessage("Not found record");
			result.setCode("404");
			
			return new ResponseEntity<ObjectOuput<ServerInfoDTO>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setMessage("Can not get data");
			result.setCode("500");
			
			return new ResponseEntity<ObjectOuput<ServerInfoDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/api/server_infors")
	public ResponseEntity<ObjectOuput<ServerInfoDTO>> createServerInfo(@RequestBody ServerInfoDTO model) {
		ObjectOuput<ServerInfoDTO> result = new ObjectOuput<ServerInfoDTO>();
		try {
			serverInfoService.save(model);
			
			result.setCode("201");
			
			return new ResponseEntity<ObjectOuput<ServerInfoDTO>>(result, HttpStatus.CREATED);
		}catch (Exception e) {
			result.setMessage("Can not create data");
			result.setCode("500");
			
			return new ResponseEntity<ObjectOuput<ServerInfoDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
				
	}

	@PutMapping(value = "/api/server_infors/{id}")
	public ResponseEntity<ObjectOuput<ServerInfoDTO>> updateServerInfo(@RequestBody ServerInfoDTO model, @PathVariable("id") long id) {
		ObjectOuput<ServerInfoDTO> result = new ObjectOuput<ServerInfoDTO>();
		try {
			model.setId(id);
			serverInfoService.save(model);
			result.setCode("200");
			
			return new ResponseEntity<ObjectOuput<ServerInfoDTO>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setMessage("Not found record");
			result.setCode("404");
			
			return new ResponseEntity<ObjectOuput<ServerInfoDTO>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setMessage("Can not create data");
			result.setCode("500");
			
			return new ResponseEntity<ObjectOuput<ServerInfoDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@DeleteMapping(value = "/api/server_infors/{id}")
	public ResponseEntity<ObjectOuput<ServerInfoDTO>> deleteServerInfo(@PathVariable("id") long id) {
		ObjectOuput<ServerInfoDTO> result = new ObjectOuput<ServerInfoDTO>();

		try {
			serverInfoService.delete(id);
			result.setCode("200");
			
			return new ResponseEntity<ObjectOuput<ServerInfoDTO>>(result, HttpStatus.OK);
		}catch (NullPointerException e) {
			result.setMessage("Not found record");
			result.setCode("404");
			
			return new ResponseEntity<ObjectOuput<ServerInfoDTO>>(result, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			result.setMessage("Can not delete data");
			result.setCode("500");
			
			return new ResponseEntity<ObjectOuput<ServerInfoDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
