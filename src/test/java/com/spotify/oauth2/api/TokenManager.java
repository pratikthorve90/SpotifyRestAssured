package com.spotify.oauth2.api;

import java.time.Instant;
import java.util.HashMap;

import com.spotify.oauth2.utils.ConfigLoader;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;


public class TokenManager {
	
	private static String access_Token;
	private static Instant token_Expiry_Time;	
	
	public synchronized static String getToken() {
		
		System.out.println("Inside Get Token");
		if(access_Token == null || Instant.now().isAfter(token_Expiry_Time)) {
			System.out.println("Generating new Token");
			Response response = renewToken();
			access_Token = response.path("access_token");
			int expirySeconds = response.path("expires_in");
			token_Expiry_Time = Instant.now().plusSeconds((expirySeconds - 300));			
		}		
		return access_Token;		
	}	
	
	public static Response renewToken() {
		
		HashMap<String, String> formParam = new HashMap<String, String>();
		formParam.put("grant_type", ConfigLoader.getInstance().getGrantType());
		formParam.put("client_id", ConfigLoader.getInstance().getClientId());
		formParam.put("client_secret", ConfigLoader.getInstance().getClientSecret());
		formParam.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());
	
		Response response = RestResourceApi.postAccount(formParam);			
			
		if(response.statusCode() != 200) {
			throw new RuntimeException("Unable to fetch token , aborting the program");
		}
	
		return response;
			
	}

}
