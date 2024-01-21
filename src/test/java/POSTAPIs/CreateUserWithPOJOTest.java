package POSTAPIs;

import POJO.User;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateUserWithPOJOTest {
	
	public static String randomEmail() {
		return "someemail"+System.currentTimeMillis()+"@hotmail.com";	
		//another way to create unique number
		//return "apiautomation"+UUID.randomUUID()+"@good.com";
		//But in some applications UUID has hiphen in it which is not allwoed in email id creation.
	}
	
	@Test
	public void CreateUserWithPOJOclass() {
		RestAssured.baseURI = "https://gorest.co.in/";
		//Set user with request body values : Call to POJO class created
		User addUser = new User("Naveen", "male", randomEmail(), "active");
		
		System.out.println("*****Post method starts*****");
		
		//Execute Post call and extract the id from response. Perform assertion for name and response code 
		int userId = given()
				.contentType(ContentType.JSON)			
				.body(addUser)
				.header("Authorization", "Bearer d9087f8fe361cc113c178208d30189fb59ee0cb58522e394292c88f798fc8d4a")
			.when().log().all()
				.post("/public/v2/users/")
			.then().log().all()
				.assertThat()
					.statusCode(201)
					.and()
					.body("name", equalTo(addUser.getName()))
			.extract()
			.path("id");
		
		System.out.println("User Id from response body: "+userId);
		
		System.out.println("*****GET method and POST Response validations(assertions)  starts*****");
		given()
			.contentType(ContentType.JSON)	
			.header("Authorization", "Bearer d9087f8fe361cc113c178208d30189fb59ee0cb58522e394292c88f798fc8d4a")
		.when().log().all()
			.get("/public/v2/users/"+userId)
		.then().log().all()
			.assertThat()
				.statusCode(200)
			.and()
				.body("id", equalTo(userId))
			.and()
				.body("name", equalTo(addUser.getName()))
				.body("email", equalTo(addUser.getEmail()))
				.body("status", equalTo(addUser.getStatus()))
				.body("gender", equalTo(addUser.getGender()));
			
	}
}
