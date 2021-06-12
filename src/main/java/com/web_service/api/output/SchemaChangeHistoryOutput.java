package com.web_service.api.output;

import java.util.ArrayList;
import java.util.List;

import com.web_service.dto.SchemaChangeHistoryDTO;

public class SchemaChangeHistoryOutput {
	private List<SchemaChangeHistoryDTO> data = new ArrayList<>();
	private PagingOutput meta = new PagingOutput();
	
	public List<SchemaChangeHistoryDTO> getData() {
		return data;
	}
	public void setData(List<SchemaChangeHistoryDTO> data) {
		this.data = data;
	}
	public PagingOutput getMeta() {
		return meta;
	}
	public void setMeta(PagingOutput meta) {
		this.meta = meta;
	}
}
