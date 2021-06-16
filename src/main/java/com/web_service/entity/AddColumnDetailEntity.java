package com.web_service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "add_column_detail")
public class AddColumnDetailEntity extends BaseEntity{
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "add_column_table_request_id", nullable = false)
	private AddColumnTableRequestEntity addColumnTableRequest;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "current_table_schema_id", nullable = false)
	private CurrentTableSchemaEntity currentTableSchema;

	public AddColumnTableRequestEntity getAddColumnTableRequest() {
		return addColumnTableRequest;
	}

	public void setAddColumnTableRequest(AddColumnTableRequestEntity addColumnTableRequest) {
		this.addColumnTableRequest = addColumnTableRequest;
	}

	public CurrentTableSchemaEntity getCurrentTableSchema() {
		return currentTableSchema;
	}

	public void setCurrentTableSchema(CurrentTableSchemaEntity currentTableSchema) {
		this.currentTableSchema = currentTableSchema;
	}
}
