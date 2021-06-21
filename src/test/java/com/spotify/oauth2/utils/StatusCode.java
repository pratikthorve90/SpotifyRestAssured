package com.spotify.oauth2.utils;

public enum StatusCode {
	
	CODE_200(200, ""),
	CODE_201(201, ""),
	CODE_400(400, "Error parsing JSON."),
	CODE_401(401, "Invalid access token");
	
	private final int statusCode;
	private final String statusMessage;
	
	StatusCode(int code, String msg) {
		this.statusCode = code;
		this.statusMessage = msg;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}
	
	
	
	


}
