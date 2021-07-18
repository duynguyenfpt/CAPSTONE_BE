package com.web_service.dto;

import java.sql.Date;
import java.sql.Time;

public class RequestDTO extends AbstractDTO<RequestDTO>{
	private String status;
	private AccountDTO creator;
	private AccountDTO approvedBy;
	private String requestType;
	private Long creatorId;
	private Long approvedById;
	private long tableId;
	private boolean isAll;
	private Date fromDate;
	private Date toDate;
	private String partitionBy;
	private String identityId;
	private long[] columnIds;

	
	public long[] getColumnIds() {
		return columnIds;
	}
	public void setColumnIds(long[] columnIds) {
		this.columnIds = columnIds;
	}
	public long getTableId() {
		return tableId;
	}
	public void setTableId(long tableId) {
		this.tableId = tableId;
	}
	public boolean isAll() {
		return isAll;
	}
	public void setAll(boolean isAll) {
		this.isAll = isAll;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Time getTimeRequest() {
		return timeRequest;
	}
	public void setTimeRequest(Time timeRequest) {
		this.timeRequest = timeRequest;
	}
	private Time timeRequest;
	
	
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
	public String getPartitionBy() {
		return partitionBy;
	}
	public void setPartitionBy(String partitionBy) {
		this.partitionBy = partitionBy;
	}
	public String getIdentityId() {
		return identityId;
	}
	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}
}
