package viewmodel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import fileIO_classes.FileReader;
import fileIO_classes.FileWriter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model_classes.User;

public class LoginViewModel {
	private List<User> users;
	private StringProperty usernameProperty;
	private StringProperty passwordProperty;
	private StringProperty labelProperty;

	private StringProperty verifyPasswordProperty;

	public LoginViewModel() {
		this.users = new ArrayList<User>();
		this.usernameProperty = new SimpleStringProperty();
		this.passwordProperty = new SimpleStringProperty();
		this.labelProperty = new SimpleStringProperty();
		this.verifyPasswordProperty = new SimpleStringProperty();
		this.loadUsers();
	}

	public StringProperty usernameProperty() {
		return this.usernameProperty;
	}

	public StringProperty passwordProperty() {
		return this.passwordProperty;
	}

	public StringProperty labelProperty() {
		return this.labelProperty;
	}

	public StringProperty verifyPasswordProperty() {
		return this.verifyPasswordProperty;
	}

	public boolean checkUserExists() {
		if (users != null) {
			for (User currentUser : users) {
				if (currentUser.verifyCredentials(usernameProperty.get(), passwordProperty.get())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Loads user
	 */
	public void loadUsers() {
		try {
			FileReader reader = new FileReader();
			this.users = reader.loadUsersFromFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Creates a user account
	 */
	public void createUserAccount() {
		this.addUser();

		FileWriter writer = new FileWriter();
		try {
			writer.write(users);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Verifies the password
	 * 
	 * @return
	 */
	public boolean verifyPassword() {
		String password = this.passwordProperty.get();
		String verify = this.verifyPasswordProperty.get();

		if (password.equals(verify)) {
			return true;
		}

		return false;
	}

	/**
	 * Adds a user
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void addUser() {
		String username = this.usernameProperty.get();
		String password = this.passwordProperty.get();

		User user = new User(username, password);
		if (!this.users.contains(user)) {
			users.add(user);
		} else {
			this.labelProperty.set("User does not exist, please create an account.");
		}
	}

}
