package com.web_service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "merge_requests")
public class MergeRequestEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(optional = false)
    @JoinColumn(name = "request_type_id", nullable = false)
	private RequestEntity request;
	
	@Column(name="current_metadata")
	private String currentMetadata;
	
	@Column(name="latest_metadata")
	private String latestMetadata;
	
	@NotEmpty
	@Column(name="merge_table_name")
	private String mergeTableName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RequestEntity getRequest() {
		return request;
	}

	public void setRequest(RequestEntity request) {
		this.request = request;
	}

	public String getCurrentMetadata() {
		return currentMetadata;
	}

	public void setCurrentMetadata(String currentMetadata) {
		this.currentMetadata = currentMetadata;
	}

	public String getLatestMetadata() {
		return latestMetadata;
	}

	public void setLatestMetadata(String latestMetadata) {
		this.latestMetadata = latestMetadata;
	}

	public String getMergeTableName() {
		return mergeTableName;
	}

	public void setMergeTableName(String mergeTableName) {
		this.mergeTableName = mergeTableName;
	}
}
