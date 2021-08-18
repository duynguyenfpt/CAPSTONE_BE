package com.web_service.dto;

public class MergeRequestDTO {
	private Long id;
	
	private String currentMetadata;
	
	private String latestMetadata;
	
	private String mergeTableName;
	
	private RequestDTO request;
	
	private Long requestId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCurrentMetadata() {
		return currentMetadata;
	}

	public void setCurrentMetadata(String currentMetadata) {
		this.currentMetadata = currentMetadata;
	}

	public String getLatestMetadata() {
		return latestMetadata;
	}

	public void setLatestMetadata(String latestMetadata) {
		this.latestMetadata = latestMetadata;
	}

	public String getMergeTableName() {
		return mergeTableName;
	}

	public void setMergeTableName(String mergeTableName) {
		this.mergeTableName = mergeTableName;
	}

	public RequestDTO getRequest() {
		return request;
	}

	public void setRequest(RequestDTO request) {
		this.request = request;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}
}
