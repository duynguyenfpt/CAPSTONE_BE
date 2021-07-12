package com.web_service.dto;

import org.bson.types.ObjectId;

public class NoteDTO {
	private ObjectId id;
	private String content;
	private long requestId;
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getRequestId() {
		return requestId;
	}
	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}
	
	
	
}
