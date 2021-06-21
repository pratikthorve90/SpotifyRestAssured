package com.spotify.oauth2.utils;

import java.util.Properties;

// Singleton class to load properties

public class ConfigLoader {
	
	private static Properties properties;
	private static ConfigLoader configLoader;
	
	private ConfigLoader() {
		properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
	}
	
	public static ConfigLoader getInstance() {
		if(configLoader == null) {
			configLoader = new ConfigLoader();
		}
		
		return configLoader;
	}
	
	public String getClientId() {
		String clientId = properties.getProperty("client_id");
		if(clientId != null) return clientId;
		else throw new RuntimeException("Unable to read property clientId" );
		
	}
	
	public String getClientSecret() {
		String client_secret = properties.getProperty("client_secret");
		if(client_secret != null) return client_secret;
		else throw new RuntimeException("Unable to read property client_secret" );
		
	}
	
	public String getRefreshToken() {
		String refresh_token = properties.getProperty("refresh_token");
		if(refresh_token != null) return refresh_token;
		else throw new RuntimeException("Unable to read property refresh_token" );
		
	}	
	
	public String getUserId() {
		String user_id = properties.getProperty("user_id");
		if(user_id != null) return user_id;
		else throw new RuntimeException("Unable to read property user_id" );
		
	}	
	
	public String getPlayListId() {
		String playlist_id = properties.getProperty("playlist_id");
		if(playlist_id != null) return playlist_id;
		else throw new RuntimeException("Unable to read property playlist_id" );
		
	}	
	
	public String getGrantType() {
		String grant_type = properties.getProperty("grant_type");
		if(grant_type != null) return grant_type;
		else throw new RuntimeException("Unable to read property grant_type" );
		
	}

}
