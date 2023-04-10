package manager;

import java.util.ArrayList;
import java.util.List;

import model_classes.User;

public class UserManager {
	private static UserManager single_instance = null;
	
	private List<User> users;
	
	public UserManager() {
		this.users = new ArrayList<User>();
	}
	
	public static UserManager getInstance() {
		if (single_instance == null) {
			single_instance = new UserManager();
		}
		return single_instance;
	}
	
	public List<User> getUsers(){
		if (this.users.isEmpty()) {
			//this.users = RequestManager.getUsers(); getting users from client
		}
		return this.users;
	}
	
	/**
	 * Adds a user
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean addUser(String username, String password) {
		if (username == null) {
			throw new IllegalArgumentException("username cannot be null");
		}
		if (username.isEmpty()) {
			throw new IllegalArgumentException("username cannot be empty");
		}
		if (password == null) {
			throw new IllegalArgumentException("password cannot be null");
		}
		if (password.isEmpty()) {
			throw new IllegalArgumentException("password cannot be null");
		}
		
		User newUser = new User(username, password);
		
		return this.users.add(newUser);
		
	}
	
	
}
