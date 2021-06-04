package com.hoc.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.hoc.entity.mongo.Bosses;

public interface BossesRepository extends MongoRepository<Bosses, ObjectId> {
	Bosses findById(ObjectId _id);
}
