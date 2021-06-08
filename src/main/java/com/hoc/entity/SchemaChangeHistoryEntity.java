package com.hoc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "schema_change_histories")
public class SchemaChangeHistoryEntity extends BaseEntity{
	
	@Column(name = "change_type")
	private String change_type;
	
	@Column(name = "field_change")
	private String field_change;
	
	@Column(name = "old_value")
	private String old_value;
	
	@Column(name = "new_value")
	private String new_value;
	
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "table_info_id", nullable = false)
	private TableEntity tableInfo;


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


	public TableEntity getTableInfo() {
		return tableInfo;
	}

	public void setTableInfo(TableEntity tableInfo) {
		this.tableInfo = tableInfo;
	}
}
