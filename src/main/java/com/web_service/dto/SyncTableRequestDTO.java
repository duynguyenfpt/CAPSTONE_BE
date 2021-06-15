package com.web_service.dto;

import java.sql.Date;
import java.sql.Time;

public class SyncTableRequestDTO extends AbstractDTO<SyncTableRequestDTO> {
	private long tableId;
	
	private long requestId;
	
	private boolean isAll;
	
	private Date fromDate;
	
	private Date toDate;
	
	private Time timeRequest;

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

	public long getRequestId() {
		return requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}
	
	
}
