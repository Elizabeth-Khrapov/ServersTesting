package com.server.serverTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;


@SpringBootTest
class ServerTestApplicationTests {

	private RestTemplate restTemplate;

	private final String GENERAL_PATH = "https://api-%s.whatslly.com";
	private final String TEST_PATH  = "/test/ping.json";
	private final String[] SERVERS = {"us1","br1","eu1","st1"};
	private final String PATH = "https://api-%s.whatslly.com/test/ping.json";
	private final int  NUMBER_SERVERS = 4;
	private final String SA_ENDPOINT[] = {"https://whatslly-main.my.salesforce.com",
				"https://whatslly-main.my.salesforce.com",
				"https://whatslly-main.my.salesforce.com",
				"https://whatslly-main--dec2020.my.salesforce.com"	};
	
	@PostConstruct
	public void init() {
		restTemplate = new RestTemplate();
		
	}
	@Test
	void getPingFromAllServers() {
		ServerEntity response;
		for(int i=0;i<SERVERS.length;i++) {
			String currentPath = String.format(PATH, SERVERS[i]);
			System.out.println(currentPath);
			response = restTemplate.getForObject(currentPath,ServerEntity.class);
			assertEquals( response.isSuccess(),true);
		}
		
	}
	
	@Test
	void checkAllFields() {
		ServerEntity response;
		for(int i=0;i<SERVERS.length;i++) {
		String currentPath = String.format(GENERAL_PATH, SERVERS[i]); 
		ServerEntity mockData = new ServerEntity(
				   true,
				   "48.0",
				   currentPath,
				   "5.5.51",
				   SA_ENDPOINT[i], true);
		this.restTemplate.postForObject(currentPath+TEST_PATH, mockData, ServerEntity.class);
			response = restTemplate.getForObject(currentPath+TEST_PATH,ServerEntity.class);
			System.out.println(response.getSaEndpoint());
			

			assertEquals( response.getSaEndpoint(),mockData.getSaEndpoint());
			assertEquals( response.getVersion(),mockData.getVersion());
	}
	
	}
}
