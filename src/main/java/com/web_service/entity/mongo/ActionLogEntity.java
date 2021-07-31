package com.web_service.entity.mongo;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "action_logs")
public class ActionLogEntity {
	@Id
	private ObjectId id;
	
	@Field("user_name")
	private String userName = "";
	
	@Field("path")
	private String path;
	
	@Field("created_at")
	private Date createdAt = new Date();
	
	@Field("body_request")
	private String bodyRequest;
	
	@Field("response")
	private Object response;
	
	@Field("request_method")
	private String requestMethod;
	
	@Field("status_code")
	private int statusCode;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreatedAt() {
		return createdAt; 
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getBodyRequest() {
		return bodyRequest;
	}

	public void setBodyRequest(String bodyRequest) {
		this.bodyRequest = bodyRequest;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	
}
