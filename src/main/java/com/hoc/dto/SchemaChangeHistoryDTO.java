package com.hoc.dto;

import javax.persistence.Column;

public class SchemaChangeHistoryDTO extends AbstractDTO<SchemaChangeHistoryDTO> {
	private String change_type;
	
	private String field_change;
	
	private String old_value;
	
	private String new_value;

	public String getChange_type() {
		return change_type;
	}

	public void setChange_type(String change_type) {
		this.change_type = change_type;
	}

	public String getField_change() {
		return field_change;
	}

	public void setField_change(String field_change) {
		this.field_change = field_change;
	}

	public String getOld_value() {
		return old_value;
	}

	public void setOld_value(String old_value) {
		this.old_value = old_value;
	}

	public String getNew_value() {
		return new_value;
	}

	public void setNew_value(String new_value) {
		this.new_value = new_value;
	}
	
	
}
