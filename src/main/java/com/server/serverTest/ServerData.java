package com.server.serverTest;

public class ServerData {

	private String path;
	private ServerEntity entity;
	
	public ServerData(String path, ServerEntity entity) {
		this.path = path;
		this.entity = entity;
	}

	public String getPath() {
		return path;
	}

	public ServerEntity getEntity() {
		return entity;
	}
		
}

