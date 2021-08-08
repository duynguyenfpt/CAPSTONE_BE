package com.web_service.dto;

public class ETLRequestDTO extends AbstractDTO<ETLRequestDTO>{
	private String query;
	
	private String queryType;
	
	private Boolean resultStatus;
	
	private Long requestId;
	
	private RequestDTO request;

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
}
