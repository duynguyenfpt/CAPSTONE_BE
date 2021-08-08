package com.web_service.services.impl;

import java.util.ArrayList;
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
	public List<ActionLogEntity> findAll(String username, String path,
			String bodyRequest, String requestMethod,
			Integer statusCode, Pageable pageable) {
		Query query = new Query().with(pageable).with(new Sort(Sort.Direction.DESC, "create_time"));
		
		final List<Criteria> criteria = createFilter(username, path, bodyRequest, requestMethod, statusCode);		

		if(!criteria.isEmpty()) {
			query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[0])));
		}
		
		return PageableExecutionUtils.getPage(mongoTemplate.find(query, ActionLogEntity.class),
				pageable,
				() -> mongoTemplate.count(query.skip(0).limit(0), ActionLogEntity.class)).getContent();		
	}

	@Override
	public int totalItem(String username, String path, String bodyRequest, String requestMethod, Integer statusCode) {
		Query query = new Query();
		final List<Criteria> criteria = createFilter(username, path, bodyRequest, requestMethod, statusCode);
		
		if(!criteria.isEmpty()) {
			query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[0])));
		}
		
		int totalItem = mongoTemplate.find(query, ActionLogEntity.class).size();
		
		return totalItem;
	}
	
	private List<Criteria> createFilter(String username, String path, String bodyRequest,
			String requestMethod, Integer statusCode){
		final List<Criteria> criteria = new ArrayList<>();		
		if(username != null) {
			criteria.add(Criteria.where("user_name").regex(username, "i"));
		}
		
		if(path != null) {
			criteria.add(Criteria.where("path").regex(path, "i"));
		}
		
		if(requestMethod != null) {
			criteria.add(Criteria.where("request_method").regex(requestMethod, "i"));
		}
		
		if(bodyRequest != null) {
			criteria.add(Criteria.where("body_request").regex(bodyRequest, "i"));
		}
		
		if(statusCode != null) {
			criteria.add(Criteria.where("status_code").is(statusCode));
		}
		
		return criteria;
	}
}
