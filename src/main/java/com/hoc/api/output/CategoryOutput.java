package com.hoc.api.output;

import java.util.ArrayList;
import java.util.List;

import com.hoc.dto.CategoryDTO;

public class CategoryOutput {
	private List<CategoryDTO> data = new ArrayList<>();
	private PagingOutput meta = new PagingOutput();

	public PagingOutput getMeta() {
		return meta;
	}


	public void setMeta(PagingOutput meta) {
		this.meta = meta;
	}


	public List<CategoryDTO> getData() {
		return data;
	}


	public void setData(List<CategoryDTO> data) {
		this.data = data;
	}

}
