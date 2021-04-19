package com.server.serverTest;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


// data model for server response  
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ServerEntity { 
	private boolean success;
	private String sfVersion;
	private String host;
	private String version;
	private String saEndpoint;
	private boolean newPackage;
}
