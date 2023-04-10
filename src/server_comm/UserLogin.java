package server_comm;

public class UserLogin {

	private String username;
	private String password;

	public UserLogin() {
		this.setUsername("");
		this.setPassword("");
	}

	public UserLogin(String name, String password) {
		if (name == null) {
			throw new IllegalArgumentException("Name cannot be null");
		}
		this.setUsername(name);
		this.setPassword(password);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if (username == null) {
			throw new IllegalArgumentException("Name cannot be null");
		}
		if (username.isEmpty()) {
			throw new IllegalArgumentException("Name cannot be empty");
		}
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (password == null) {
			throw new IllegalArgumentException("Password cannot be null");
		}
		if (password.isEmpty()) {
			throw new IllegalArgumentException("Password cannot be empty");
		}
		this.password = password;
	}
}
