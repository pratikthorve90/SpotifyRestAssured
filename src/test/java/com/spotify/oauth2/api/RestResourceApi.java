package com.spotify.oauth2.api;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import com.spotify.oauth2.pojo.Playlist;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

// Track all the operation that we can do on playlist API
// Make sure we parameterized them as required.
// Remove validations
// Overload the methods if needed : like post with no body , post with wrong token , and 1 method that is all correct

public class RestResourceApi {
	
	
	public static Response post(Object body, String path, String token) {
		
		return	given(SpecBuilder.getRequestSpecification())
					.body(body)
				//	.header("Authorization", "Bearer " + token)
					.auth().oauth2(token)
				.when()
					.post(path)
				.then().spec(SpecBuilder.getResponseSpecification())
					.extract().response();		
		
	}
	
	
	public static Response post(String path, String token) {
		
		return	given(SpecBuilder.getRequestSpecification())
					.header("Authorization", "Bearer " + token)
				.when()
					.post(path)
				.then().spec(SpecBuilder.getResponseSpecification())
					.extract().response();		
		
	}

	public static Response get(String playListId, String path, String token) {
		
		return given(SpecBuilder.getRequestSpecification())
				.header("Authorization", "Bearer " + token)
					.when()
						.get(path + playListId)
				.then().spec(SpecBuilder.getResponseSpecification())					
						.extract().response();
	}
	
	public static Response put(Object body, String path, String playListId, String token) {
		
		return given(SpecBuilder.getRequestSpecification())
				.header("Authorization", "Bearer " + token)
				.body(body)
				.log().all()
			.when()
				.put(path + playListId)
			.then()
			.log().all()
			.extract().response();
	}
	
	public static Response postAccount(HashMap<String, String> formParam) {
		
	 	Response response = given(SpecBuilder.getRefreshTokenRequestSpecification())  
				.formParams(formParam)   // similar to x-www-form-encoded
			.when()
				.post()
			.then().spec(SpecBuilder.getResponseSpecification())
				.extract().response();
	 	
	 	return response;
	}

}
