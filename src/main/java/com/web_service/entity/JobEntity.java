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
	
	@Column
	private int maxRetry;
	
	@JsonBackReference
	@ManyToOne
    @JoinColumn(name = "executed_by", nullable = false)
    private AccountEntity executedBy;
	
	@JsonBackReference
	@ManyToOne
    @JoinColumn(name = "request", nullable = false)
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

	public int getMaxRetry() {
		return maxRetry;
	}

	public void setMaxRetry(int maxRetry) {
		this.maxRetry = maxRetry;
	}

	public AccountEntity getExecutedBy() {
		return executedBy;
	}

	public void setExecutedBy(AccountEntity executedBy) {
		this.executedBy = executedBy;
	}
}
