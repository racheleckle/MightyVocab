package manager;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model_classes.RequestType;
import model_classes.User;
import server_comm.Client;

public class NotecardManager {

	private static JSONObject createRequestJson(Object... data) {
		JSONObject json = new JSONObject();
		if (data.length % 2 == 0) {
			for (int i = 0; i < data.length; i += 2) {
				try {
					json.put(data[i].toString(), data[i + 1]);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
		return json;
	}

	private static JSONObject sendRequest(RequestType type, JSONObject request) {
		Client client = new Client(type, request.toString());
		try {
			client.start();
			String response = client.sendRequest();
			return new JSONObject(response);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<User> createUserList(JSONArray jsonArray) {
		List<User> users = new ArrayList<>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject userJson = jsonArray.optJSONObject(i);
			if (userJson != null) {
				try {
					User user = User.fromJson(userJson);
					users.add(user);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return users;
	}

	public static User verifyPassword(String username, String password) {
		JSONObject request = createRequestJson(username, password);
		JSONObject response = sendRequest(RequestType.VERIFY_PASSWORD, request);
		if (response != null && response.optInt("isValid") == 1) {
			JSONObject userJson = response.optJSONObject("user");
			if (userJson != null) {
				try {
					return User.fromJson(userJson);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static List<User> getUsers() {
		JSONObject request = new JSONObject();
		JSONObject response = sendRequest(RequestType.GET_USER, request);
		if (response != null && response.optInt("response") == 1) {
			return createUserList(response.optJSONArray("users"));
		}
		return null;
	}

	public static boolean addUser(User user) {
		JSONObject request = createRequestJson(user.getUsername(), user.getPassword(), user.toJSON());
		JSONObject response = sendRequest(RequestType.ADD_USER, request);
		return response != null && response.optInt("response") == 1;
	}

	public static boolean removeUser(String username) {
		JSONObject request = createRequestJson(username);
		JSONObject response = sendRequest(RequestType.REMOVE_USER, request);
		return response != null && response.optInt("response") == 1;
	}

// public static boolean updateUser(User user) {
// JSONObject request = createRequestJson(user.GetUsername(), user.toJSON());
// JSONObject response = sendRequest(RequestType.UPDATE_USER, request);
// return response != null && response.optInt("response") == 1;
// }
}