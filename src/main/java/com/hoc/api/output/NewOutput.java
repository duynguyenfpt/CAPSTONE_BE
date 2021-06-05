package com.hoc.api.output;

import java.util.ArrayList;
import java.util.List;

import com.hoc.dto.NewDTO;

public class NewOutput {
	private int page;
	private int totalPage;
	private int totalItem;
	
	public int getPage() {
		return page;
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
