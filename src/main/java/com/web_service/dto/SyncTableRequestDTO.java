package com.web_service.dto;

import java.sql.Date;
import java.sql.Time;

public class SyncTableRequestDTO extends AbstractDTO<SyncTableRequestDTO> {
	private long tableId;
		
	private boolean isAll;
	
	private Date fromDate;
	
	private Date toDate;
	
	private Time timeRequest;
	
	private String partitionBy;
	
	private String identityId;
	


	public long getTableId() {
		return tableId;
	}

	public void setTableId(long tableId) {
		this.tableId = tableId;
	}

	public boolean isAll() {
		return isAll;
	}

	public void setIsAll(boolean isAll) {
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
