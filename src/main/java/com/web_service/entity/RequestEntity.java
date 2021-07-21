package com.web_service.entity;

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
@Table(name = "request")
@SQLDelete(sql = "UPDATE request SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class RequestEntity extends BaseEntity {
	@Column
	private String status = "0";
	
	@Column
	private String requestType;
	
    @JoinColumn(name = "approved_by")
    private String approvedBy;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "request")
    private List<JobEntity> listJob;
	
	@Column
	private boolean deleted = Boolean.FALSE;
//	
//	@JsonManagedReference
//	@OneToMany(mappedBy = "requestAddColumn", fetch=FetchType.EAGER)
//	private List<AddColumnTableRequestEntity> listAddColumnTableRequests = new ArrayList<>();


	public List<JobEntity> getListJob() {
		return listJob;
	}

	public void setListJob(List<JobEntity> listJob) {
		this.listJob = listJob;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
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
