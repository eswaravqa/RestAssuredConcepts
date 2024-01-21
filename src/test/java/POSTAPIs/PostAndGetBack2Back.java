package POSTAPIs;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class PostAndGetBack2Back {
	
	@Test
	public void PostAndGetBack2Back_PassUserIdtoGET() {
		RestAssured.baseURI = "https://gorest.co.in/";
		
		//Execute Post call and extract the id from response. Perform assertion for name and response code 
		int userId = given()
				.contentType(ContentType.JSON)			
				.body(new File("./src/test/resources/RequestPayloads/GoRestAPICreateUserRequestPayload.json"))
				.header("Authorization", "Bearer d9087f8fe361cc113c178208d30189fb59ee0cb58522e394292c88f798fc8d4a")
			.when()
				.post("/public/v2/users/")
			.then()
				.assertThat()
					.statusCode(201)
					.and()
					.body("name", equalTo("naveen"))
			.extract()
			.path("id");
			
			System.out.println(userId);
			
			//Get Call takes id from above post call and pass as query parameter and validates response 
			
			given()
				.contentType(ContentType.JSON)	
				.header("Authorization", "Bearer d9087f8fe361cc113c178208d30189fb59ee0cb58522e394292c88f798fc8d4a")
			.when().log().all()
				.get("/public/v2/users/"+userId)
			.then()
				.assertThat()
				.statusCode(200)
			.and()
				.body("id", equalTo(userId));
			
	}

}
