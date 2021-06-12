package com.web_service.api.output;

import java.util.ArrayList;
import java.util.List;

public class BaseOutput<T> {
	private String code;
	
	private List<T> data = new ArrayList<>();
	
	private PagingOutput metaData;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public PagingOutput getMetaData() {
		return metaData;
	}

	public void setMetaData(PagingOutput metaData) {
		this.metaData = metaData;
	}
	
}
