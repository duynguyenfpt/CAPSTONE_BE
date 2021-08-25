package com.web_service.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "rights")
@SQLDelete(sql = "UPDATE rights SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class RightEntity extends BaseEntity{
	@NotBlank
	@Column(name = "path")
	private String path;
	
	@NotBlank
	@Column(name = "method")
	private String method;
	
	@NotBlank
	@Column(name = "description")
	private String description;
	
	@Column
	private boolean deleted = Boolean.FALSE;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "right")
	private List<AccountRightEntity> accountRights = new ArrayList<>();

	public List<AccountRightEntity> getAccountRights() {
		return accountRights;
	}

	public void setAccountRights(List<AccountRightEntity> accountRights) {
		this.accountRights = accountRights;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
