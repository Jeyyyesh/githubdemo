Feature: Validating add place API

@Addplace
Scenario Outline: Verify whether place is getting added using post api method
Given add place payload with "<name>" "<language>" "<Address>"
When user calls addplaceapi with POST method "Addplaceapi" "POST"
Then API call got success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify the name "<name>" is matching with response from "getplaceapi"

Examples:
|name         |language     |Address   |  
|Rajkumar     |Tamil        |Chennai	 |		


@deleteplace
Scenario: Verify if place is getting deleted
Given delete place using place id
When user calls addplaceapi with POST method "deleteplaceapi" "POST"
Then API call got success with status code 200
And "status" in response body is "OK"