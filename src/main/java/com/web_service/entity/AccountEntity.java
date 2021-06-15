package com.web_service.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

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
	private String userName;
	
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	
	@NotBlank
	@Size(max = 120)
	private String password;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "role")
	private String role;
	
	@JsonManagedReference
    @OneToOne(mappedBy = "creator")
    private RequestEntity requestCreator;
    
	@JsonManagedReference
    @OneToOne(mappedBy = "approvedBy")
    private RequestEntity requestApprovedBy;
    

	public RequestEntity getRequestCreator() {
		return requestCreator;
	}

	public void setRequestCreator(RequestEntity requestCreator) {
		this.requestCreator = requestCreator;
	}

	public RequestEntity getRequestApprovedBy() {
		return requestApprovedBy;
	}

	public void setRequestApprovedBy(RequestEntity requestApprovedBy) {
		this.requestApprovedBy = requestApprovedBy;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
}
