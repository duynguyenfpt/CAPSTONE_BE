package com.hoc.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "database_infos")
public class DatabaseInfoEntity extends BaseEntity{
	@Column
	private String port;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column(name = "database_name")
	private String databaseName;
	
	@Column(name = "database_type")
	private String databaseType;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "database_info")
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
