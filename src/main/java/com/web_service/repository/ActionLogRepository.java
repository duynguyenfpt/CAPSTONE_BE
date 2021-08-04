package com.web_service.repository;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.web_service.entity.mongo.ActionLogEntity;

public interface ActionLogRepository extends MongoRepository<ActionLogEntity, ObjectId>{
	Page<ActionLogEntity> findAllByOrderByCreatedAtDesc(Pageable pageable);

}
