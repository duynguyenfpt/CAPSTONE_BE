package com.web_service.dto;


import java.sql.Date;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class JobDTO extends AbstractDTO<JobDTO>{
	 
	private long requestId;
	private long executedById;
	private String jobSchedule;
	private boolean isActive;
	private int maxRetry;
	private AccountDTO excutedBy;
	
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
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public int getMaxRetry() {
		return maxRetry;
	}
	public void setMaxRetry(int maxRetry) {
		this.maxRetry = maxRetry;
	}
}
