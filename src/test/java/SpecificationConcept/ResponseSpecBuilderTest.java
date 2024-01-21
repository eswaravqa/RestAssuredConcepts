package SpecificationConcept;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.*;



public class ResponseSpecBuilderTest {
	
	public static ResponseSpecification getResponseSpec_200_0K() {
		ResponseSpecification resSpec200OK = new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.expectStatusCode(200)
				.expectStatusLine("HTTP/1.1 200 OK")
				.expectHeader("Server", "cloudflare")
				.build();
		return resSpec200OK;			
	}
	
	public static ResponseSpecification getResponseSpec_200_0K_with_Body() {
		ResponseSpecification resSpec200OKwithBody = new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.expectStatusCode(200)
				.expectStatusLine("HTTP/1.1 200 OK")
				.expectHeader("Server", "cloudflare")
				.expectBody("$.size()", equalTo(10))
				.expectBody("id", hasSize(10))
				.build();
		return resSpec200OKwithBody;			
	}
	
	public static ResponseSpecification getResponseSpec_401_Auth_Fail() {
		ResponseSpecification resSpec401AuthFail = new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.expectStatusCode(401)
				.expectStatusLine("HTTP/1.1 401 Unauthorized")
				.expectHeader("Server", "cloudflare")
				.build();
		return resSpec401AuthFail;			
	}
		
	@Test
	public void get_user_res_200_spec_Test() {
		RestAssured.baseURI = "https://gorest.co.in/";
		 
		System.out.println("****get_user_res_200_spec_Test method starts*****");
		RestAssured.given().log().all()
			.header("Authorization", "Bearer d9087f8fe361cc113c178208d30189fb59ee0cb58522e394292c88f798fc8d4a")
			.when()
			.get("public/v2/users")
			.then()
			.assertThat()
			.spec(getResponseSpec_200_0K())
			.log().all();		
	}
	
	@Test
	public void get_user_res_200_spec_Test_withBody() {
		RestAssured.baseURI = "https://gorest.co.in/";
		 
		System.out.println("****getResponseSpec_200_0K_with_Body method starts*****");
		RestAssured.given().log().all()
			.header("Authorization", "Bearer d9087f8fe361cc113c178208d30189fb59ee0cb58522e394292c88f798fc8d4a")
			.when()
			.get("public/v2/users")
			.then()
			.assertThat()
			.spec(getResponseSpec_200_0K_with_Body())
			.log().all();		
	}
	
	@Test
	public void get_user_res_401_auth_fail_test() {
		RestAssured.baseURI = "https://gorest.co.in/";
		 
		System.out.println("****get_user_res_401_auth_fail_test method starts*****");
		RestAssured.given().log().all()
			.header("Authorization", "Bearer d9087f8fe361cc113c1aseff8d30189fb59ee0cb58522e394292c88f798fc8d4a")
			.when()
			.get("public/v2/users")
			.then()
			.assertThat()
			.spec(getResponseSpec_401_Auth_Fail())
			.log().all();		
	}	

}
