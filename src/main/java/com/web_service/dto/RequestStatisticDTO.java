package com.web_service.dto;

public class RequestStatisticDTO {
	private String status;
	private int value;
	
	public RequestStatisticDTO(String status, int value) {
		super();
		this.status = status;
		this.value = value;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
