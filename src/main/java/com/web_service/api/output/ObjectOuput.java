package com.web_service.api.output;

public class ObjectOuput<T> extends BaseOutput {

	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	
}
