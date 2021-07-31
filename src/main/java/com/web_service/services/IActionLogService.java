package com.web_service.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.web_service.entity.mongo.ActionLogEntity;

public interface IActionLogService {
	List<ActionLogEntity> findAll(Pageable pageable);
	int totalItem();
}
