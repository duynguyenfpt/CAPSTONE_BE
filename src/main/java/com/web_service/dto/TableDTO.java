package com.web_service.dto;

import java.util.List;

import com.web_service.entity.CurrentTableSchemaEntity;

public class TableDTO extends AbstractDTO<TableDTO>{
	private String tableName;
	private Long databaseInforId;
	private List<CurrentTableSchemaEntity> currentTableSchemas;
	private DatabaseInfoDTO databaseInfo;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Long getDatabaseInforId() {
		return databaseInforId;
	}
	public void setDatabaseInforId(Long databaseInforId) {
		this.databaseInforId = databaseInforId;
	}
	public List<CurrentTableSchemaEntity> getCurrentTableSchemas() {
		return currentTableSchemas;
	}
	public void setCurrentTableSchemas(List<CurrentTableSchemaEntity> currentTableSchemas) {
		this.currentTableSchemas = currentTableSchemas;
	}
	public DatabaseInfoDTO getDatabaseInfo() {
		return databaseInfo;
	}
	public void setDatabaseInfo(DatabaseInfoDTO databaseInfo) {
		this.databaseInfo = databaseInfo;
	}
}
