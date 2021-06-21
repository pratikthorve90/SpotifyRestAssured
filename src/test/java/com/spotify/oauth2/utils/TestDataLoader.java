package com.spotify.oauth2.utils;

import java.util.Properties;

public class TestDataLoader {
	
	private static Properties properties;
	private static TestDataLoader testDataLoader;
	
	private TestDataLoader() {
		properties = PropertyUtils.propertyLoader("src/test/resources/testData.properties");
	}
	
	public static TestDataLoader getInstance() {
		if(testDataLoader == null) {
			testDataLoader = new TestDataLoader();
		}
		return testDataLoader;
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

}
