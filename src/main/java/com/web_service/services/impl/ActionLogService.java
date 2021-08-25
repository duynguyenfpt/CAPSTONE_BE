
package com.web_service.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import com.web_service.entity.mongo.ActionLogEntity;
import com.web_service.repository.ActionLogRepository;
import com.web_service.services.IActionLogService;

@Service
public class ActionLogService  implements IActionLogService{
	@Autowired
	ActionLogRepository actionLogRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public List<ActionLogEntity> findAll(String username, String startDate, String endDate, Pageable pageable) {
		//Create query
		Query query = new Query().with(pageable).with(new Sort(Sort.Direction.DESC, "created_at"));
		
		//Add condition for query
		final List<Criteria> criteria = createFilter(username, startDate, endDate);		

		if(!criteria.isEmpty()) {
			query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[0])));
		}
		
		return PageableExecutionUtils.getPage(mongoTemplate.find(query, ActionLogEntity.class),
				pageable,
				() -> mongoTemplate.count(query.skip(0).limit(0), ActionLogEntity.class)).getContent();		
	}

	@Override
	public int totalItem(String username, String startDate, String endDate) {
		Query query = new Query();
		final List<Criteria> criteria = createFilter(username, startDate, endDate);
		
		//Add condition for query
		if(!criteria.isEmpty()) {
			query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[0])));
		}
		
		int totalItem = mongoTemplate.find(query, ActionLogEntity.class).size();
		
		return totalItem;
	}
	
	private List<Criteria> createFilter(String username, String startDate, String endDate){
		final List<Criteria> criteria = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(username != null) {
			criteria.add(Criteria.where("user_name").regex(username, "i"));
		}
		
		if(startDate != null) {
			try {
				Date startDateConvert = format.parse(startDate);
				criteria.add(Criteria.where("created_at").gte(startDateConvert));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(endDate != null) {
			try {
				Date endDateConvert = format.parse(endDate);
				criteria.add(Criteria.where("created_at").lt(endDateConvert));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return criteria;
	}
}
