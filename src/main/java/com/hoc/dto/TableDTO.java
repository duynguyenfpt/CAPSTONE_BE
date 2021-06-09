package com.hoc.dto;

import java.util.List;

import com.hoc.entity.CurrentTableSchemaEntity;

public class TableDTO extends AbstractDTO<TableDTO>{
	private String table_name;
	private Long database_infor_id;
	private List<CurrentTableSchemaEntity> current_table_schemas;

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

	public List<CurrentTableSchemaEntity> getCurrent_table_schemas() {
		return current_table_schemas;
	}

	public void setCurrent_table_schemas(List<CurrentTableSchemaEntity> current_table_schemas) {
		this.current_table_schemas = current_table_schemas;
	}




}
