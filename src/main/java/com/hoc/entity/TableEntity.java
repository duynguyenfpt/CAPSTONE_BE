package com.hoc.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tables")
public class TableEntity extends BaseEntity{

	@Column(name = "table_name")
	private String table_name;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "database_info_id", nullable = false)
	private DatabaseInfoEntity databaseInfo;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "tableInfo")
	private List<SchemaChangeHistoryEntity> schemaChangeHistories = new ArrayList<>();

	public String getTableName() {
		return table_name;
	}

	public void setTableName(String table_name) {
		this.table_name = table_name;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public DatabaseInfoEntity getDatabaseInfo() {
		return databaseInfo;
	}

	public void setDatabaseInfo(DatabaseInfoEntity databaseInfo) {
		this.databaseInfo = databaseInfo;
	}

	public List<SchemaChangeHistoryEntity> getSchemaChangeHistories() {
		return schemaChangeHistories;
	}

	public void setSchemaChangeHistories(List<SchemaChangeHistoryEntity> schemaChangeHistories) {
		this.schemaChangeHistories = schemaChangeHistories;
	}
	
	
}
