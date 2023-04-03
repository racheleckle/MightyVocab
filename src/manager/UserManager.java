package manager;

import java.util.ArrayList;
import java.util.List;

import model_classes.User;

public class UserManager {

	private static final UserManager INSTANCE = new UserManager();
	private List<User> users = new ArrayList<>();

	private UserManager() {
	}

	public static UserManager getInstance() {
		return INSTANCE;
	}

	public List<User> getUsers() {
		if (users.isEmpty()) {
			// users = RequestManager.getUsers(); // getting users from client
		}
		return new ArrayList<>(users);
	}

	public boolean addUser(String username, String password) {
		if (username == null || username.isEmpty()) {
			throw new IllegalArgumentException("username cannot be null or empty");
		}
		if (password == null || password.isEmpty()) {
			throw new IllegalArgumentException("password cannot be null or empty");
		}
		User newUser = new User(username, password);
		return users.add(newUser);
	}
}
