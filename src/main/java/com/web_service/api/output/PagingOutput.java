package com.web_service.api.output;

public class PagingOutput {
	private int page;
	private int total_page;
	private int total_item;
	
	
	public PagingOutput() {
	
	}
	public PagingOutput(int page, int total_page, int total_item) {
		super();
		this.page = page;
		this.total_page = total_page;
		this.total_item = total_item;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotal_page() {
		return total_page;
	}
	public void setTotal_page(int total_page) {
		this.total_page = total_page;
	}
	public int getTotal_item() {
		return total_item;
	}
	public void setTotal_item(int total_item) {
		this.total_item = total_item;
	}
	
	
}
