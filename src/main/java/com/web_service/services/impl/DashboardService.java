package com.web_service.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.web_service.dto.RequestDateStatisticDTO;
import com.web_service.dto.RequestStatisticDTO;
import com.web_service.entity.mongo.JobLogEntity;
import com.web_service.repository.JobLogRepository;
import com.web_service.repository.JobRepository;
import com.web_service.repository.RequestRepository;
import com.web_service.services.IDashboardService;

@Service
public class DashboardService implements IDashboardService{
	@Autowired
	RequestRepository requestRepository;
	
	@Autowired
    private MongoTemplate mongoTemplate;
	
	@Autowired
	private JobLogRepository jobLogRepository;
	
	@Autowired
	private JobRepository jobRepository;
	
	@Override
	public List<RequestStatisticDTO> getTotalRequest() {
		List<RequestStatisticDTO> listRequestStatistic = new ArrayList<>();
		int pendingRequest =  requestRepository.countRequest("0");
		listRequestStatistic.add(new RequestStatisticDTO("pending", pendingRequest));
		int approvedRequest = requestRepository.countRequest("1");
		listRequestStatistic.add(new RequestStatisticDTO("approved", approvedRequest));
		int rejectedRequest = requestRepository.countRequest("2");
		listRequestStatistic.add(new RequestStatisticDTO("rejected", rejectedRequest));
		int successJob = jobRepository.countJobByStatus("success");
		listRequestStatistic.add(new RequestStatisticDTO("success", successJob));
		int failJob = jobRepository.countJobByStatus("fail");
		listRequestStatistic.add(new RequestStatisticDTO("fail", failJob));
		int totalJob = jobRepository.countJob("");
		int processingJob = totalJob - failJob - successJob;
		listRequestStatistic.add(new RequestStatisticDTO("proccessing", processingJob));
		
		return listRequestStatistic;
	}

	@Override
	public List<RequestDateStatisticDTO> countJob(String startDate, String endDate) {
		List<RequestDateStatisticDTO> listRequestDateStatistic = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date startDateConvert = format.parse(startDate);
			Date endDateConvert = format.parse(endDate);
			
			while(startDateConvert.compareTo(endDateConvert) <= 0) {
				String a = format.format(startDateConvert);
				int successJob = jobRepository.countJobByCreatedDate(format.format(startDateConvert), "success");
				int totalJob = jobRepository.countJobByCreatedDate(format.format(startDateConvert), "");
				int value = 0;
				if(totalJob != 0) {
					value = (int) ((double)successJob/totalJob * 100);
				}
				listRequestDateStatistic.add(new RequestDateStatisticDTO(startDateConvert, value));
				Calendar cal = Calendar.getInstance();
				cal.setTime(startDateConvert);
				cal.add(Calendar.DAY_OF_MONTH, 1);
				startDateConvert = cal.getTime();
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listRequestDateStatistic;
	}
	

}
