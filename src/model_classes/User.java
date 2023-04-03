package model_classes;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import manager.UserNotecardManager;

/**
 * User Class
 * 
 * @author William Clark
 * @version 2/6/2023
 */
public class User {
	private String username;
	private String password;
	private List<UserSets> notecardSets;

	public User(String username, String password) {
		setUsername(username);
		setPassword(password);
		notecardSets = new ArrayList<>();
	}

	public String getUsername() {
		return username;
	}

	private void setUsername(String username) {
		if (username == null || username.isEmpty()) {
			throw new IllegalArgumentException("Username cannot be null or empty.");
		}
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	private void setPassword(String password) {
		if (password == null || password.isEmpty()) {
			throw new IllegalArgumentException("Password cannot be null or empty.");
		}
		this.password = password;
	}

	public boolean verifyCredentials(String username, String password) {
		return this.username.equals(username) && this.password.equals(password);
	}

	@Override
	public String toString() {
		return username + resources.ResourceMessages.SEPARATOR + password;
	}

	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		JSONArray sets = new JSONArray();
		for (UserSets set : notecardSets) {
			sets.put(set.toJson());
		}
		try {
			json.put("__account__", true);
			json.put("username", username);
			json.put("password", password);
			json.put("Notecard Sets", sets);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

	public static User fromJson(JSONObject json) throws Exception {
		UserNotecardManager manager = UserNotecardManager.getInstance();
		String username = json.getString("username");
		String password = json.getString("password");
		JSONObject account = json.getJSONObject("__account__");
		User user = new User(username, password);
		JSONArray sets = account.optJSONArray("Notecard Sets");
		if (sets != null) {
			for (int i = 0; i < sets.length(); i++) {
				JSONObject setJson = sets.getJSONObject(i);
				String type = setJson.getString("type");
				String status = setJson.getString("status");
				UserSets set = new UserSets(type, status);
				manager.addUserRequest(set);
				user.notecardSets.add(set);
			}
		}
		return user;
	}
}
