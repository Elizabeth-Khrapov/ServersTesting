package com.server.serverTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;


@SpringBootTest
class ServerTestApplicationTests {

	private RestTemplate restTemplate;
	private List<ServerData> serversData;
	private final int REQUEST_NUMBER = 50;
	
	@PostConstruct
	public void init() {
		restTemplate = new RestTemplate();
		TestConfigurator testConfigurator = new TestConfigurator();
		serversData = testConfigurator.getTestConfigurator();
	}
	@Test
	void getPingFromAllServers() {
		ServerEntity response;
		for(int i=0;i<serversData.size();i++) {
			response = restTemplate.getForObject(serversData.get(i).getPath(),ServerEntity.class);
			assertEquals( response.isSuccess(),true);
		}
		
	}
	
	@Test
	void checkAllFields() {
		ServerEntity response;
		for(int i=0;i<serversData.size();i++) {
			response = restTemplate.getForObject(serversData.get(i).getPath(),ServerEntity.class);
			assertEquals( response.getSaEndpoint(),serversData.get(i).getEntity().getSaEndpoint());
			assertEquals( response.getVersion(),serversData.get(i).getEntity().getVersion());
			assertEquals( response.getSfVersion(),serversData.get(i).getEntity().getSfVersion());
			assertEquals( response.getHost(),serversData.get(i).getEntity().getHost());
			assertEquals( response.isNewPackage(),serversData.get(i).getEntity().isNewPackage());
	}
	
	}
	@Test
	void postNotValid() {
		HttpStatus response = null;
		for(int i=0;i<serversData.size();i++) {
		try {
			this.restTemplate.postForObject(serversData.get(i).getPath(), serversData.get(i).getEntity(), ServerEntity.class);
		} catch (HttpStatusCodeException ex) {
			response = ex.getStatusCode();
		}
		assertEquals(response, HttpStatus.METHOD_NOT_ALLOWED);	
	}
	}
	@Test
	void checkPerformence() {
		ServerEntity response;
		for(int j=0;j<REQUEST_NUMBER;j++) {
		for(int i=0;i<serversData.size();i++) {
			response = restTemplate.getForObject(serversData.get(i).getPath(),ServerEntity.class);
			assertEquals( response.isSuccess(),true);
		}
		}
		}
}
