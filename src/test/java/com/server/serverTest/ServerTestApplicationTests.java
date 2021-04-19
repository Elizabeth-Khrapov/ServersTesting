package com.server.serverTest;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@SpringBootTest
@EnableConfigurationProperties
@ActiveProfiles("test")
@RequiredArgsConstructor(onConstructor = @__(@Autowired)) //creates default constructor checks that all required values are not null 
class ServerTestApplicationTests {
	@NonNull
	private TestConfigurator testConfigurator;
	private RestTemplate restTemplate; //
	private List<ServerData> serversData;

	//gets test environment from configuration
	@PostConstruct
	public void init() {
		restTemplate = new RestTemplate();
		serversData = testConfigurator.getTestConfiguration();
	}

	//communication test 
	@Test
	void getPingFromAllServers() {
		ServerEntity response;
		for (ServerData sd : serversData) {
			response = restTemplate.getForObject(sd.getPath(), ServerEntity.class);
			assertThat(response.isSuccess()).isEqualTo(true);
		}
	}

	//verifies that the response matches the desired values
	@Test
	void checkAllFields() {
		ServerEntity response;
		for (ServerData sd : serversData) {
			response = restTemplate.getForObject(sd.getPath(), ServerEntity.class);
			assertThat(response).isEqualTo(sd.getEntity());
		}
	}

	//makes sure that the servers support security level verification
	@Test
	void postNotValid() {
		HttpStatus response = null;
		for (ServerData sd : serversData) {
			try {
				this.restTemplate.postForObject(sd.getPath(), sd.getEntity(),
						ServerEntity.class);
			} catch (HttpStatusCodeException ex) {
				response = ex.getStatusCode();
			}
			assertThat(response).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED);
		}
	}

	//checks the performance of multiple http requests
	@Test
	void checkPerformence() {
		for (int j = 0; j < testConfigurator.getRequestloadtest(); j++) {
			getPingFromAllServers();
		}
	}
}
