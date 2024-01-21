package GETAPIs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

//*************Non BDD Approach***************

public class GETAPIValidations {
	RequestSpecification request;

	@BeforeTest
	public void setup() {
		//Request
		RestAssured.baseURI = "https://gorest.co.in/";
		request = RestAssured.given();
		request.header("Authorization", "Bearer d9087f8fe361cc113c178208d30189fb59ee0cb58522e394292c88f798fc8d4a");

	}

	@Test (priority = 1)
	public void getUserAPITest() {

		System.out.println("*******************************************************");
		System.out.println("Start of New Method: GET User API Test");

		Response response = request.get("public/v2/users/");

		// Add filters in query parameters
		// Response response = request.get("public/v2/users?name=naveen&status=active");

		// capture Status Code
		int statusCode = response.statusCode();
		System.out.println("Status Code: " + statusCode);

		// Status code verification
		Assert.assertEquals(statusCode, 200);

		// Capture Status message
		String statusMesg = response.statusLine();
		System.out.println("Status Message : " + statusMesg);

		// Status Message Verification
		Assert.assertEquals(statusMesg, "HTTP/1.1 200 OK");

		// Fetch Response body
		response.prettyPrint();

		// Fetch Header information : Single Header or Specific Header
		String contentType = response.header("Content-Type");
		System.out.println(contentType);

		// Fetch all Headers
		List<Header> headerList = response.headers().asList();
		System.out.println(headerList.size());

		System.out.println("------------------------");
		// From above list iterate all values and Print on console
		for (Header h : headerList) {
			System.out.println(h.getName() + " : " + h.getValue());
		}

	}

	@Test (priority = 2)
	public void GETUserswithQueryParam() {

		System.out.println("*******************************************************************");
		System.out.println("Start of New Method: GET Users with Query Parameters");

		// Better way of adding query parameters is add them as separate query parameters -- Better than above procedure
		request.queryParam("name", "Naveen");
		request.queryParam("status", "active");

		Response response = request.get("public/v2/users/");

		// capture Status Code
		int statusCode = response.statusCode();
		System.out.println("Status Code: " + statusCode);

		// Status code verification
		Assert.assertEquals(statusCode, 200);

		//Fetch response body in Pretty format
		response.prettyPrint();
	}

	@Test (priority = 3)
	public void GETUsersWithQueryParaminHashMap() {
		
		System.out.println("********************************************************************************");
		System.out.println("Start of New Method: GET User with Query Parameters in Hash Map");	

		Map<String, String> queryParamHash = new HashMap<String, String>();
		queryParamHash.put("name", "Naveen");
		queryParamHash.put("status", "active");

		request.queryParams(queryParamHash);
		
		Response response = request.get("public/v2/users/");

		// capture Status Code
		int statusCode = response.statusCode();
		System.out.println("Status Code: " + statusCode);

		// Status code verification
		Assert.assertEquals(statusCode, 200);

		//Fetch response body in Pretty format
				response.prettyPrint();
	}

}
