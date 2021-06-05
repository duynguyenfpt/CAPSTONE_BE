package com.hoc.dto;

public class TableDTO extends AbstractDTO<TableDTO>{
	private String table_name;
	private Long database_infor_id;


	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public Long getDatabase_infor_id() {
		return database_infor_id;
	}

	public void setDatabase_infor_id(Long database_infor_id) {
		this.database_infor_id = database_infor_id;
	}


}
