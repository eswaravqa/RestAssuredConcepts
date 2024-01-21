package POSTAPIs;
import POJO.BookingAuthLogin;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;



public class BookingWithPOJO {
	
	@Test
	public void getBookingAuthTokenTest_WithPOJO() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		BookingAuthLogin login = new BookingAuthLogin("admin", "password123");
		
		String token =	given().log().all()
			.contentType(ContentType.JSON)			
			.body(login)
			.when()
				.post("/auth")
					.then().log().all()
						.assertThat()
							.statusCode(200)
								.extract()
									.path("token");
		System.out.println("*****getBookingAuthTokenTest_WithJSONpayloadinsidethePOSTCall() method starts*****");
		System.out.println("Token extracted from response: "+token);
		Assert.assertNotNull(token);
	}
}
