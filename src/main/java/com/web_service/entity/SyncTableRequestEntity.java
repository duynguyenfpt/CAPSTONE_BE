package com.web_service.entity;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "sync_table_requests")
public class SyncTableRequestEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "is_all")
	private boolean isAll;
	
	@Column(name = "from_date")
	private Date fromDate;
	
	@Column(name = "to_date")
	private Date toDate;
	
	@Column(name = "partition_by")
	private String partitionBy;
	
	@Column(name = "identity_id")
	private String identityId;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "is_process")
	private Boolean isProcess;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "table_id", nullable = false)
	private TableEntity tableInfo;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "request_id", nullable = false)
	private RequestEntity request;

	public boolean isAll() {
		return isAll;
	}

	public void setIsAll(boolean isAll) {
		this.isAll = isAll;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public TableEntity getTableInfo() {
		return tableInfo;
	}

	public void setTableInfo(TableEntity tableInfo) {
		this.tableInfo = tableInfo;
	}

	public RequestEntity getRequest() {
		return request;
	}

	public void setRequest(RequestEntity request) {
		this.request = request;
	}

	
	public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}

	public String getPartitionBy() {
		return partitionBy;
	}

	public void setPartitionBy(String partitionBy) {
		this.partitionBy = partitionBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setAll(boolean isAll) {
		this.isAll = isAll;
	}

	public Boolean getIsProcess() {
		return isProcess;
	}

	public void setIsProcess(Boolean isProcess) {
		this.isProcess = isProcess;
	}
}
