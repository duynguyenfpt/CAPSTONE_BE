package com.web_service.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web_service.entity.mongo.ActionLogEntity;
import com.web_service.entity.mongo.JobLogEntity;
import com.web_service.repository.ActionLogRepository;
import com.web_service.services.IActionLogService;

@Service
public class ActionLogService  implements IActionLogService{
	@Autowired
	ActionLogRepository actionLogRepository;
	
	@Override
	public List<ActionLogEntity> findAll(Pageable pageable) {
		List<ActionLogEntity> entities = actionLogRepository.findAll(pageable).getContent();
		
		return entities;
	}

	@Override
	public int totalItem() {
		return (int) actionLogRepository.count();
	}
}
