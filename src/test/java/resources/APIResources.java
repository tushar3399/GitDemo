package resources;


// enum is a special collection of constant and methods
public enum APIResources {

	AddPlaceAPU("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlacePAI("/maps/api/place/delete/json");
	private String resource;
	
	APIResources(String resource)
	{
			this.resource=resource;
			
	}
	
	public String getResource()
	{
			return resource;
			
	}
}
