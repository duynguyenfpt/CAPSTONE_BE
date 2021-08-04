package com.web_service.dto;

import java.util.ArrayList;
import java.util.List;

import com.web_service.entity.TableEntity;

public class DatabaseInfoDTO extends AbstractDTO<DatabaseInfoDTO> {
	private String host;
	
	private String port;
	
	private String username;
	
	private String password;
	
	private String databaseName;
	
	private String databaseType;
	
	private Long serverInforId;
	
	private List<TableEntity> tables = new ArrayList<>();
	
	private ServerInfoDTO serverInfor;
	
	private String sid;

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

	public List<TableEntity> getTables() {
		return tables;
	}

	public void setTables(List<TableEntity> tables) {
		this.tables = tables;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
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

	public Long getServerInforId() {
		return serverInforId;
	}

	public void setServerInforId(Long serverInforId) {
		this.serverInforId = serverInforId;
	}

	public ServerInfoDTO getServerInfor() {
		return serverInfor;
	}

	public void setServerInfor(ServerInfoDTO serverInfor) {
		this.serverInfor = serverInfor;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}
}
