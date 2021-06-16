package com.web_service.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "current_table_schemas")
public class CurrentTableSchemaEntity extends BaseEntity {
	@Column
	private String rowName;
	
	@Column
	private String rowType;
	
	@Column
	private String typeSize;
	
	@Column
	private String defaultValue;
	
	@Column
	private String possibleValue;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "table_info_id", nullable = false)
	private TableEntity tableInfo;
	
//	@JsonManagedReference
//	@OneToMany(mappedBy = "currentTableSchema")
//	private List<AddColumnDetailEntity> addColumnDetails = new ArrayList<>();
//
//	
//	public List<AddColumnDetailEntity> getAddColumnDetails() {
//		return addColumnDetails;
//	}
//
//	public void setAddColumnDetails(List<AddColumnDetailEntity> addColumnDetails) {
//		this.addColumnDetails = addColumnDetails;
//	}

	public String getRowName() {
		return rowName;
	}

	public void setRowName(String rowName) {
		this.rowName = rowName;
	}

	public String getRowType() {
		return rowType;
	}

	public void setRowType(String rowType) {
		this.rowType = rowType;
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

	public TableEntity getTableInfo() {
		return tableInfo;
	}

	public void setTableInfo(TableEntity tableInfo) {
		this.tableInfo = tableInfo;
	}

	
}
