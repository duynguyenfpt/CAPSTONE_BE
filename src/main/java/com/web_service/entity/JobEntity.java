
package com.web_service.entity;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "jobs")
@SQLDelete(sql = "UPDATE jobs SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class JobEntity extends BaseEntity{
	@Column
	private boolean isActive;
	
	@Column
	private Date jobSchedule;
	
	@Column(name = "max_retries")
	private Integer maxRetries;
	
	@Column
	private String status;
	
	@Column(name = "number_retries")
	private Integer numberRetries;
	
	@Column
	private String description;
	
	@Column
	private boolean deleted = Boolean.FALSE;
	
	@JsonBackReference
	@ManyToOne
    @JoinColumn(name = "executed_by", nullable = false)
    private AccountEntity executedBy;
	
	@JsonBackReference
	@ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private RequestEntity request;

	public RequestEntity getRequest() {
		return request;
	}

	public void setRequest(RequestEntity request) {
		this.request = request;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getJobSchedule() {
		return jobSchedule;
	}

	public void setJobSchedule(Date jobSchedule) {
		this.jobSchedule = jobSchedule;
	}

	public AccountEntity getExecutedBy() {
		return executedBy;
	}

	public void setExecutedBy(AccountEntity executedBy) {
		this.executedBy = executedBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMaxRetries() {
		return maxRetries;
	}

	public void setMaxRetries(Integer maxRetries) {
		this.maxRetries = maxRetries;
	}

	public Integer getNumberRetries() {
		return numberRetries;
	}

	public void setNumberRetries(Integer numberRetries) {
		this.numberRetries = numberRetries;
	}
}
