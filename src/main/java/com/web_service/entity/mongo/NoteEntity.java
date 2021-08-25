package com.web_service.entity.mongo;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "notes")
public class NoteEntity {
	@Id
	private ObjectId id;
	
	@Field("request_id")
	private long requestId;

	@Field("content")
	private String content;

	@Field("created_by")
	private String createdBy;
	
	@Field("created_at")
	private Date createdAt;

	// Constructors
	public NoteEntity() {
	}

	public NoteEntity(ObjectId id, long requestId, String content) {
		this.id = id;
		this.requestId = requestId;
		this.content = content;
	}

	// ObjectId needs to be converted to string
	public ObjectId get_id() {
		return id;
	}

	public void set_id(ObjectId _id) {
		this.id = _id;
	}

	public long getRequestId() {
		return requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
