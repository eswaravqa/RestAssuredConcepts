package com.product.api;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductAPITest {
	
	@Test
	public void getProductTestWithPOJO() {
		RestAssured.baseURI = "https://fakestoreapi.com";
			Response response = given()
			.when()
			.get("/products");
			
			//Json - POJO mapping : De-serialization
			
			ObjectMapper mapper = new ObjectMapper();
			try {
			Product product[] =	mapper.readValue(response.getBody().asString() , Product[].class);
				for(Product p : product) {
					System.out.println("ID:  "+ p.getId());
					System.out.println("Title:  "+ p.getTitle());
					System.out.println("Price:  "+ p.getPrice());
					System.out.println("Description:  "+ p.getDescription());
					System.out.println("Category:  "+ p.getCategory());
					System.out.println("Image:  "+ p.getImage());
					System.out.println("Rating -> Rate:  "+ p.getRating().getRate());
					System.out.println("Rating -> Count:  "+ p.getRating().getCount());
					System.out.println("--------------------------------------------------------");
					
				}
			} catch (JsonParseException e) {		
				e.printStackTrace();
			} catch (JsonMappingException e) {			
				e.printStackTrace();
			} catch (IOException e) { 
				e.printStackTrace();
			}			
		
	}
	
	@Test
	public void getProductTestWithPOJOLombok() {
		RestAssured.baseURI = "https://fakestoreapi.com";
			Response response = given()
			.when()
			.get("/products");
			
			System.out.println("*********getProductTestWithPOJOLombok method starts*******");
			//Json - POJO mapping : De-serialization
			
			ObjectMapper mapper = new ObjectMapper();
			try {
				ProductLombok product[] =	mapper.readValue(response.getBody().asString() , ProductLombok[].class);
				for(ProductLombok p : product) {
					System.out.println("ID:  "+ p.getId());
					System.out.println("Title:  "+ p.getTitle());
					System.out.println("Price:  "+ p.getPrice());
					System.out.println("Description:  "+ p.getDescription());
					System.out.println("Category:  "+ p.getCategory());
					System.out.println("Image:  "+ p.getImage());
					System.out.println("Rating -> Rate:  "+ p.getRating().getRate());
					System.out.println("Rating -> Count:  "+ p.getRating().getCount());
					System.out.println("--------------------------------------------------------");
					
				}
			} catch (JsonParseException e) {		
				e.printStackTrace();
			} catch (JsonMappingException e) {			
				e.printStackTrace();
			} catch (IOException e) { 
				e.printStackTrace();
			}			
		
	}
	


}
