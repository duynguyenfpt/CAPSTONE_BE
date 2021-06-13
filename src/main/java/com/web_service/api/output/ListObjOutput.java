package com.web_service.api.output;

import java.util.List;

public class ListObjOutput<T> extends BaseOutput {
	private List<T> data;
	
	private PagingOutput metaData;
	
	

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
