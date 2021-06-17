package com.web_service.dto;

public class SchemaChangeHistoryDTO extends AbstractDTO<SchemaChangeHistoryDTO> {
	private String changeType;
	
	private String fieldChange;
	
	private String oldValue;
	
	private String newValue;
	
	private TableDTO tableInfo;

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public String getFieldChange() {
		return fieldChange;
	}

	public void setFieldChange(String fieldChange) {
		this.fieldChange = fieldChange;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public TableDTO getTableInfo() {
		return tableInfo;
	}

	public void setTableInfo(TableDTO tableInfo) {
		this.tableInfo = tableInfo;
	}

	
	
	
}
