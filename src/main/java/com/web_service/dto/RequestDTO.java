package com.web_service.dto;

import java.sql.Date;
import java.sql.Time;

public class RequestDTO extends AbstractDTO<RequestDTO>{
	private String status;
	private String approvedBy;
	private String requestType;
	private long tableId;
	private boolean isAll;
	private Date fromDate;
	private Date toDate;
	private String partitionBy;
	private String identityId;
	private long[] columnIds;
	private String description;

	
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
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
