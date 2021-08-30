package com.web_service.api;

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
import com.web_service.dto.AccountDTO;
import com.web_service.dto.DatabaseInfoDTO;
import com.web_service.dto.RequestDateStatisticDTO;
import com.web_service.dto.RequestStatisticDTO;
import com.web_service.services.IDashboardService;
import com.web_service.services.impl.DashboardService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
public class DashboardAPI {
	@Autowired
	IDashboardService dashboardService;
	
	@GetMapping(value = "/api/dashboard/statistic_request")
	public ResponseEntity<ListObjOutput<RequestStatisticDTO>> statisticRequest() {
		ListObjOutput<RequestStatisticDTO> result = new ListObjOutput<RequestStatisticDTO>();	
		try {
			result.setData(dashboardService.getTotalRequest());
			result.setMessage("Get data successfully");
	
			result.setCode("200");
	
			return new ResponseEntity<ListObjOutput<RequestStatisticDTO>>(result, HttpStatus.OK);
		}catch (Exception e) {
			result.setCode("500");
			result.setMessage("Can not get data");
			
			return new ResponseEntity<ListObjOutput<RequestStatisticDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/api/dashboard/statistic_percent_job")
	public ResponseEntity<ListObjOutput<RequestDateStatisticDTO>> statisticPercentJob(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) {
		ListObjOutput<RequestDateStatisticDTO> result = new ListObjOutput<RequestDateStatisticDTO>();
		try {
			result.setData(dashboardService.countJob(startDate, endDate));
			result.setMessage("Get data successfully");
			result.setCode("200");

			return new ResponseEntity<ListObjOutput<RequestDateStatisticDTO>>(result, HttpStatus.OK);
		}catch (Exception e) {
			result.setCode("500");
			result.setMessage("Can not get data");
			
			return new ResponseEntity<ListObjOutput<RequestDateStatisticDTO>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
