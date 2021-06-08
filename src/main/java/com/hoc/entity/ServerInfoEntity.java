package com.hoc.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "server_infos")
public class ServerInfoEntity extends BaseEntity {
	@Column(name = "server_host")
	private String server_host;
	
	@Column(name = "server_domain")
	private String server_domain;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "serverInfo")
	private List<DatabaseInfoEntity> database_infoes = new ArrayList<>();

	public String getServer_host() {
		return server_host;
	}

	public void setServer_host(String server_host) {
		this.server_host = server_host;
	}

	public String getServer_domain() {
		return server_domain;
	}

	public void setServer_domain(String server_domain) {
		this.server_domain = server_domain;
	}

	public List<DatabaseInfoEntity> getDatabase_infoes() {
		return database_infoes;
	}

	public void setDatabase_infoes(List<DatabaseInfoEntity> database_infoes) {
		this.database_infoes = database_infoes;
	} 
	
	
}
