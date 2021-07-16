package com.web_service.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "rights")
public class RightEntity extends BaseEntity{
	@Column(name = "right_name")
	private String rightName;
	
	@Column(name = "code")
	private String code;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "right")
	private List<AccountRightEntity> accountRights = new ArrayList<>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<AccountRightEntity> getAccountRights() {
		return accountRights;
	}

	public void setAccountRights(List<AccountRightEntity> accountRights) {
		this.accountRights = accountRights;
	}

	public String getRightName() {
		return rightName;
	}

	public void setRightName(String rightName) {
		this.rightName = rightName;
	}
}
