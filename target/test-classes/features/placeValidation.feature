Feature: Validating place API
@AddPlace
Scenario Outline: verify if place is being successfully added using AddPlaceAPI
	Given Add place payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPU" with "Post" http request
	Then the API call is success with status code 200
	And "status" in responsy body is "OK"
	And "scope" in responsy body is "APP"
	And verify place_ID created maps to "<name>" using "getPlaceAPI"
	
Examples:
	|name| language|address|
	|tusharHouse|English|new center|
#	|BB house|hindi|ne oppo center|
	

@DeletePlace	
Scenario: verify if delete place functionality is working
	Given DeletePlace payload
	When user calls "deletePlacePAI" with "Post" http request
	Then the API call is success with status code 200
	And "status" in responsy body is "OK"