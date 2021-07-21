package com.web_service.entity.mongo;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "job_logs")
public class JobLogEntity {
	@Id
	public ObjectId id;
	
	@Field("job_id")
	public Long jobId;
	
	@Field("message")
	public String message;
	
	@Field("status")
	public String status;
	
	@Field("created_date")
	public Date createdDate = new Date();	

	// ObjectId needs to be converted to string
	public ObjectId get_id() {
		return id;
	}

	public void set_id(ObjectId id) {
		this.id = id;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
