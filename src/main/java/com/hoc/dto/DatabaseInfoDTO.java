package com.hoc.dto;

import java.util.ArrayList;
import java.util.List;

import com.hoc.entity.ServerInfoEntity;
import com.hoc.entity.TableEntity;

public class DatabaseInfoDTO extends AbstractDTO<DatabaseInfoDTO> {
	private String host;
	
	private String port;
	
	private String username;
	
	private String password;
	
	private String database_name;
	
	private String database_type;
	
	private Long server_infor_id;
	
	private List<TableEntity> tables = new ArrayList<>();
	
	private ServerInfoDTO server_infor;

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

	public String getDatabase_name() {
		return database_name;
	}

	public void setDatabase_name(String database_name) {
		this.database_name = database_name;
	}

	public String getDatabase_type() {
		return database_type;
	}

	public void setDatabase_type(String database_type) {
		this.database_type = database_type;
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

	public Long getServer_infor_id() {
		return server_infor_id;
	}

	public void setServer_infor_id(Long server_infor_id) {
		this.server_infor_id = server_infor_id;
	}

	public ServerInfoDTO getServer_infor() {
		return server_infor;
	}

	public void setServer_infor(ServerInfoDTO server_infor) {
		this.server_infor = server_infor;
	} 
}
