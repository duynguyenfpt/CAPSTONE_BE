package com.web_service.entity.mongo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "job_logs")
public class JobLogEntity {
	@Id
	private ObjectId id;
	
	@Field("job_id")
	private Long jobId;
	
	@Field("request_id")
	private Long requestId;
	
	@Field("host")
	private String host;
	
	@Field("port")
	private String port;
	
	@Field("database_name")
	private String databaseName;
	
	@Field("table_name")
	private String tableName;
	
	@Field("step")
	private int step;
	
	@Field("request_type")
	private String requestType;
	
	@Field("number_step")
	private int numberStep;
	
	@Field("message")
	private String message;
	
	@Field("status")
	private String status;
	
	@Field("create_time")
	public String createdTime;	

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

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public int getNumberStep() {
		return numberStep;
	}

	public void setNumberStep(int numberStep) {
		this.numberStep = numberStep;
	}

	public Date getCreatedTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
		try {
			return dateFormat.parse(createdTime.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;

	}	
}
