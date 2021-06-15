//package com.web_service.entity;
//
//import java.sql.Time;
//
//import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//
//@Entity
//@Table(name = "add_column_table_requests")
//public class AddColumnTableRequestEntity extends BaseEntity{
//	@JsonBackReference
//	@ManyToOne
//	@JoinColumn(name = "table_id", nullable = false)
//	private TableEntity tableInfo;
//	
//	@JsonBackReference
//	@ManyToOne
//	@JoinColumn(name = "request_id", nullable = false)
//	private RequestEntity request;
//	
////	 @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
////	    @JoinTable(name = "add_column_detail",
////	            joinColumns = {
////	                    @JoinColumn(name = "add_column_table_request_id", referencedColumnName = "id",
////	                            nullable = false, updatable = false)},
////	            inverseJoinColumns = {
////	                    @JoinColumn(name = "current_table_shchemas", referencedColumnName = "id",
////	                            nullable = false, updatable = false)})
////	    private List<CurrentTableSchemaEntity> currentTableSchemas = new ArrayList<>();
//	
//	private Time timeRequest;
//
//	public TableEntity getTableInfo() {
//		return tableInfo;
//	}
//
//	public void setTableInfo(TableEntity tableInfo) {
//		this.tableInfo = tableInfo;
//	}
//
//	public RequestEntity getRequest() {
//		return request;
//	}
//
//	public void setRequest(RequestEntity request) {
//		this.request = request;
//	}
//
//	public Time getTimeRequest() {
//		return timeRequest;
//	}
//
//	public void setTimeRequest(Time timeRequest) {
//		this.timeRequest = timeRequest;
//	}
//}
