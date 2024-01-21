package SpecificationConcept;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ReqResSpecifications {
	
	public static RequestSpecification req_spec_builder() {
		RequestSpecification requestSpec = new RequestSpecBuilder()
				.setBaseUri("http://gorest.co.in")
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", "Bearer d9087f8fe361cc113c178208d30189fb59ee0cb58522e394292c88f798fc8d4a")
				.build();
		return requestSpec;
	}
	public static ResponseSpecification getResponseSpec_200_0K() {
		ResponseSpecification resSpec200OK = new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.expectStatusCode(200)
				.expectStatusLine("HTTP/1.1 200 OK")
				.expectHeader("Server", "cloudflare")
				.build();
		return resSpec200OK;			
	}

}
