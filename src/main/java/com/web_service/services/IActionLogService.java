package com.web_service.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.web_service.entity.mongo.ActionLogEntity;

public interface IActionLogService {
	List<ActionLogEntity> findAll(String username, String path,
			String bodyRequest, String requestMethod,
			Integer statusCode, Pageable pageable);
	int totalItem(String username, String path,
			String bodyRequest,
			String requestMethod, Integer statusCode);
}
