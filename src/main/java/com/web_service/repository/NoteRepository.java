package com.web_service.repository;



import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.web_service.entity.mongo.NoteEntity;


public interface NoteRepository extends MongoRepository<NoteEntity, ObjectId> {
	NoteEntity findById(ObjectId _id);
	
	Page<NoteEntity> findByRequestId(long requestId, Pageable pageable);
	
	List<NoteEntity> getAllNoteByRequestId(long requestId);

}
