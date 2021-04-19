package com.server.serverTest;

import java.util.ArrayList;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "server.test")
@ActiveProfiles("test")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TestConfigurator {
	
	//data containers for appliaction.properties values
	private String path;
	private String testPath;
	private String[] servers;
	private String[] endpoints;
	private String[] versions;
	private String salesforceVersion;
	private int requestloadtest;

	//returns list of server`s configuration data
	public List<ServerData> getTestConfiguration() {
		List<ServerData> serversdata = new ArrayList<>();
		for (int i = 0; i < servers.length; i++) {
			String currentPath = String.format(path, servers[i]);//set server name to path
			if (i < 3) {
				ServerEntity serverEntity = new ServerEntity(true, salesforceVersion, currentPath, versions[0], endpoints[0], true);
				ServerData serverdata = new ServerData((currentPath + testPath), serverEntity);
				serversdata.add(serverdata);
			} else {
				ServerEntity serverEntity = new ServerEntity(true, salesforceVersion, currentPath, versions[1], endpoints[1], true);
				ServerData serverdata = new ServerData((currentPath + testPath), serverEntity);
				serversdata.add(serverdata);
			}
		}
		return serversdata;
	}

}
