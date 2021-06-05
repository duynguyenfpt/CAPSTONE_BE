package com.hoc.api.output;

import java.util.ArrayList;
import java.util.List;

import com.hoc.dto.TableDTO;

public class TableOutput {
	private List<TableDTO> data = new ArrayList<>();
	private PagingOutput meta = new PagingOutput();
	public List<TableDTO> getData() {
		return data;
	}
	public void setData(List<TableDTO> data) {
		this.data = data;
	}
	public PagingOutput getMeta() {
		return meta;
	}
	public void setMeta(PagingOutput meta) {
		this.meta = meta;
	}
	
	
}
