package io.com.qa.petstore.api;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.com.qa.petstore.api.PetLombok.Category;
import io.com.qa.petstore.api.PetLombok.Tag;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreatPetTest {

	@Test
	public void createPetTest() {
		RestAssured.baseURI = "https://petstore.swagger.io";

		Category cat1 = new Category(102, "Italy breed");
		List<String> photoUrls = Arrays.asList("https://dog.com/IB-loony1.jpg", "https://dog.com/IB-loony2.jpg");
		Tag tag1 = new Tag(202, "Tag1");
		Tag tag2 = new Tag(203, "Tag2");
		List<Tag> tag = Arrays.asList(tag1, tag2);

		PetLombok pet1 = new PetLombok(600, cat1, "loony1", photoUrls, tag, "active");

		Response createPetRes = RestAssured.given().body(pet1) // serialization is happening at this line
				.contentType(ContentType.JSON).when().log().all().post("/v2/pet");

		System.out.println("Response code:  " + createPetRes.statusCode());
		createPetRes.prettyPrint();

		// De-serialization
		ObjectMapper objMap = new ObjectMapper();

		try {
			PetLombok deSePetRes = objMap.readValue(createPetRes.getBody().asString(), PetLombok.class);

			System.out.println("*****************************************");
			System.out.println("De Serialized Response values ");
			System.out.println(deSePetRes.getId());
			System.out.println(deSePetRes.getName());

			System.out.println(deSePetRes.getCategory().getId());
			System.out.println(deSePetRes.getCategory().getName());

			System.out.println(deSePetRes.getPhotoUrls());

			System.out.println(deSePetRes.getTags().get(0).getName());
			System.out.println(deSePetRes.getTags().get(0).getId());

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
	public void createPetTest_WithBuilderPattern() {
		RestAssured.baseURI = "https://petstore.swagger.io";

		Category cat1 = new Category.CategoryBuilder()
				.id(102)
					.name("Italy breed")
						.build();
		
		Tag tag1 = new Tag.TagBuilder()
				.id(1)
					.name("Tag1")
						.build();
		Tag tag2 = new Tag.TagBuilder()
				.id(2)
					.name("Tag2")
								.build();

		PetLombok pet1 = new PetLombok.PetLombokBuilder()
					.id(800)
						.category(cat1)
							.name("cat")
								.photoUrls(Arrays.asList("https://cat.com/IB-Moony1.jpg", "https://cat.com/IB-Moony2.jpg"))
									.tags(Arrays.asList(tag1, tag2))
										.status("available")
											.build();

		Response createPetRes = RestAssured.given()
				.body(pet1) // serialization is happening at this line
					.contentType(ContentType.JSON)
					.when().log().all()
						.post("/v2/pet");

		System.out.println("Response code:  " + createPetRes.statusCode());
		createPetRes.prettyPrint();

		// De-serialization
		ObjectMapper objMap = new ObjectMapper();

		try {
			PetLombok deSePetRes = objMap.readValue(createPetRes.getBody().asString(), PetLombok.class);

			System.out.println("*****************************************");
			System.out.println("De Serialized Response values ");
			System.out.println(deSePetRes.getId());
			System.out.println(deSePetRes.getName());

			System.out.println(deSePetRes.getCategory().getId());
			System.out.println(deSePetRes.getCategory().getName());

			System.out.println(deSePetRes.getPhotoUrls());

			System.out.println(deSePetRes.getTags().get(0).getName());
			System.out.println(deSePetRes.getTags().get(0).getId());

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
