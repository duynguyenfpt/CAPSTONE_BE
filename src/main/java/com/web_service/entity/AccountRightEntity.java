package com.web_service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "account_right",
uniqueConstraints = { @UniqueConstraint(columnNames = { "account_id", "right_id" }) })
public class AccountRightEntity extends BaseEntity{
	
	@Column(name = "status")
	private String status;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "account_id", nullable = false)
	private AccountEntity account;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "right_id", nullable = false)
	private RightEntity right;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AccountEntity getAccount() {
		return account;
	}

	public void setAccount(AccountEntity account) {
		this.account = account;
	}

	public RightEntity getRight() {
		return right;
	}

	public void setRight(RightEntity right) {
		this.right = right;
	}
}
