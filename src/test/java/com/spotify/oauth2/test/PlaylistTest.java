package com.spotify.oauth2.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.spotify.oauth2.api.PlayListApi;
import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.pojo.ErrorResponse;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;
import com.spotify.oauth2.utils.FakerDataUtils;
import com.spotify.oauth2.utils.StatusCode;
import com.spotify.oauth2.utils.TestDataLoader;

import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Spotify Oauth 2")
@Feature("Playlist Feature")
public class PlaylistTest extends BaseTest{
	
	public Playlist playListBuilder(String Name, String description, boolean _public) {		
			
		return new Playlist().setDescription(description).setName(Name).setPublic(_public);		
	}
	
	public void assertPlaylist(Playlist requestPlaylist, Playlist responsePlaylist) {
		
			assertEquals(responsePlaylist.getName(), requestPlaylist.getName());   // TestNG
			assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));   // Hamcrest
			assertEquals(responsePlaylist.getPublic(), requestPlaylist.getPublic());   // TestNG
	}
	
	public void assertStatusCode(int actualStatusCode , int expectedStatusCode) {
		assertThat(actualStatusCode, equalTo(expectedStatusCode));
	}
	
	public void assertErrorDetails(ErrorResponse errorResponse, StatusCode code) {
		assertThat(errorResponse.getError().getMessage(), equalTo(code.getStatusMessage()));
		assertThat(errorResponse.getError().getStatus(), equalTo(code.getStatusCode()));
	}


	@Test(description = "We should be able to fetch user details")
	@Link("Just_Using_The_Link_Annotation")
	@Issue("#Defect123")
	@TmsLink("Link to QC")
	@Story("User should be able to get user details")
	public void getUserDetails() {
		
			Response response = given(SpecBuilder.getRequestSpecification())
									.when()
										.get("v1/me")
									.then().spec(SpecBuilder.getResponseSpecification())
									.extract().response();
			
			JsonPath js = new JsonPath(response.asString());
			System.out.println("User id is : " + js.getString("id"));						
		
		
	}
	
	@Test(description = "We should be able to create playlist")
	@Description("This is used to check if we are able to create playlist or not")	
	public void shouldBeAbleToCreatePlaylist() {
			
		Playlist requestPlaylist = playListBuilder(FakerDataUtils.generatePlaylistName(), FakerDataUtils.generatePlaylistDescription(), false);		
		Response response = PlayListApi.post(requestPlaylist);		
		assertStatusCode(response.statusCode(), 201);		
		assertPlaylist(requestPlaylist, response.as(Playlist.class));		
	}
	
	
	@Test(description = "We should be able to update a playlist")
	public void shouldBeAbleToUpdateAPlaylist() {
		
		Playlist updatePlaylistObject =  playListBuilder("Update Name with pojo", "Update desciprtion with pojo", false);			
		Response response = PlayListApi.put(updatePlaylistObject, TestDataLoader.getInstance().getPlayListId());
		assertStatusCode(response.statusCode(), 200);				
	}	
	
	@Test(description = "We should be able to get a playlist")
	public void shouldBeAbleToGetAPlaylist() {		
		
		Response response = PlayListApi.get(TestDataLoader.getInstance().getPlayListId());
		assertStatusCode(response.statusCode(), 200);		
		Playlist responsePlaylist = response.as(Playlist.class);		
		System.out.println("Playlist name is : "+ responsePlaylist.getName() );	
	}	

	@Test(description = "We should not be able to create a playlist with missing attributes")
	public void negativeTestCreatePlaylistWithNoBody() {	
		
		Response response =  PlayListApi.post();
		assertStatusCode(response.statusCode(), 400);
		//assertErrorDetails(response.as(ErrorResponse.class), "Error parsing JSON.", 400);
		assertErrorDetails(response.as(ErrorResponse.class),StatusCode.CODE_400);
	}

	@Test(description = "We should not be able to create playlist with expired token")
	public void negativeTestCreatePlaylistWithExpiredToken() {
		
		Playlist createPlaylistObject = playListBuilder("Update Name with pojo", "Update desciprtion with pojo", false);		
		Response response =  PlayListApi.post(createPlaylistObject,"dummyToken");
		assertStatusCode(response.statusCode(), 401);		
		assertErrorDetails(response.as(ErrorResponse.class), StatusCode.CODE_401);

	}


}
