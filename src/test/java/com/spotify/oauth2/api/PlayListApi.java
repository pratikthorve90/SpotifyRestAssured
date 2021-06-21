package com.spotify.oauth2.api;

import static io.restassured.RestAssured.given;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;
import com.spotify.oauth2.utils.TestDataLoader;

import io.restassured.response.Response;

// Track all the operation that we can do on playlist API
// Make sure we parameterized them as required.
// Remove validations
// Overload the methods if needed : like post with no body , post with wrong token , and 1 method that is all correct

public class PlayListApi {
	
	static String access_token = "BQAzckulQINyUcM_zSggy7YevKOOHIK7Qj5LoLfUsPPwa-uP1A1DNHiyi3Twq9mafEK_KAypSaLoqQblfVh7EjzD_rpG83qruHbw6jEMZo1dK_oDr7_LsnYpg_ZaO3AnbSP-Bdmo9He9MlAv7OxPtx0K3OTHXZNYHmKomGpAbCJ5e573cXcWBVWEi5mbTsuvYOxW3Klj7VEIu46O4M9Wfk0Hz2oPUfme1morq2Mp52brKv1knMG5";
	
	public static Response post(Playlist body) {
		String useride = TestDataLoader.getInstance().getUserId() ;
		return RestResourceApi.post(body, Route.USER + "/" + TestDataLoader.getInstance().getUserId() + Route.PLAYLIST + "/", TokenManager.getToken());
		
	}
	
	public static Response post(Playlist body, String token) {
		
		return RestResourceApi.post(body, Route.USER +  "/" + TestDataLoader.getInstance().getUserId() + Route.PLAYLIST + "/", token);
		
	}
	
	public static Response post() {
		
		return RestResourceApi.post(Route.USER +  "/" + TestDataLoader.getInstance().getUserId() + Route.PLAYLIST + "/", TokenManager.getToken());
		
	}

	public static Response get(String playListId) {
		
		return RestResourceApi.get(playListId, Route.USER +  "/" + TestDataLoader.getInstance().getUserId() + Route.PLAYLIST + "/", TokenManager.getToken());
	}
	
	public static Response put(Playlist updatePlaylist, String playListId) {
		
		return RestResourceApi.put(updatePlaylist, Route.USER +  "/" + TestDataLoader.getInstance().getUserId() + Route.PLAYLIST + "/", playListId, TokenManager.getToken());

	}

}
