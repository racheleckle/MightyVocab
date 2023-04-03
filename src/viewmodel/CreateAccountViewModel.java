package viewmodel;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model_classes.User;

public class CreateAccountViewModel {

	private StringProperty username = new SimpleStringProperty();
	private StringProperty password = new SimpleStringProperty();
	private StringProperty confirmPassword = new SimpleStringProperty();
	private StringProperty label = new SimpleStringProperty();

	private List<User> users = new ArrayList<>();

	public String getUsername() {
		return this.username.get();
	}

	public void setUsername(String username) {
		this.username.set(username);
	}

	public StringProperty usernameProperty() {
		return this.username;
	}

	public String getPassword() {
		return this.password.get();
	}

	public void setPassword(String password) {
		this.password.set(password);
	}

	public StringProperty passwordProperty() {
		return this.password;
	}

	public String getConfirmPassword() {
		return this.confirmPassword.get();
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword.set(confirmPassword);
	}

	public StringProperty confirmPasswordProperty() {
		return this.confirmPassword;
	}

	public String getLabel() {
		return this.label.get();
	}

	public void setLabel(String label) {
		this.label.set(label);
	}

	public StringProperty labelProperty() {
		return this.label;
	}

	public boolean passwordMatchesConfirmation() {
		return this.password.get().equals(this.confirmPassword.get());
	}

	public void createUserAccount() {
		if (doesUsernameExist()) {
			setLabel("Username already exists");
			return;
		}
		User newUser = new User(this.username.get(), this.password.get());
		this.users.add(newUser);
		setLabel("Account created successfully");
	}

	public boolean doesUsernameExist() {
		for (User user : users) {
			if (user.getUsername().equals(this.username.get())) {
				return true;
			}
		}
		return false;
	}
}
