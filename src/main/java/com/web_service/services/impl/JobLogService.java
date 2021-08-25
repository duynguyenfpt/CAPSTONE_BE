package com.web_service.services.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.web_service.entity.mongo.JobLogEntity;
import com.web_service.repository.JobLogRepository;
import com.web_service.services.IJobLogService;

@Service
public class JobLogService implements IJobLogService{

	@Autowired
	private JobLogRepository jobLogRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	
	@Override
	public JobLogEntity save(JobLogEntity jobLogEntity) {
		jobLogEntity = jobLogRepository.save(jobLogEntity);
		return jobLogEntity;
	}

	@Override
	public List<JobLogEntity> findAll(String host, String port, String databaseName, String tableName,
			String requestType, String status, Pageable pageable) {
		//create query
		Query query = new Query().with(pageable).with(new Sort(Sort.Direction.DESC, "create_time"));
		
		//add condition for query
		final List<Criteria> criteria = createFilter(host, port, databaseName, tableName, requestType, status);		

		if(!criteria.isEmpty()) {
			query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[0])));
		}
		
		return PageableExecutionUtils.getPage(mongoTemplate.find(query, JobLogEntity.class),
				pageable,
				() -> mongoTemplate.count(query.skip(0).limit(0), JobLogEntity.class)).getContent();		
	}

	@Override
	public int totalItem(String host, String port, String databaseName, String tableName,
			String requestType, String status) {
		Query query = new Query();
		final List<Criteria> criteria = createFilter(host, port, databaseName, tableName, requestType, status);
		
		if(!criteria.isEmpty()) {
			query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[0])));
		}
		
		int totalItem = mongoTemplate.find(query, JobLogEntity.class).size();
		
		return totalItem;
	}

	@Override
	public List<JobLogEntity> findAllByJobId(long jobId, Pageable pageable) {
		List<JobLogEntity> entities = jobLogRepository.findByJobIdOrderByCreatedTimeDesc(jobId, pageable).getContent();
		
		return entities;
	}

	@Override
	public int totalItemByJobId(long jobId) {
		return jobLogRepository.getAllJobLogByJobId(jobId).size();
	}

	@Override
	public JobLogEntity getLastJobLog(long jobId) {
		//get last log of job
		List<JobLogEntity> entities = jobLogRepository.getAllJobLogByJobId(jobId);
		if(entities.isEmpty()) return null;
		JobLogEntity lastJobLog = entities.stream().filter(e -> e.getStatus() != "processing")
										  .max(Comparator.comparing(JobLogEntity::getCreatedTime)).get();
		
		return lastJobLog;
	}
	
	private List<Criteria> createFilter(String host, String port, String databaseName, String tableName,
			String requestType, String status){
		final List<Criteria> criteria = new ArrayList<>();
		
		if(host != null) {
			criteria.add(Criteria.where("host").regex(host, "i"));
		}
		
		if(databaseName != null) {
			criteria.add(Criteria.where("database_name").regex(databaseName, "i"));
		}
		
		if(tableName != null) {
			criteria.add(Criteria.where("table_name").regex(tableName, "i"));
		}
		
		if(port != null) {
			criteria.add(Criteria.where("port").regex(port, "i"));
		}
		
		if(requestType != null) {
			criteria.add(Criteria.where("request_type").regex(requestType, "i"));
		}
		
		if(status != null) {
			criteria.add(Criteria.where("status").regex(status, "i"));
		}
		
		return criteria;
	}
}
