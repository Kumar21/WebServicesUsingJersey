package Module1;

import java.util.HashMap;

import org.apache.commons.lang3.RandomStringUtils;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.LoggingFilter;

public class RegisterUser {

	
	public HashMap<String,String>  RegisterUserMethod(){
		
			Client client = Client.create();
			client.addFilter(new LoggingFilter());
			
			WebResource webResource = client.resource("http://10.12.40.220:30001/register");
			
			HashMap<String,String> userInfo=new HashMap<String,String>();
			userInfo.put("username", "Kumar"+RandomStringUtils.randomAlphabetic(10));
			userInfo.put("password", "pass"+RandomStringUtils.randomAlphabetic(5));
			userInfo.put("email", "Kumar"+RandomStringUtils.randomAlphabetic(5)+"@Qmetry.com");
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.post(ClientResponse.class, new Gson().toJson(userInfo).toString());	
			
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			String output = response.getEntity(String.class);
			String id = output.substring(7,output.length()-2);
			System.out.println("id = " + id);
			System.out.println("Output = "+output);
			System.out.println("Register Sucessfully");
			
			return userInfo;
	}
}


