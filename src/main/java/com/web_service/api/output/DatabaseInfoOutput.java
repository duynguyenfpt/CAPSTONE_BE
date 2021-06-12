package com.web_service.api.output;

import java.util.ArrayList;
import java.util.List;

import com.web_service.dto.DatabaseInfoDTO;

public class DatabaseInfoOutput {
	private List<DatabaseInfoDTO> data = new ArrayList<>();
	private PagingOutput meta = new PagingOutput();

	public PagingOutput getMeta() {
		return meta;
	}


	public void setMeta(PagingOutput meta) {
		this.meta = meta;
	}


	public List<DatabaseInfoDTO> getData() {
		return data;
	}


	public void setData(List<DatabaseInfoDTO> data) {
		this.data = data;
	}
}
