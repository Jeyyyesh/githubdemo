package resource;

import java.util.ArrayList;
import java.util.List;

import pojo.addgoogleapi;
import pojo.googlelocation;

public class TestDataBuild 
{
	public addgoogleapi addplacebody(String namee, String languagee, String Addresss)
	{
		addgoogleapi ag = new addgoogleapi();
		
		googlelocation l = new googlelocation();
				
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		ag.setLocation(l);
				
		ag.setAccuracy(50);
		ag.setName(namee);
		ag.setPhone_number("(+91) 983 893 3937");
		ag.setAddress(Addresss);			
				
		List<String> t = new ArrayList<String>();
		t.add("shoe park");
		t.add("shop");
		ag.setTypes(t);
		
		ag.setWebsite("http://google.com");
		ag.setLanguage(languagee);
		
		return ag;
	}
	
	public String deleteplacebody(String placeid)
	{
		return "{\r\n    \"place_id\":\""+placeid+"\"\r\n}";
	}

}
