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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "database_infos")
@SQLDelete(sql = "UPDATE database_infos SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
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
	
	@Column(name = "sid")
	private String sid;
	
	@Column
	private boolean deleted = Boolean.FALSE;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "databaseInfo", cascade = CascadeType.ALL)
	private List<TableEntity> tables = new ArrayList<>();
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "server_info_id", nullable = false)
	private ServerInfoEntity serverInfo;

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

	public ServerInfoEntity getServerInfo() {
		return serverInfo;
	}

	public void setServerInfo(ServerInfoEntity serverInfo) {
		this.serverInfo = serverInfo;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}
}
