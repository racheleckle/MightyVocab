package server_comm;

import manager.NotecardManager;
import model_classes.User;

public class UserLogin {

	private String username;
	private String password;

	public UserLogin(String name, String password) {
		this.setUsername(name);
		this.setPassword(password);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public User verifyCredentials() {
		try {
			return NotecardManager.verifyPassword(this.username, this.password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}