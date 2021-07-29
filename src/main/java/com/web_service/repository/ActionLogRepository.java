package com.web_service.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.web_service.entity.mongo.ActionLogEntity;

public interface ActionLogRepository extends MongoRepository<ActionLogEntity, ObjectId>{

}
