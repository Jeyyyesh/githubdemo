package StepDefinition;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cucumber.Options.Utilities;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.*;
import resource.TestDataBuild;
import resource.apicall;



public class StepDefinition_cucumber extends Utilities 
{
	
	RequestSpecification res;
	RequestSpecification ress;
	ResponseSpecification resspec;
	Response response;
	Response responses;
	static String resp;	
	TestDataBuild td = new TestDataBuild();
	static String placeid;	
	
	@Given("add place payload with {string} {string} {string}")
	public void add_place_payload(String nam, String lang, String add) throws IOException 
	{			
				
		res = given().spec(requestspecification()).body(td.addplacebody(nam,lang,add));			
		//RestAssured.baseURI="https://rahulshettyacademy.com";	
		
	}
	@When("user calls addplaceapi with POST method {string} {string}")
	public void user_calls_addplaceapi_with_post_method(String apiname, String method) 
	{
				
		apicall resource = apicall.valueOf(apiname);
		
		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST"))
		{
			response = res.when().post(resource.returnmethod());		
		}
		else if(method.equalsIgnoreCase("GET"))
		{		 
			response = res.when().get(resource.returnmethod());
		}
		
	}
	@Then("API call got success with status code {int}")
	public void api_call_got_success_with_status_code(Integer int1) 
	{
		assertEquals(response.getStatusCode(), 200);  
				
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyvalue, String expectedvalue) 
	{
	    assertEquals(responseget(response, keyvalue), expectedvalue);
	    
	}
	
	@Then("verify the name {string} is matching with response from {string}")
	public void verify_the_name_is_matching_with_response_from(String name, String apimethod) throws IOException 
	{
	   placeid = responseget(response, "place_id");		   
	   
	   /*res = res.given().queryParam("key", "qaclick123").queryParam("place_id", placeid);
	   Response getplace = res.when().get("maps/api/place/get/json");		
	   System.out.println("direct getplace"+getplace);
	   String actualnamee = responseget(getplace, "name");
	   System.out.println("name"+actualnamee);*/
	   
	   /*Response getplace = res.given().contentType(ContentType.JSON).queryParam("place_id", placeid).queryParam("key", "qaclick123").log().all()
				.when().get("maps/api/place/get/json");		
	   System.out.println("direct getplace"+getplace);
	   String actualnamee = responseget(getplace, "name");
	   System.out.println("name"+actualnamee);*/
	   
	   
	   
	   //res = res.queryParam("key", "qaclick123").queryParam("place_id", placeid);
	   res = given().spec(requestspecification()).queryParam("place_id", placeid);
	   user_calls_addplaceapi_with_post_method(apimethod,"GET");   
	   res.log().all();
	   String actualname = responseget(response, "name");	
	   assertEquals(actualname,name);	 
	   
	}
	
	@Given("delete place using place id")
	public void delete_place_using_place_id()
	{
		try {
			System.out.println("deleteapi");
			res = given().spec(requestspecification()).body(td.deleteplacebody(placeid));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
