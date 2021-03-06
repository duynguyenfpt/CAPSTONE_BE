package com.web_service.dto;

public class JobDTO extends AbstractDTO<JobDTO> {

	private long requestId;
	private long executedById;
	private String jobSchedule;
	private Boolean isActive;
	private Integer maxRetries;
	private Integer numberRetries;
	private String status;
	private AccountDTO excutedBy;
	private RequestDTO request;

	public RequestDTO getRequest() {
		return request;
	}

	public void setRequest(RequestDTO request) {
		this.request = request;
	}

	public AccountDTO getExcutedBy() {
		return excutedBy;
	}

	public void setExcutedBy(AccountDTO excutedBy) {
		this.excutedBy = excutedBy;
	}

	public long getRequestId() {
		return requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}

	public long getExecutedById() {
		return executedById;
	}

	public void setExecutedById(long executedById) {
		this.executedById = executedById;
	}

	public String getJobSchedule() {
		return jobSchedule;
	}

	public void setJobSchedule(String jobSchedule) {
		this.jobSchedule = jobSchedule;
	}

	public Boolean isActive() {
		return isActive;
	}

	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}


	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getMaxRetries() {
		return maxRetries;
	}

	public void setMaxRetries(Integer maxRetries) {
		this.maxRetries = maxRetries;
	}

	public Integer getNumberRetries() {
		return numberRetries;
	}

	public void setNumberRetries(Integer numberRetries) {
		this.numberRetries = numberRetries;
	}

	
}
