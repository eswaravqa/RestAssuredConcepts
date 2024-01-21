package SpecificationConcept;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecBuilderTest {
	
	public static RequestSpecification req_spec_builder() {
		RequestSpecification requestSpec = new RequestSpecBuilder()
				.setBaseUri("http://gorest.co.in")
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", "Bearer d9087f8fe361cc113c178208d30189fb59ee0cb58522e394292c88f798fc8d4a")
				.build();
		return requestSpec;
	}
	
	@Test
	public void getUserTest_With_Request_SpecBuilder() {
		System.out.println("*****getUserTest_With_Request_SpecBuilder method starts*****");
		RestAssured.given().log().all()
			.spec(req_spec_builder())
				.get("/public/v2/users")
					.then()
						.statusCode(200);	
	}
		@Test
		public void getUserTest_With_queryParam_SpecBuilder() {
			
			System.out.println("*****getUserTest_With_queryParam_SpecBuilde method starts*****");
			RestAssured.given().log().all()
				.queryParam("name", "Naveen")
				.queryParam("status", "active")
					.spec(req_spec_builder())
						.get("/public/v2/users")
							.then()
								.statusCode(200);	
				}
					
	
}
