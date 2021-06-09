package com.hoc.dto;

public class CurrentTableSchemaDTO extends AbstractDTO<CurrentTableSchemaDTO> {
	private String row_name;
	
	private String row_type;
	
	private String type_size;
	
	private String default_value;
	
	private String possible_value;

	public String getRow_name() {
		return row_name;
	}

	public void setRow_name(String row_name) {
		this.row_name = row_name;
	}

	public String getRow_type() {
		return row_type;
	}

	public void setRow_type(String row_type) {
		this.row_type = row_type;
	}

	public String getType_size() {
		return type_size;
	}

	public void setType_size(String type_size) {
		this.type_size = type_size;
	}

	public String getDefault_value() {
		return default_value;
	}

	public void setDefault_value(String default_value) {
		this.default_value = default_value;
	}

	public String getPossible_value() {
		return possible_value;
	}

	public void setPossible_value(String possible_value) {
		this.possible_value = possible_value;
	}
	
	
}
