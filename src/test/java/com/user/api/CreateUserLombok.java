package com.user.api;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;



public class CreateUserLombok {
	
	public static String getRandomEmail() {
		return "someemail"+System.currentTimeMillis()+"@hotmail.com";		
	}
	@Test
	public void CreateUserLombokPOSTandGETTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		
		User user = new  User("eswar", getRandomEmail(), "male", "active");			
						
		//if we don't want  to do all static methods import we can use the following way
		
	Response createUserRes = 	RestAssured.given()
								.contentType(ContentType.JSON)
								.header("Authorization", "Bearer d9087f8fe361cc113c178208d30189fb59ee0cb58522e394292c88f798fc8d4a")
								.body(user)  //at this step Serialization happens 
								.when()
								.post("/public/v2/users");
	
	Integer userId = createUserRes.jsonPath().get("id");
	System.out.println("Userid: "+userId);
	
	//GET api to get the same user 
		Response getRes = RestAssured.given()
			.header("Authorization", "Bearer d9087f8fe361cc113c178208d30189fb59ee0cb58522e394292c88f798fc8d4a")
			.when().log().all()
			.get("/public/v2/users/"+userId);
			
	//de-serialization 
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			User getUserRes = mapper.readValue(getRes.getBody().asString(), User.class);
			
			System.out.println(getUserRes.getId()+ ":" +getUserRes.getEmail()+":"+getUserRes.getGender()+":"+getUserRes.getStatus());
			
			///Assert.assertEquals(userId, getUserRes.getId());
			Assert.assertEquals(user.getName(), getUserRes.getName());
			Assert.assertEquals(userId, getUserRes.getId());			
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
	}
	
	@Test
	public void CreateUserLombokBuilderPatternTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		//We can define user with builder patter in the following way. @Builder annotation in lombok is responsible for this 
	User user =	new User.UserBuilder()	
		.name("eswar")
		.email(getRandomEmail())
		.gender("male")
		.status("active")
		.build();
						
		//if we don't want  to do all static methods import we can use the following way
		
	Response createUserRes = 	RestAssured.given()
								.contentType(ContentType.JSON)
								.header("Authorization", "Bearer d9087f8fe361cc113c178208d30189fb59ee0cb58522e394292c88f798fc8d4a")
								.body(user)  //at this step Serialization happens 
								.when()
								.post("/public/v2/users");
	
	Integer userId = createUserRes.jsonPath().get("id");
	System.out.println("Userid: "+userId);
	
	//GET api to get the same user 
		Response getRes = RestAssured.given()
			.header("Authorization", "Bearer d9087f8fe361cc113c178208d30189fb59ee0cb58522e394292c88f798fc8d4a")
			.when().log().all()
			.get("/public/v2/users/"+userId);
			
	//de-serialization 
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			User getUserRes = mapper.readValue(getRes.getBody().asString(), User.class);
			
			System.out.println(getUserRes.getId()+ ":" +getUserRes.getEmail()+":"+getUserRes.getGender()+":"+getUserRes.getStatus());
			
			///Assert.assertEquals(userId, getUserRes.getId());
			Assert.assertEquals(user.getName(), getUserRes.getName());
			Assert.assertEquals(userId, getUserRes.getId());			
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
								
		
	}
	
	
}
