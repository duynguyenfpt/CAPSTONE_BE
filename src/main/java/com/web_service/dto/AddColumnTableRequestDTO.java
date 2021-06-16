package com.web_service.dto;

public class AddColumnTableRequestDTO extends AbstractDTO<AddColumnTableRequestDTO>{
	private long requestTypeId;
	private long tableId;
	private long[] rowIds;
	public long getRequestTypeId() {
		return requestTypeId;
	}
	public void setRequestTypeId(long requestTypeId) {
		this.requestTypeId = requestTypeId;
	}
	public long[] getRowIds() {
		return rowIds;
	}
	public void setRowIds(long[] rowIds) {
		this.rowIds = rowIds;
	}
	public long getTableId() {
		return tableId;
	}
	public void setTableId(long tableId) {
		this.tableId = tableId;
	}

	
}
