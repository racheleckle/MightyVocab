package viewmodel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fileIO_classes.FileReader;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model_classes.User;
import server_comm.UserLogin;

public class LoginViewModel {
	private final List<User> users;
	private final UserLogin login;
	private final StringProperty usernameProperty;
	private final StringProperty passwordProperty;
	private final StringProperty labelProperty;

	public LoginViewModel() {
		users = new ArrayList<>();
		usernameProperty = new SimpleStringProperty();
		passwordProperty = new SimpleStringProperty();
		labelProperty = new SimpleStringProperty();
		login = new UserLogin(this.usernameProperty.get(), this.passwordProperty.get());
		loadUsers();
	}

	public boolean checkUserExists() {
		Optional<User> user = users.stream()
				.filter(u -> u.verifyCredentials(usernameProperty.get(), passwordProperty.get())).findFirst();
		if (user.isPresent()) {
			return true;
		}
		labelProperty.set("User does not exist, please create an account.");
		return false;
	}

	public StringProperty usernameProperty() {
		return usernameProperty;
	}

	public StringProperty passwordProperty() {
		return passwordProperty;
	}

	public StringProperty labelProperty() {
		return labelProperty;
	}

	public User confirmLogin() {
		String username = this.usernameProperty.getValue();
		String password = this.passwordProperty.getValue();
		if (username.equals("") || password.equals("")) {
			return null;
		}
		login.setUsername(username);
		login.setPassword(password);
		User user = login.verifyCredentials();
		return user;
	}

	private void loadUsers() {
		try {
			FileReader reader = new FileReader();
			users.addAll(reader.loadUsersFromFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	private void addUser() {
//		User newUser = new User(usernameProperty.get(), passwordProperty.get());
//		users.add(newUser);
//		FileWriter writer = new FileWriter();
//		writer.saveUsersToFile(users);
//	}
}