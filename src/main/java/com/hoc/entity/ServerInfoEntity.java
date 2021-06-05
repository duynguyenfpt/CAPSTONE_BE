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
	@OneToMany(mappedBy = "server_info")
	private List<DatabaseInfoEntity> database_infoes = new ArrayList<>(); 
}
