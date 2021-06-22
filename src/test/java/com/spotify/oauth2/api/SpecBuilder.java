package com.spotify.oauth2.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {
	
	
	
	public static RequestSpecification getRequestSpecification() {
		
		System.out.println("Inside Spec builder" + System.getProperty("BASE_URI") + System.getProperty("BASE_URI_ACCOUNT_API") );
		
		return new RequestSpecBuilder()
			//	.setBaseUri("https://api.spotify.com")
				.setBaseUri(System.getProperty("BASE_URI"))
				.setBasePath(Route.BASE_PATH)
				.log(LogDetail.ALL)
				.setContentType(ContentType.JSON)
				.build();		
		
	}		
	public static RequestSpecification getRefreshTokenRequestSpecification() {
		
	//	return new RequestSpecBuilder().setBaseUri("https://accounts.spotify.com" + Route.API + Route.TOKEN)
		return new RequestSpecBuilder().setBaseUri(System.getProperty("BASE_URI_ACCOUNT_API") + Route.API + Route.TOKEN)
									.setContentType(ContentType.URLENC)
									.build();
		
	}
	
	public static ResponseSpecification getResponseSpecification() {		
		return new ResponseSpecBuilder()
				.log(LogDetail.ALL)
				.build();	
	}
	
	

}
