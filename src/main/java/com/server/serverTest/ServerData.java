package com.server.serverTest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//data model for properties required for server test

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServerData {
	private String path;
	private ServerEntity entity;
}

