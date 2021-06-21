package com.spotify.oauth2.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

// Properties class to Load the properties file

public class PropertyUtils {
	
	public static Properties propertyLoader(String filePath) {
		
		Properties properties = new Properties();
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(filePath));
			properties.load(reader);
			reader.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to read File :" + filePath);
		}
		
		return properties;
		
	}

}
