package com.web_service.dto;

public class ShareETLRequestDTO {
	private Long requestId;
	
	private Long[] accountIds;

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public Long[] getAccountIds() {
		return accountIds;
	}

	public void setAccountIds(Long[] accountIds) {
		this.accountIds = accountIds;
	}
}
