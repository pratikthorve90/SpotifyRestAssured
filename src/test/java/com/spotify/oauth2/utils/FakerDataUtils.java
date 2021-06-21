package com.spotify.oauth2.utils;

import com.github.javafaker.Faker;

public class FakerDataUtils {
	
	Faker faker = new Faker();

	
	public static String generatePlaylistName() {
		Faker faker = new Faker();
		return "Playlist Name : " +  faker.regexify("[A-Za-z0-9 ,_-]{20}");   // we are using regex aligned with the dev team so that we dont use any characters that are not allowed
	}

	public static String generatePlaylistDescription() {
		Faker faker = new Faker();
		return "Playlist Description" + faker.regexify("[A-Za-z0-9 ,_-]{40}");
	}
}
