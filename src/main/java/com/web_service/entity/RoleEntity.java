package com.web_service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

