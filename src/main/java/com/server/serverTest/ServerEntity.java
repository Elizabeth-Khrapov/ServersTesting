package com.server.serverTest;



public class ServerEntity {
	private boolean success;
	private String sfVersion;
	private String host;
	private String version;
	private String saEndpoint;
	private boolean newPackage;
	
	public ServerEntity(){}
	public ServerEntity(boolean success, String sfVersion, String host, String version, String saEndpoint,
			boolean newPackage) {
		this.success = success;
		this.sfVersion = sfVersion;
		this.host = host;
		this.version = version;
		this.saEndpoint = saEndpoint;
		this.newPackage = newPackage;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getSfVersion() {
		return sfVersion;
	}
	public void setSfVersion(String sfVersion) {
		this.sfVersion = sfVersion;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getSaEndpoint() {
		return saEndpoint;
	}
	public void setSaEndpoint(String saEndpoint) {
		this.saEndpoint = saEndpoint;
	}
	public boolean isNewPackage() {
		return newPackage;
	}
	public void setNewPackage(boolean newPackage) {
		this.newPackage = newPackage;
	}

	
}
