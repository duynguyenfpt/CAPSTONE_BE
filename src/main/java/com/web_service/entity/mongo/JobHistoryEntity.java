package com.web_service.entity.mongo;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "job_history")
public class JobHistoryEntity {
	@Id
	public ObjectId id;
	
	@Field(value = "job_id")
	public long jobId;
	
	@PersistenceConstructor
	public JobHistoryEntity(ObjectId id, long jobId) {
		super();
		this.id = id;
		this.jobId = jobId;
	}
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public long getJobId() {
		return jobId;
	}
	public void setJobId(long jobId) {
		this.jobId = jobId;
	}
	
	
}
