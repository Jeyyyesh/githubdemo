package StepDiifination;

import org.testng.AssertJUnit;

import cucumber.Options.Utilities;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



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
import pojo.*;
import resource.*;



public class StepDefination extends Utilities {
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data =new TestDataBuild();
	static String place_id;
	
	
	@Given("add place payload with {string} {string} {string}")
	public void add_place_payload(String nam, String lang, String add) throws IOException  {
	    // Write code here that turns the phrase above into concrete actions
	
		 
		 res=given().spec(requestSpecification())
		.body(data.addplacebody(nam,lang,add));
	}

	@When("user calls addplaceapi with POST method {string} {string}")
	public void user_calls_addplaceapi_with_post_method(String resource, String method)  {
	    // Write code here that turns the phrase above into concrete actions
//constructor will be called with value of resource which you pass
	apicall resourceAPI=apicall.valueOf(resource);
		System.out.println(resourceAPI.returnmethod());
		
		
		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST"))
		 response =res.when().post(resourceAPI.returnmethod());
		else if(method.equalsIgnoreCase("GET"))
			 response =res.when().get(resourceAPI.returnmethod());
		
}

	@Then("API call got success with status code {int}")
	public void api_call_got_success_with_status_code(Integer int1)  {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(),200);
		
	
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyvalue, String expectedvalue)  {
	    // Write code here that turns the phrase above into concrete actions
	  
		assertEquals(responseget(response,keyvalue),expectedvalue);
	}
	
	@Then("verify the name {string} is matching with response from {string}")
	public void verify_the_name_is_matching_with_response_from(String name, String resource) throws IOException {
	
	   // requestSpec
	     place_id=responseget(response,"place_id");
		 res=given().spec(requestSpecification()).queryParam("place_id",place_id);
		 //res = res.queryParam("key", "qaclick123").queryParam("place_id", place_id);
		 user_calls_addplaceapi_with_post_method(resource,"GET");
		  String actualName=responseget(response,"name");
		  assertEquals(actualName,name);
		 
	    
	}
	

@Given("delete place using place id")
public void delete_place_using_place_id() throws IOException {
    // Write code here that turns the phrase above into concrete actions
   
	res =given().spec(requestSpecification()).body(data.deleteplacebody(place_id));
}



}
