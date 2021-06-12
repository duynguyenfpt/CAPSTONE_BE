package com.web_service.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.web_service.entity.mongo.Bosses;

public interface BossesRepository extends MongoRepository<Bosses, ObjectId> {
	Bosses findById(ObjectId _id);
}
