package com.hoc.api.output;

import java.util.ArrayList;
import java.util.List;

import com.hoc.dto.NewDTO;

public class NewOutput {
	private int page;
	private int totalPage;
	private int totalItem;
	private List<NewDTO> listResult = new ArrayList<>();
	public int getPage() {
		return page;
	}
	public List<NewDTO> getListResult() {
		return listResult;
	}
	public void setListResult(List<NewDTO> listResult) {
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
