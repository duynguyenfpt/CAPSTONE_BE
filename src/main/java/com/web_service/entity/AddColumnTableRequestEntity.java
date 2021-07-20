package com.web_service.entity;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "add_column_table_requests")
public class AddColumnTableRequestEntity extends BaseEntity{
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "table_id", nullable = false)
	private TableEntity tableInfo;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "request_id", nullable = false)
	private RequestEntity requestAddColumn;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "addColumnTableRequest", fetch=FetchType.EAGER)
	private List<AddColumnDetailEntity> addColumnDetails = new ArrayList<>();
	
	
	private Time timeRequest;

	public TableEntity getTableInfo() {
		return tableInfo;
	}

	public void setTableInfo(TableEntity tableInfo) {
		this.tableInfo = tableInfo;
	}


	public RequestEntity getRequestAddColumn() {
		return requestAddColumn;
	}

	public void setRequestAddColumn(RequestEntity requestAddColumn) {
		this.requestAddColumn = requestAddColumn;
	}

	public Time getTimeRequest() {
		return timeRequest;
	}

	public void setTimeRequest(Time timeRequest) {
		this.timeRequest = timeRequest;
	}

	public List<AddColumnDetailEntity> getAddColumnDetails() {
		return addColumnDetails;
	}

	public void setAddColumnDetails(List<AddColumnDetailEntity> addColumnDetails) {
		this.addColumnDetails = addColumnDetails;
	}
}
