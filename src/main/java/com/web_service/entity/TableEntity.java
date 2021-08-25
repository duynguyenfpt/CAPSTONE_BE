package com.web_service.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tables")
@SQLDelete(sql = "UPDATE tables SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class TableEntity extends BaseEntity{

	@Column(name = "table_name")
	private String tableName;
	
	@Column(name = "default_key")
	private String defaultKey;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "database_info_id", nullable = false)
	private DatabaseInfoEntity databaseInfo;
	
	@Column
	private boolean deleted = Boolean.FALSE;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "tableInfo", cascade = CascadeType.ALL)
	private List<SchemaChangeHistoryEntity> schemaChangeHistories = new ArrayList<>();
	
	@JsonManagedReference
	@OneToMany(mappedBy = "tableInfo", cascade = CascadeType.ALL)
	private List<CurrentTableSchemaEntity> currentTableSchemaEntities  = new ArrayList<>();
	
//	@JsonManagedReference
//	@OneToMany(mappedBy = "tableInfo")
//	private List<SyncTableRequestEntity> syncTableRequestEntities = new ArrayList<>();
	
//	@JsonManagedReference
//	@OneToMany(mappedBy = "tableInfo")
//	private List<AddColumnTableRequestEntity> addColumnTableRequestEntities = new ArrayList<>();
	
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

	public List<CurrentTableSchemaEntity> getCurrentTableSchemaEntities() {
		return currentTableSchemaEntities;
	}

	public void setCurrentTableSchemaEntities(List<CurrentTableSchemaEntity> currentTableSchemaEntities) {
		this.currentTableSchemaEntities = currentTableSchemaEntities;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDefaultKey() {
		return defaultKey;
	}

	public void setDefaultKey(String defaultKey) {
		this.defaultKey = defaultKey;
	}

//	public List<SyncTableRequestEntity> getSyncTableRequestEntities() {
//		return syncTableRequestEntities;
//	}
//
//	public void setSyncTableRequestEntities(List<SyncTableRequestEntity> syncTableRequestEntities) {
//		this.syncTableRequestEntities = syncTableRequestEntities;
//	}

//	public List<AddColumnTableRequestEntity> getAddColumnTableRequestEntities() {
//		return addColumnTableRequestEntities;
//	}
//
//	public void setAddColumnTableRequestEntities(List<AddColumnTableRequestEntity> addColumnTableRequestEntities) {
//		this.addColumnTableRequestEntities = addColumnTableRequestEntities;
//	}
}
