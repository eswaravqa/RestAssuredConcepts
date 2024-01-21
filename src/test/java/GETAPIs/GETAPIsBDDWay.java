package GETAPIs;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class GETAPIsBDDWay {
	
	@DataProvider
	public Object[][] getYearData() {
		return new Object[][] {
			{"2016",21},
			{"2017",20},
			{"1966",9},
			{"2023",22},			
		};		
	}
	
	@Test (dataProvider = "getYearData")
	public void getCurrentYearDatawithDataProvider(String currentYear, int numofCircuits) {
		RestAssured.baseURI = "http://ergast.com";
		
		given().log().all()
			.pathParam("year",currentYear)
				.when().log().all()
					.get("/api/f1/{year}/circuits.json")
						.then()
							.assertThat()
								.statusCode(200)
									.and()
										.body("MRData.CircuitTable.season", equalTo(currentYear))
											.and()
												.body("MRData.CircuitTable.Circuits.circuitId", hasSize(numofCircuits));
		
	}
	
	

}
