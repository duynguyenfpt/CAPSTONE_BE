package com.web_service.api.output;

import java.util.ArrayList;
import java.util.List;

import com.web_service.dto.CurrentTableSchemaDTO;

public class CurrentTableSchemaOutput {
	private List<CurrentTableSchemaDTO> data = new ArrayList<>();
	private PagingOutput meta = new PagingOutput();

	public PagingOutput getMeta() {
		return meta;
	}


	public void setMeta(PagingOutput meta) {
		this.meta = meta;
	}


	public List<CurrentTableSchemaDTO> getData() {
		return data;
	}


	public void setData(List<CurrentTableSchemaDTO> data) {
		this.data = data;
	}
}
