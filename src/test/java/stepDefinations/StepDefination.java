package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
// import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils{
	RequestSpecification res;
	ResponseSpecification resp;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	
	
	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		
		res=given().spec(requestSpecification())
		.body(data.addPlacePayload(name,language,address));
		
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
		
		APIResources resourceAPI=APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		resp=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("post"))
		response=res.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("get"))
			response=res.when().get(resourceAPI.getResource());
				
	}

	@Then("the API call is success with status code {int}")
	public void the_API_call_is_success_with_status_code(Integer int1) {
		
		assertEquals(response.getStatusCode(),200);
		
	}

	@Then("{string} in responsy body is {string}")
	public void in_responsy_body_is(String key, String value) {
	    // Write code here that turns the phrase above into concrete actions
		
		
		
		assertEquals(getJsonPath(response, key),value);
	
	}
	
	@Then("verify place_ID created maps to {string} using {string}")
	public void verify_place_ID_created_maps_to_using(String expectedName, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	  
		place_id=getJsonPath(response, "place_id");
		res=given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource,"get");
		String actualName=getJsonPath(response, "name");
		assertEquals(actualName, expectedName);
		
		
	}
	
	@Given("DeletePlace payload")
	public void deleteplace_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	  res =given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	  
	}



	
}
