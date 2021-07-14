package com.web_service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web_service.api.output.ListObjOutput;
import com.web_service.api.output.PagingOutput;
import com.web_service.dto.RightDTO;
import com.web_service.services.IRightService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
public class RightAPI {

	@Autowired
	private IRightService rightService;

	@GetMapping(value = "/api/rights")
	public ResponseEntity<ListObjOutput<RightDTO>> showRights(@RequestParam("page") int page,
								@RequestParam("limit") int limit) {
		
		ListObjOutput<RightDTO> result = new ListObjOutput<RightDTO>();
		Pageable pageable = new PageRequest(page - 1, limit);
		result.setData(rightService.findAll(pageable));
		int totalPage = (int) Math.ceil((double) (rightService.totalItem()) / limit);
		int totalItem = rightService.totalItem();
		result.setMetaData(new PagingOutput(totalPage, totalItem));
		result.setCode("200");

		return new ResponseEntity<ListObjOutput<RightDTO>>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/api/accounts/{account_id}/rights")
	public ResponseEntity<ListObjOutput<RightDTO>> showRightByAccountId(@RequestParam("page") int page,
								@RequestParam("limit") int limit, @PathVariable("account_id") long accountId) {
		
		ListObjOutput<RightDTO> result = new ListObjOutput<RightDTO>();
		Pageable pageable = new PageRequest(page - 1, limit);
		result.setData(rightService.findAllByAccountId(accountId, pageable));
		int totalPage = (int) Math.ceil((double) (rightService.countRightByAccountId(accountId)) / limit);
		int totalItem = rightService.countRightByAccountId(accountId);
		result.setMetaData(new PagingOutput(totalPage, totalItem));
		result.setCode("200");

		return new ResponseEntity<ListObjOutput<RightDTO>>(result, HttpStatus.OK);
	}
}
