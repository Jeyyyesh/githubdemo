package cucumber.Options;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utilities 
{
	public static RequestSpecification req;
	ResponseSpecification resspec;
	
	public RequestSpecification requestspecification() throws IOException
	{
		RestAssured.reset();
		if(req==null)
		{
			PrintStream stream = new PrintStream(new FileOutputStream("logging.txt"));
			
			req = new RequestSpecBuilder().setBaseUri(baseurl("baseURL"))
				.addQueryParam("key", "qaclick123")				
				.addFilter(RequestLoggingFilter.logRequestTo(stream))
				.addFilter(ResponseLoggingFilter.logResponseTo(stream))
				.setContentType(ContentType.JSON)
				.build();			
			return req;
		}
		return req;		
	
	}
	
	
	public RequestSpecification requestSpecification() throws IOException
	{
		
		if(req==null)
		{
		PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
		 req=new RequestSpecBuilder().setBaseUri(baseurl("baseURL")).addQueryParam("key", "qaclick123")
				 .addFilter(RequestLoggingFilter.logRequestTo(log))
				 .addFilter(ResponseLoggingFilter.logResponseTo(log))
		.setContentType(ContentType.JSON).build();
		 return req;
		}
		return req;
		
		
	}
	
	
	/*public ResponseSpecification responsespecification()
	{
		resspec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200)
				//.expectHeader("Server", "Apache/2.4.52 (Ubuntu)")
				.log(LogDetail.ALL).build();
		return resspec;
	}*/
	
	public static String baseurl(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/cucumber/Options/dataFile.properties");
		prop.load(fis);
		return prop.getProperty(key);				
		
	}
	
	public String responseget(Response response, String key)
	{
		System.out.println("key"+key);
		String respp=response.asString();
		System.out.println("response"+respp);		
		JsonPath  j1 = new JsonPath(respp);
		String value = j1.get(key).toString();
		return value;
		
	}
}
