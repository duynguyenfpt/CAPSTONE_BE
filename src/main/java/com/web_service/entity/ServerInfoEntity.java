package com.web_service.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "server_infors")
public class ServerInfoEntity extends BaseEntity {
	@Column(name = "server_host")
	private String serverHost;
	
	@Column(name = "server_domain")
	private String serverDomain;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "serverInfo", fetch=FetchType.EAGER)
	private List<DatabaseInfoEntity> databaseInfoes = new ArrayList<>();

	public String getServerHost() {
		return serverHost;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

	public String getServerDomain() {
		return serverDomain;
	}

	public void setServerDomain(String serverDomain) {
		this.serverDomain = serverDomain;
	}

	public List<DatabaseInfoEntity> getDatabaseInfoes() {
		return databaseInfoes;
	}

	public void setDatabaseInfoes(List<DatabaseInfoEntity> databaseInfoes) {
		this.databaseInfoes = databaseInfoes;
	}
}
