package com.web_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web_service.entity.ETLEntity;

public interface ETLRequestRepository extends JpaRepository<ETLEntity, Integer>{
	ETLEntity findByRequestId(Integer requestId);
}
