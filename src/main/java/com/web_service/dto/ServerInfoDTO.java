package com.web_service.dto;

public class ServerInfoDTO extends AbstractDTO<ServerInfoDTO>{
	private String serverHost;
	
	private String serverDomain;

	public String getServerHost() {
		return serverHost;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

	public String getServerDomain() {
		return serverDomain;
	}

	public void setServerDomain(String serverDomain) {
		this.serverDomain = serverDomain;
	}
}
