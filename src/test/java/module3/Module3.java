package module3;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.core.Cookie;

import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import Module1.LoginUser;
import Module1.RegisterUser;


public class Module3 {
	
	@Test
	public void stepModule3()
	{
		System.out.println("************************************** JESREY MODULE-3 *****************************************");

		RegisterUser registerUser = new RegisterUser();
		 HashMap<String,String> info = registerUser.RegisterUserMethod();
		
		LoginUser loginuser = new LoginUser();
		 ArrayList<Cookie> ck = loginuser.loginUserId(info.get("username").toString(),
				info.get("password").toString());
		 addItem("3395a43e-2d88-40de-b95f-e00e1502085b",ck);
		//add one more item
		 addItem("03fef6ac-1896-4ce8-bd69-b798f85c6e0b",ck);
		 updateItemBasket(ck,"3395a43e-2d88-40de-b95f-e00e1502085b","5");
		deleteBasket(ck, "3395a43e-2d88-40de-b95f-e00e1502085b");
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
		public void deleteBasket( ArrayList<Cookie> cookie, String itemId)
		{
			Client client = Client.create();

			WebResource webResource = client.resource("http://10.12.40.220:30001/cart/"+itemId);
			ClientResponse response = webResource.accept("application/json").type("application/json").
					cookie(new Cookie(cookie.get(0).getName(),cookie.get(0).getValue())).
					cookie(new Cookie(cookie.get(0).getName(),cookie.get(0).getValue()))
					.delete(ClientResponse.class);

			if (response.getStatus() != 202) {
				System.out.println("Status not found");
			}
			
			System.out.println("Status = " + response.getStatus());
			System.out.println("Delete from Basket sucessfully ============================================================================= \n");
			
		}
		public void updateItemBasket( ArrayList<Cookie> cookie, String itemId,String quatity)
		{
			Client client = Client.create();

			WebResource webResource = client.resource("http://10.12.40.220:30001/cart/update");
			
			HashMap<String,String> updateItem =new HashMap<String,String>();
			updateItem.put("id", itemId);
			updateItem.put("quantity",quatity);

			ClientResponse response = webResource.accept("application/json").type("application/json").
					cookie(new Cookie(cookie.get(0).getName(),cookie.get(0).getValue())).
					cookie(new Cookie(cookie.get(0).getName(),cookie.get(0).getValue()))
					.post(ClientResponse.class, new Gson().toJson(updateItem).toString());		
			
			if (response.getStatus() != 202) {
				System.out.println("Status not found");
			}
			
			System.out.println("Status = " + response.getStatus());
			System.out.println("Basket Updated sucessfully ============================================================================= \n");
			
		}

}
