package com.server.serverTest;

import java.util.ArrayList;
import java.util.List;

public class TestConfigurator {


	private final String GENERAL_PATH = "https://api-%s.whatslly.com";
	private final String TEST_PATH  = "/test/ping.json";
	private final String[] SERVERS = {"us1","br1","eu1","st1"};
	private final String PATH = "https://api-%s.whatslly.com/test/ping.json";
	private final int  NUMBER_SERVERS = 4;
	private final String SA_ENDPOINT[] = {"https://whatslly-main.my.salesforce.com",
				"https://whatslly-main--dec2020.my.salesforce.com"	};
	
	
	public List<ServerData> getTestConfigurator(){
		List<ServerData> serversdata = new ArrayList<>();
		for(int i=0;i<NUMBER_SERVERS;i++) {
		String currentPath = String.format(GENERAL_PATH, SERVERS[i]);
		if(i<3) {
			ServerEntity serverEntity = new ServerEntity(
					   true,
					   "48.0",
					   currentPath,
					   "5.5.51",
					   SA_ENDPOINT[0], true);
			ServerData serverdata = new ServerData((currentPath+TEST_PATH),serverEntity);
			serversdata.add(serverdata);
		}else {
			ServerEntity serverEntity = new ServerEntity(
					   true,
					   "48.0",
					   currentPath,
					   "5.4.64",
					   SA_ENDPOINT[1], true);
			ServerData serverdata = new ServerData((currentPath+TEST_PATH),serverEntity);
			serversdata.add(serverdata);
		}
		}
		return serversdata;
	}
	
}
