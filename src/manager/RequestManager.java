package manager;

import org.json.JSONException;
import org.json.JSONObject;

import model_classes.RequestType;
import model_classes.User;
import server_comm.Client;

public class RequestManager {
	
	//private static RequestManager single_instance = null;

	public static User verifyPassword(String username, String password) {
		try {
			JSONObject request = new JSONObject();
			request.put("username", username); 
			request.put("password", password);
			Client client = new Client(request.toString(), "verifyUser" ); //RequestType.VERIFY_USER
			
			client.start();
			String response = client.sendRequest();
			JSONObject json = new JSONObject(response);
			
//			if (json.get("isValid").equals("1")) {
//				return User.fromJSON(new JSONObject(json.get("user").toString()));
//			} else {
//				return null;
//			}
			
			return User.fromJSON(new JSONObject(json.get("user").toString()));
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}
	
	public static boolean addUser(User user) {
		JSONObject request = new JSONObject();
		
		try {
			request.put("username", user.GetUsername());
			request.put("password", user.GetPassword());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		Client client = new Client(request.toString(), "addUser");
		
		try {
			client.start();
			String response = client.sendRequest();
			JSONObject json = new JSONObject(response);
			return json.get("response").equals("1");
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
	}
	
	
}
