package com.web_service.dto;

import java.math.BigInteger;

public class JobDetailDTO {
	private String serverDomain;
	
	private String serverHost;
	
	private String port;
	
	private String databaseType;
	
	private String identityId;
	
	private String partitionBy;
	
	private BigInteger strId;
	
	private BigInteger jobId;
	
	private Integer maxRetries;
	
	private Integer numberRetries;
	
	private Integer lastestOffset;
	
	private String table;
	
	private String database;

	private String createdDate;
	
	private String executedBy;
	
	private String createdBy;
	
	private Boolean active;
	
	private Long requestId;
	
	private String query;
	
	public String getServerDomain() {
		return serverDomain;
	}

	public void setServerDomain(String serverDomain) {
		this.serverDomain = serverDomain;
	}

	public String getServerHost() {
		return serverHost;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
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

	public BigInteger getStrId() {
		return strId;
	}

	public void setStrId(BigInteger strId) {
		this.strId = strId;
	}

	public BigInteger getJobId() {
		return jobId;
	}

	public void setJobId(BigInteger jobId) {
		this.jobId = jobId;
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

	public Integer getLastestOffset() {
		return lastestOffset;
	}

	public void setLastestOffset(Integer lastestOffset) {
		this.lastestOffset = lastestOffset;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getExecutedBy() {
		return executedBy;
	}

	public void setExecutedBy(String executedBy) {
		this.executedBy = executedBy;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
}
