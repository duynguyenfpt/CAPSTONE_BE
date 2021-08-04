package com.web_service.services;

import com.web_service.entity.ETLEntity;

public interface IETLService {
	ETLEntity getResult(Integer requestId);
}
