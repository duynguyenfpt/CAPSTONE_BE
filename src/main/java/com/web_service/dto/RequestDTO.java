package com.web_service.dto;

public class RequestDTO extends AbstractDTO<RequestDTO>{
	private String status;
	private AccountDTO creator;
	private AccountDTO approvedBy;
	private String requestType;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public AccountDTO getCreator() {
		return creator;
	}
	public void setCreator(AccountDTO creator) {
		this.creator = creator;
	}
	public AccountDTO getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(AccountDTO approvedBy) {
		this.approvedBy = approvedBy;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
}
