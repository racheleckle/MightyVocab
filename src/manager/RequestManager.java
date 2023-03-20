package manager;

import org.json.JSONObject;

import model_classes.RequestType;
import model_classes.User;
import server_comm.Client;

public class RequestManager {
	
	public static User verifyPassword(String username, String password) {
		
		try {
			JSONObject request = new JSONObject();
			request.put("username", username);
			request.put("password", password);
			Client client = new Client(request.toString(), RequestType.VERIFY_USER);
			
			client.start();
			String response = client.sendRequest();
			JSONObject json = new JSONObject(response);
			if (json.get("isValid").equals("1")) {
				return User.fromJSON(new JSONObject(json.get("user").toString()));
			} else {
				return null;
			}
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}

}
