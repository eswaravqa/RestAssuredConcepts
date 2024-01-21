package POSTAPIs;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class PostNGetB2BOAuth2dot0 {
	static String accessToken;
	
	@BeforeMethod
	public void getAccessToken() {
		RestAssured.baseURI = "https://test.api.amadeus.com";	
		//1. Get the access token by executing POST call 
		accessToken = 	given()
				.header("Content-Type", "application/x-www-form-urlencoded")
				.formParam("grant_type", "client_credentials")
				.formParam("client_id", "TAnRnsU5lASXZ8mPGdwRQZMoQzhu6Gwv")
				.formParam("client_secret", "VjjgfcJilNAzcSJw")
			.when()
				.post("/v1/security/oauth2/token")
			.then()
				.assertThat()
				.statusCode(200)
				.extract().path("access_token");		
		System.out.println("Access Toke : "+accessToken);
	}

	@Test
	public void getFlightInfoTest() {
		
	//2.Supply access token in get call and get the flight details 
		Response flightDetailsResponse = given().log().all()
			.header("Authorization", "Bearer "+accessToken)
			.queryParam("origin", "PAR")
			.queryParam("maxPrice", 200)
		.when().log().all()
			.get("/v1/shopping/flight-destinations")
		.then().log().all()
			.assertThat()
			.statusCode(200)
		.and()
			.extract()
			.response();
		
		JsonPath js = flightDetailsResponse.jsonPath();
		String type = js.getString("data[0].type");
		System.out.println("Type in the response is :  "+type);
		
	}
		
}
