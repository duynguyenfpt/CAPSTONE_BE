package com.web_service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "current_table_schemas")
public class CurrentTableSchemaEntity extends BaseEntity {
	@Column
	private String row_name;
	
	@Column
	private String row_type;
	
	@Column
	private String type_size;
	
	@Column
	private String default_value;
	
	@Column
	private String possible_value;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "table_info_id", nullable = false)
	private TableEntity tableInfo;

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
