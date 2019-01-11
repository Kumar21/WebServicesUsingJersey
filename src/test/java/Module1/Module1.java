package Module1;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.core.Cookie;


import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class Module1 {
	@Test
	public void stepModule1() {
		RegisterUser registerUser = new RegisterUser();
		registerUser.RegisterUserMethod();
	    HashMap<String,String> info = registerUser.RegisterUserMethod();
		LoginUser loginuser = new LoginUser();
		ArrayList<Cookie> ck = loginuser.loginUserId(info.get("username").toString(),info.get("password").toString());
		addItem("808a2de1-1aaa-4c25-a9b9-6612e8f29a38",ck);
		addUserAddress(ck);
		addCardProfile(ck);
		placeAndVerifyOrder(ck);

	}
	
	  public void addItem(String itemId, ArrayList<Cookie> cookie) {
			Client client = Client.create();

			WebResource webResource = client.resource("http://10.12.40.220:30001/cart");
			
			HashMap<String,String> item =new HashMap<String,String>();
			item.put("id", itemId);

			ClientResponse response = webResource.accept("application/json").type("application/json").
					cookie(new Cookie(cookie.get(0).getName(),cookie.get(1).getValue())).
					cookie(new Cookie(cookie.get(0).getName(),cookie.get(1).getValue()))
					.post(ClientResponse.class, new Gson().toJson(item).toString());
			
			if (response.getStatus() != 201) {
				System.out.println("Status not found");
			}
			
			System.out.println("Status = "+response.getStatus());
			response =	client.resource("http://10.12.40.220:30001/cart").type("application/json").
					cookie(new Cookie(cookie.get(0).getName(),cookie.get(1).getValue())).
					cookie(new Cookie(cookie.get(0).getName(),cookie.get(1).getValue()))
					.get(ClientResponse.class);
			System.out.println("Response = "+response.getEntity(String.class));
			System.out.println("Item added sucessfully  \n");

	  }
	  
	  
	 public void addUserAddress(ArrayList<Cookie> cookie) {
			Client client = Client.create();

			WebResource webResource = client.resource("http://10.12.40.220:30001/addresses");

			HashMap<String,String> userAddress=new HashMap<String,String>();
			userAddress.put("number", "401");
			userAddress.put("street", "RamdevNagar");
			userAddress.put("city", "Ahmedabad");
			userAddress.put("postcode", "385011");
			userAddress.put("country", "india");
		
			ClientResponse response = webResource.accept("application/json").type("application/json").
					cookie(new Cookie(cookie.get(0).getName(),cookie.get(1).getValue())).
					cookie(new Cookie(cookie.get(0).getName(),cookie.get(1).getValue())).
					post(ClientResponse.class, new Gson().toJson(userAddress).toString());	
			
			if (response.getStatus() != 200) {
				System.out.println("Status not found");
			}
			System.out.println("Status = "+response.getStatus());
			response = client.resource("http://10.12.40.220:30001/address").type("application/json").
					cookie(new Cookie(cookie.get(0).getName(),cookie.get(1).getValue())).
					cookie(new Cookie(cookie.get(0).getName(),cookie.get(1).getValue())).
					get(ClientResponse.class);
			
	//		 response = client.resource("http://10.12.40.220:30001/address").accept("application/json").type("application/json").cookie(cookie.get(0)).cookie(cookie.get(1)).(ClientResponse.class);

			System.out.println("Response = "+response.getEntity(String.class));

			System.out.println("Address added sucessfully  \n");

	  }
		public void addCardProfile(ArrayList<Cookie> cookie)
		{
			Client client = Client.create();

			WebResource webResource = client.resource("http://10.12.40.220:30001/cards");

			HashMap<String,String> userAddress=new HashMap<String,String>();
			userAddress.put("longNum", "9876543210");
			userAddress.put("expires", "10/20");
			userAddress.put("ccv", "125");
		
			ClientResponse response = webResource.accept("application/json").type("application/json").
					cookie(new Cookie(cookie.get(0).getName(),cookie.get(1).getValue())).
					cookie(new Cookie(cookie.get(0).getName(),cookie.get(1).getValue()))
					.post(ClientResponse.class, new Gson().toJson(userAddress).toString());		
			
			if (response.getStatus() != 200) {
				System.out.println("Status not found");
			}
			
			System.out.println("Status ="+response.getStatus());
			response = client.resource("http://10.12.40.220:30001/card").type("application/json").
					cookie(new Cookie(cookie.get(0).getName(),cookie.get(1).getValue())).
					cookie(new Cookie(cookie.get(0).getName(),cookie.get(1).getValue()))
					.get(ClientResponse.class);		
			System.out.println("Response = " + response.getEntity(String.class));

			System.out.println("Added card in Profile");
		}
		
		public void placeAndVerifyOrder( ArrayList<Cookie> cookie) {
			Client client = Client.create();
			WebResource webResource = client.resource("http://10.12.40.220:30001/orders");
			Cookie ck1 = cookie.get(0);
			Cookie ck2= cookie.get(1);
					
	ClientResponse response = webResource.accept("application/json").type("application/json").cookie(ck1).cookie(ck2).post(ClientResponse.class);

			if (response.getStatus() != 201) {
				System.out.println("Status not found");
			}
			
			System.out.println("Status = " + response.getStatus());
			System.out.println("Response = " + response.getEntity(String.class));

			System.out.println(
					"Place and Verify Order sucessfully \n");

		}

}
