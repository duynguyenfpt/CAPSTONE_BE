package com.web_service.api.output;

public class PagingOutput {
	private int totalPage;
	private int totalItem;
	
	
	public PagingOutput() {
	
	}
	public PagingOutput(int totalPage, int totalItem) {
		this.totalPage = totalPage;
		this.totalItem = totalItem;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalItem() {
		return totalItem;
	}
	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}
}
