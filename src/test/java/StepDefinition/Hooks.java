package StepDefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@deleteplace")
	public void beforescenario() throws IOException 
	{
		StepDefinition_cucumber a = new StepDefinition_cucumber();
		if(StepDefinition_cucumber.placeid==null) 
		{
			a.add_place_payload("Nick", "Telugu", "Bengaluru");
			a.user_calls_addplaceapi_with_post_method("Addplaceapi", "POST");
			a.verify_the_name_is_matching_with_response_from("Nick", "getplaceapi");
		}
				
	}

}
