package resource;

public enum apicall 
{
	Addplaceapi("maps/api/place/add/json"),
	getplaceapi("maps/api/place/get/json"),
	deleteplaceapi("maps/api/place/delete/json");
	String apicll;
	
	apicall(String api) 
	{
		this.apicll = api;
	}
	
	public String returnmethod()
	{
		return apicll;
	}

	
}
