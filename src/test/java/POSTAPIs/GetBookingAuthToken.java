package POSTAPIs;

import io.restassured.RestAssured;
import java.io.File;
import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class GetBookingAuthToken {
	
	@Test
	public void getBookingAuthTokenTest_WithJSONpayloadinsidethePOSTCall() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		String token =	given()
			.contentType(ContentType.JSON)
			.body("{\r\n"
					+ "    \"username\" : \"admin\",\r\n"
					+ "    \"password\" : \"password123\"\r\n"
					+ "}")
			.when()
				.post("/auth")
					.then()
						.assertThat()
							.statusCode(200)
								.extract()
									.path("token");
		System.out.println("*****getBookingAuthTokenTest_WithJSONpayloadinsidethePOSTCall() method starts*****");
		System.out.println("Token extracted from response: "+token);
		Assert.assertNotNull(token);
	}
	
	@Test
	public void getBookingAuthTokenTest_WithJSOFileinresourceFolder() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		String token =	given()
			.contentType(ContentType.JSON)
			.body(new File("./src/test/resources/RequestPayloads/BookingAuthRequestPayload.json"))
			.when()
				.post("/auth")
					.then()
						.assertThat()
							.statusCode(200)
								.extract()
									.path("token");
		
		System.out.println("*****getBookingAuthTokenTest_WithJSOFileinresourceFolder method starts*****");
		System.out.println("Token extracted from response: "+token);
		Assert.assertNotNull(token);
	}

}
