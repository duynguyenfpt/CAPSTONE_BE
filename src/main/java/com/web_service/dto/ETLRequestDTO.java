package com.web_service.dto;

import java.util.Date;

import javax.persistence.Column;

public class ETLRequestDTO extends AbstractDTO<ETLRequestDTO>{
	private String query;
	
	private String queryType;
	
	private Boolean resultStatus;
	
	private Long requestId;
	
	private RequestDTO request;
	
	private int maxRetries;
	
	private int numberRetries;
		
	private String status;
	
	private Date createdDate;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public Boolean getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(Boolean resultStatus) {
		this.resultStatus = resultStatus;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public RequestDTO getRequest() {
		return request;
	}

	public void setRequest(RequestDTO request) {
		this.request = request;
	}

	public int getMaxRetries() {
		return maxRetries;
	}

	public void setMaxRetries(int maxRetries) {
		this.maxRetries = maxRetries;
	}

	public int getNumberRetries() {
		return numberRetries;
	}

	public void setNumberRetries(int numberRetries) {
		this.numberRetries = numberRetries;
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
