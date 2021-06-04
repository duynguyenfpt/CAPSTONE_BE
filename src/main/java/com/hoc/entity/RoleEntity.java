package com.hoc.entity;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;
	

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}
	
//	@ManyToMany(mappedBy = "roles")
//	private List<UserEntity> users = new ArrayList<>();


}

