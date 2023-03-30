package viewmodel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fileIO_classes.FileReader;
import fileIO_classes.FileWriter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model_classes.User;

public class LoginViewModel {
	private final List<User> users;
	private final StringProperty usernameProperty;
	private final StringProperty passwordProperty;
	private final StringProperty labelProperty;

	public LoginViewModel() {
		users = new ArrayList<>();
		usernameProperty = new SimpleStringProperty();
		passwordProperty = new SimpleStringProperty();
		labelProperty = new SimpleStringProperty();
		loadUsers();
	}

	public boolean checkUserExists() {
		for (User user : users) {
			if (user.verifyCredentials(usernameProperty.get(), passwordProperty.get())) {
				return true;
			}
		}
		labelProperty.set("User does not exist, please create an account.");
		return false;
	}

	public void createUserAccount() {
		addUser();
		FileWriter writer = new FileWriter();
//		writer.saveUsersToFile(users);
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

	private void loadUsers() {
		try {
			FileReader reader = new FileReader();
			users.addAll(reader.loadUsersFromFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addUser() {
		User newUser = new User(usernameProperty.get(), passwordProperty.get());
		users.add(newUser);
	}
}