package com.web_service.dto;

public class RightDTO extends AbstractDTO<RightDTO>{
	private String path;
	private String method;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
}
