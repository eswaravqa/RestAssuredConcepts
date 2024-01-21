package io.com.qa.schemavalidations;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class SchemaValidation {
	
	@Test
	public void createUserSchemaValidation() {
		RestAssured.baseURI = "https://gorest.co.in/";
		 given().log().all()
				.contentType(ContentType.JSON)			
				.body(new File("./src/test/resources/RequestPayloads/GoRestAPICreateUserRequestPayload.json"))
				.header("Authorization", "Bearer d9087f8fe361cc113c178208d30189fb59ee0cb58522e394292c88f798fc8d4a")
			.when()
				.post("/public/v2/users/")
			.then().log().all()
				.assertThat()
					.statusCode(201)
						.and()
							.assertThat()
								.body(matchesJsonSchemaInClasspath("createuserschema.json"));
				
	}
	@Test
	public void getUserSchemaValidation() {
		RestAssured.baseURI = "https://gorest.co.in/";
		 given().log().all()
				.contentType(ContentType.JSON)			
				.header("Authorization", "Bearer d9087f8fe361cc113c178208d30189fb59ee0cb58522e394292c88f798fc8d4a")
			.when()
				.get("/public/v2/users/")
			.then().log().all()
				.assertThat()
					.statusCode(200)
						.and()
							.assertThat()
								.body(matchesJsonSchemaInClasspath("getuserschema.json"));
				
	}


}
