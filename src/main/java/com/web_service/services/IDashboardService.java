package com.web_service.services;

import java.util.List;

import com.web_service.dto.RequestDateStatisticDTO;
import com.web_service.dto.RequestStatisticDTO;

public interface IDashboardService {
	List<RequestStatisticDTO> getTotalRequest();
	List<RequestDateStatisticDTO> countJob(String startDate, String endDate);
}
