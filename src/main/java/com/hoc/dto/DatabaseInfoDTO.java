package com.hoc.dto;

import java.util.ArrayList;
import java.util.List;

import com.hoc.entity.TableEntity;

public class DatabaseInfoDTO extends AbstractDTO<DatabaseInfoDTO> {
	private String port;
	
	private String username;
	
	private String password;
	
	private String databaseName;
	
	private String databaseType;
	
	private List<TableEntity> tables = new ArrayList<>();

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}

	public List<TableEntity> getTables() {
		return tables;
	}

	public void setTables(List<TableEntity> tables) {
		this.tables = tables;
	} 
	
	

}
