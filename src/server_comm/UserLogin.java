package server_comm;

public class UserLogin {
	private String username;
	private String password;
	
	public UserLogin() {
		this.username = "";
		this.password = "";
	}
	
	public UserLogin(String name, String password) {
		if (name == null) {
			throw new IllegalArgumentException("Name cannot be null");
		}
		if (password == null) {
			throw new IllegalArgumentException("Password cannot be null");
		}
		
		this.username = name;
		this.password = password;
	}
	
}
