package com.web_service.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "etl_request")
public class ETLEntity extends BaseEntity{
	@OneToOne(optional = false)
    @JoinColumn(name = "request_type_id", nullable = false)
	private RequestEntity request;
	
	@Column(name="query_type")
	private String queryType;
	
	@Column(name="query")
	private String query;
	
	@Column(name="result_status")
	private Boolean resultStatus = false;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "share_etl_requests", joinColumns = {
			@JoinColumn(name = "etl_request_id") }, inverseJoinColumns = { @JoinColumn(name = "account_id") })
	private List<AccountEntity> accounts;

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Boolean getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(Boolean resultStatus) {
		this.resultStatus = resultStatus;
	}

	public RequestEntity getRequest() {
		return request;
	}

	public void setRequest(RequestEntity request) {
		this.request = request;
	}

	public List<AccountEntity> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountEntity> accounts) {
		this.accounts = accounts;
	}
}
