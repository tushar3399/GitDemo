package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	
	public AddPlace addPlacePayload(String name,String language, String address)
	{
		
		AddPlace ad=new AddPlace();
		ad.setAccuracy(50);
		ad.setAddress(address);
		ad.setName(name);
		ad.setLanguage(language);
		ad.setPhone_number("(+91) 983 893 3937");
		ad.setWebsite("testindia.com");
		
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		
		ad.setTypes(myList);
		
		Location l=new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		ad.setLocation(l);
		return ad;
		
	}
	
	public String deletePlacePayload(String placeID)
	{
		return "{\r\n   \"place_id\":\""+placeID+"\"\r\n}";
		
	}

}
