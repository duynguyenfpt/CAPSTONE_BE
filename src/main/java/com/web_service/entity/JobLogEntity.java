package com.web_service.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "job_logs")
public class JobLogEntity extends BaseEntity{
	@Column
	private String status;
	
	@Column
	private String message;
	
	@Column
	private Date timeCreated;
	
	public Date getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}

	@JsonBackReference
	@ManyToOne
    @JoinColumn(name = "job")
    private JobEntity job;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public JobEntity getJob() {
		return job;
	}

	public void setJob(JobEntity job) {
		this.job = job;
	}
}
