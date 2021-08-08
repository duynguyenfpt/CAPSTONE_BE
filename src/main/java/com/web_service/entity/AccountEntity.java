package com.web_service.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "accounts",
	   uniqueConstraints = {
			   @UniqueConstraint(columnNames = "username"),
			   @UniqueConstraint(columnNames = "email")
	   })

public class AccountEntity extends BaseEntity {
	
	@NotBlank
	@Size(max = 20)
	@Column(name = "username")
	private String username;
	
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	
	@NotBlank
	@JsonIgnore
	@Size(max = 120)
	private String password;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "role")
	private String role;
	
	@Column(name = "active")
	private Boolean active = true;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "executedBy")
    private List<JobEntity> listJob;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "account")
	private List<AccountRightEntity> accountRights = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "accounts")
	private List<ETLEntity> etlRequest;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<AccountRightEntity> getAccountRights() {
		return accountRights;
	}

	public void setAccountRights(List<AccountRightEntity> accountRights) {
		this.accountRights = accountRights;
	}

	public List<JobEntity> getListJob() {
		return listJob;
	}

	public void setListJob(List<JobEntity> listJob) {
		this.listJob = listJob;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<ETLEntity> getEtlRequest() {
		return etlRequest;
	}

	public void setEtlRequest(List<ETLEntity> etlRequest) {
		this.etlRequest = etlRequest;
	}
}
