package com.web_service.dto;

public class CurrentTableSchemaDTO extends AbstractDTO<CurrentTableSchemaDTO> {
	private String columnName;
	
	private String columnType;
	
	private String typeSize;
	
	private String defaultValue;
	
	private String possibleValue;


	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getTypeSize() {
		return typeSize;
	}

	public void setTypeSize(String typeSize) {
		this.typeSize = typeSize;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getPossibleValue() {
		return possibleValue;
	}

	public void setPossibleValue(String possibleValue) {
		this.possibleValue = possibleValue;
	}
	
	
}
