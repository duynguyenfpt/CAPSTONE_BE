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
	@Column(name = "column_name")
	private String columnName;
	
	@Column(name = "column_type")
	private String columnType;
	
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

	public String getTypeSize() {
		return typeSize;
	}

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
