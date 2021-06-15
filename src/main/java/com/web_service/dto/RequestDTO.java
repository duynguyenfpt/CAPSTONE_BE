package com.web_service.dto;

public class RequestDTO extends AbstractDTO<RequestDTO>{
	private String status;
	private AccountDTO creator;
	private AccountDTO approvedBy;
	private String requestType;
	private Long creatorId;
	private Long approvedById;
	
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
	public Long getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	public Long getApprovedById() {
		return approvedById;
	}
	public void setApprovedById(Long approvedById) {
		this.approvedById = approvedById;
	}
}