package com.hoc.api.output;

import java.util.ArrayList;
import java.util.List;

import com.hoc.dto.CategoryDTO;

public class CategoryOutput {
	private int page;
	private int totalPage;
	private int totalItem;
	private List<CategoryDTO> listResult = new ArrayList<>();
	public int getPage() {
		return page;
	}
	public List<CategoryDTO> getListResult() {
		return listResult;
	}
	public void setListResult(List<CategoryDTO> listResult) {
		this.listResult = listResult;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public long getTotalItem() {
		return totalItem;
	}
	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}
}
