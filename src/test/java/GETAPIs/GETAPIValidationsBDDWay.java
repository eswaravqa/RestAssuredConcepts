package GETAPIs;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


import org.testng.annotations.Test;

public class GETAPIValidationsBDDWay {
	
	@Test
	public void getProductsTest() {
		
		given().log().all()
			.when().log().all()
				.get("https://fakestoreapi.com/products")
					.then().log().all()
						.assertThat().statusCode(200)
							.and()
								.contentType(ContentType.JSON)
									.and()
										.header("Connection", "keep-alive")
											.and()
												.body("$.size()", equalTo(20))
													.and()
														.body("id", is(notNullValue()))
															.and()
																.body("title", hasItem("Mens Cotton Jacket"));		
			
	}	

}
