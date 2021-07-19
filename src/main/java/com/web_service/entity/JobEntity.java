
package com.web_service.entity;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "jobs")
public class JobEntity extends BaseEntity{
	@Column
	private boolean isActive;
	
	@Column
	private Date jobSchedule;
	
	@Column(name = "max_retries")
	private int maxRetries;
	
	@Column
	private String status;
	
	@Column(name = "number_retries")
	private int numberRetries;
	
	@Column
	private String description;
	
	@JsonBackReference
	@ManyToOne
    @JoinColumn(name = "executed_by", nullable = false)
    private AccountEntity executedBy;
	
	@JsonBackReference
	@ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private RequestEntity request;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "job")
    private List<JobLogEntity> listJobLog;

	public RequestEntity getRequest() {
		return request;
	}

	public void setRequest(RequestEntity request) {
		this.request = request;
	}

	public List<JobLogEntity> getListJobLog() {
		return listJobLog;
	}

	public void setListJobLog(List<JobLogEntity> listJobLog) {
		this.listJobLog = listJobLog;
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

	public int getMaxRetries() {
		return maxRetries;
	}

	public void setMaxRetries(int maxRetries) {
		this.maxRetries = maxRetries;
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

	public int getNumberRetries() {
		return numberRetries;
	}

	public void setNumberRetries(int numberRetries) {
		this.numberRetries = numberRetries;
	}
}
