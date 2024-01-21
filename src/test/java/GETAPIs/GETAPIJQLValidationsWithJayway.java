package GETAPIs;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;

public class GETAPIJQLValidationsWithJayway {

	@Test
	public void GETAPI_JQLValidations_WithJaywayClass() {
		
		RestAssured.baseURI = "http://ergast.com";
		Response response = given().log().all()
				.when()
				.get("/api/f1/2017/circuits.json");
		
		String jsonResponse = response.asString();
		System.out.println(jsonResponse);
		
		int totalCircuits = JsonPath.read(jsonResponse, "$.MRData.CircuitTable.Circuits.length()");
		System.out.println("Total Circuits :"+totalCircuits);
		//1.Single List (Single attribute)
		List<String> countryList = JsonPath.read(jsonResponse, "$..Circuits..country");
		System.out.println("Country List Size : "+countryList.size());
		System.out.println("Country List : "+countryList);				
	}
	
	@Test 
	public void getProductsTest() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		Response fakeStoreRes = given()
										.when()
										.get("/products");
		
		System.out.println("*****get Products Test() method starts*****");
		String fSRes = fakeStoreRes.asString();
		System.out.println(fSRes);
		
		List<Float> rateLessThanThree = JsonPath.read(fSRes,"$[?(@.rating.rate<3)].rating.rate");
		System.out.println("List of Json response with rate less than three : "+rateLessThanThree);
		
		System.out.println("*****----------------------------****");
		
		//2.Two Attributes 
		List<Map<String, Object>> jewellryList = JsonPath.read(fSRes, "$[?(@.category == 'jewelery')].[\"title\", \"price\"]");	
		System.out.println(jewellryList);
		
		for (Map<String, Object> product : jewellryList) {
			String title = (String)product.get("title");
			Object price = (Object)product.get("price");
			
			System.out.println("title : "+title);
			System.out.println("price : "+price);
			System.out.println("---------------------------------------------------");
			
		//3.Three Attributes
			List<Map<String, Object>> jewellryList2 = JsonPath.read(fSRes, "$[?(@.category == 'jewelery')].[\"title\", \"price\", \"id\"]");	
			System.out.println(jewellryList);
			System.out.println("*****----------------------------****");
			
			for (Map<String, Object> p : jewellryList2) {
				String title1 = (String)p.get("title");
				Object price1 = (Object)p.get("price");
				Integer id = (Integer)p.get("id");			
				
				System.out.println("title : "+title1);
				System.out.println("price : "+price1);
				System.out.println("id : "+id);
				System.out.println("---------------------------------------------------");
			}
		}
		
	}
	
	
	
	
	
	
	
}
