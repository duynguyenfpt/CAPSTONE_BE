package com.web_service.entity.mongo;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class NoteEntity {
	@Id
	 public ObjectId id;
	 public long requestId;
	 public String content;
	 
	 // Constructors
	 public NoteEntity() {}
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
}
