package Module1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class LoginUser {
	static String md_sid = "";
	public ArrayList<Cookie> loginUserId(String username, String password) {
		HTTPBasicAuthFilter auth = new HTTPBasicAuthFilter(username, password);

		Client client = Client.create();
		client.addFilter(auth);

		WebResource webResource = client.resource("http://10.12.40.220:30001/login");

		ClientResponse response = webResource.accept("application/json").type("application/json")
				.get(ClientResponse.class);
		
		List<NewCookie> cookies = response.getCookies();
		HashMap<String, String> cookieValue = new HashMap<String, String>();
		for (NewCookie newCookie : cookies) {
			cookieValue.put(newCookie.getName(), newCookie.getValue());
			
		}
		System.out.println("Status = "+response.getStatus());
		System.out.println("Response/Output = "+response.getEntity(String.class));
		System.out.println("md.sid = " + cookieValue.get("md.sid"));
		System.out.println("logged_in = "+cookieValue.get("logged_in"));
		
		
		
		md_sid = cookieValue.get("md.sid");
		Cookie  ck = new Cookie("logged_in",cookieValue.get("logged_in"));
		Cookie  ck1 = new Cookie("md.sid",cookieValue.get("md.sid"));
		 ArrayList<Cookie> list=new ArrayList<Cookie>();
		 list.add(ck);
		 list.add(ck1);

		System.out.println("Login sucessfully \n");

		return list;

	}
	
	
}
