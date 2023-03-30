package model_classes;

//import org.json.JSONException;
//import org.json.JSONObject;
//import manager.RequestManager;
//import manager.UserManager;

/**
 * User Class
 * 
 * @author William Clark
 * @version 2/6/2023
 */
public class User {
	private String username;
	private String password;

	/**
	 * Instantiates an instance of a User
	 * 
	 * @param username
	 * @param password
	 */
	public User(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	/**
	 * Gets username
	 * 
	 * @return username
	 */
	public String GetUsername() {
		return this.username;
	}

	/**
	 * Functionality to set the username
	 * 
	 * @param username
	 * @return true if username is being set
	 */
	private boolean setUsername(String username) {
		if (username == null) {
			throw new IllegalArgumentException("Username cannot be null.");
		}
		if (username.isEmpty()) {
			throw new IllegalArgumentException("Username cannot be empty.");
		}
		this.username = username;
		return true;
	}

	/**
	 * Gets password
	 * 
	 * @return password
	 */
	public String GetPassword() {
		return this.password;
	}

	/**
	 * Functionality to set the password
	 * 
	 * @param password
	 * @return true if password is being set
	 */
	private boolean setPassword(String password) {
		if (password == null) {
			throw new IllegalArgumentException("Password cannot be null.");
		}
		if (password.isEmpty()) {
			throw new IllegalArgumentException("Password cannot be empty.");
		}
		this.password = password;
		return true;
	}

	/**
	 * Verify user has valid credentials
	 * 
	 * @param username
	 * @param password
	 * @return true if valid credentials, false otherwise
	 */
	public boolean verifyCredentials(String username, String password) {
		return this.username.equals(username) && this.password.equals(password);
	}
	
	@Override
	public String toString() {
		return this.username + resources.ResourceMessages.SEPARATOR + this.password;
	}
	
//	public JSONObject toJSON() {
//		JSONObject json = new JSONObject();
//		
//		try {
//			json.put("__user__", true);
//			json.put("username", this.username);
//			json.put("password", this.password);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return json;
//	}
	
//	public static User fromJSON(JSONObject json) throws JSONException {
//		UserManager requestManager = UserManager.getInstance();
//		
//		String username = json.getString("username");
//		String password = json.getString("password");
//		
//		User user = new User(username, password);
//		
//		return user;
//	}
}
