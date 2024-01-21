package GETAPIs;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class GETAPIsExtractResponseJsonValues {

	@Test
	public void getArrayElements_inList() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response response = given().log().all()
										.queryParam("limit", 5)
											.when().log().all()
												.get("/products");
		
		JsonPath js = response.jsonPath();
		List<Integer> idList = js.getList("id");
		List<String> titleList = js.getList("title");
		
		System.out.println("Print Id List ");
		for (Integer i : idList) {
			System.out.println(i);
		}
		
		System.out.println("Print tiitle List ");
		for (String o : titleList) {
			System.out.println(o);
		}
	}	
	
		@Test
		public void getResValues_WithExtractMethod() {
			RestAssured.baseURI = "https://gorest.co.in";
			
			System.out.println("***getResValues_WithExtractMethod() starts ***");
			
		Response goResResponse = 	given().log().all()		
													.header("Authorization", "Bearer d9087f8fe361cc113c178208d30189fb59ee0cb58522e394292c88f798fc8d4a")
												.when()
													.get("/public/v2/users/3571519")
												.then()
													.extract()
													.response();
		
			int userId = goResResponse.path("id");
			String email = goResResponse.path("email");
			
			System.out.println(userId);
			System.out.println(email);
			
		}
			
}
	

