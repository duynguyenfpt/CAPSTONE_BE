package com.web_service.api.output;

import java.util.ArrayList;
import java.util.List;

import com.web_service.dto.ServerInfoDTO;

public class ServerOutput {
	private List<ServerInfoDTO> data = new ArrayList<>();
	private PagingOutput meta = new PagingOutput();
	
	public List<ServerInfoDTO> getData() {
		return data;
	}
	public void setData(List<ServerInfoDTO> data) {
		this.data = data;
	}
	public PagingOutput getMeta() {
		return meta;
	}
	public void setMeta(PagingOutput meta) {
		this.meta = meta;
	}
}
