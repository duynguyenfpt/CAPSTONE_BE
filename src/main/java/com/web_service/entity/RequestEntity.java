package com.web_service.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "request")
public class RequestEntity extends BaseEntity {
	@Column
	private String status;
	
	@Column
	private String requestType;
	
	@JsonBackReference
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private AccountEntity creator;
	
	@JsonBackReference
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "approved_by_id", referencedColumnName = "id")
    private AccountEntity approvedBy;
	

	public AccountEntity getCreator() {
		return creator;
	}

	public void setCreator(AccountEntity creator) {
		this.creator = creator;
	}

	public AccountEntity getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(AccountEntity approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	
	
}
