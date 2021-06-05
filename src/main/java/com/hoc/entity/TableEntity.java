package com.hoc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tables")
public class TableEntity extends BaseEntity{

	@Column(name = "table_name")
	private String table_name;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "database_info_id", nullable = false)
	private DatabaseInfoEntity database_info;

	public String getTableName() {
		return table_name;
	}

	public void setTableName(String table_name) {
		this.table_name = table_name;
	}

	public DatabaseInfoEntity getDatabase_info() {
		return database_info;
	}

	public void setDatabase_info(DatabaseInfoEntity database_info) {
		this.database_info = database_info;
	}
}
