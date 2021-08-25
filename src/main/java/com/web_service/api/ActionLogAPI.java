package com.web_service.api;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web_service.api.output.ListObjOutput;
import com.web_service.api.output.PagingOutput;
import com.web_service.entity.mongo.ActionLogEntity;
import com.web_service.services.IActionLogService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
public class ActionLogAPI {
	@Autowired
	private IActionLogService actionLogService;
	
	@GetMapping(value = "/api/action_logs")
	public ResponseEntity<ListObjOutput<ActionLogEntity>> getAllNotes(@RequestParam("page") int page,
			@RequestParam("limit") int limit,
			@RequestParam(required = false) String username, 
			@RequestParam(required = false) String startDate,
			@RequestParam(required = false) String endDate) {
		ListObjOutput<ActionLogEntity> result = new ListObjOutput<ActionLogEntity>();
		try {
			Pageable pageable = new PageRequest(page - 1, limit);
			result.setData(actionLogService.findAll(username, startDate, endDate, pageable));
			int totalItem = actionLogService.totalItem(username, startDate, endDate);
			int totalPage = (int) Math.ceil((double) (totalItem) / limit);
			result.setMetaData(new PagingOutput(totalPage, totalItem));
			result.setCode("200");
			result.setMessage("Get action logs success");

			return new ResponseEntity<ListObjOutput<ActionLogEntity>>(result, HttpStatus.OK);
		}catch (Exception e) {			
			result.setCode("500");
			result.setMessage("Can not get action logs");

			return new ResponseEntity<ListObjOutput<ActionLogEntity>>(result, HttpStatus.OK);
		}
		
	}
}
