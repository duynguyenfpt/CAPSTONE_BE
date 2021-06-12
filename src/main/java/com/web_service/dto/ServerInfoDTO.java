package com.web_service.dto;

public class ServerInfoDTO extends AbstractDTO<ServerInfoDTO>{
	private String server_host;
	
	private String server_domain;

	public String getServer_host() {
		return server_host;
	}

	public void setServer_host(String server_host) {
		this.server_host = server_host;
	}

	public String getServer_domain() {
		return server_domain;
	}

	public void setServer_domain(String server_domain) {
		this.server_domain = server_domain;
	}
	
	
}
