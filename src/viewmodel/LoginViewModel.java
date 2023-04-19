package viewmodel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import fileIO_classes.FileReader;
import fileIO_classes.FileWriter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import manager.RequestManager;
import model_classes.User;

public class LoginViewModel {
	private List<User> users;
	private StringProperty usernameProperty;
	private StringProperty passwordProperty;
	private StringProperty labelProperty;

	private StringProperty verifyPasswordProperty;
	
	private RequestManager manager;
	private User activeUser;

	public LoginViewModel() {
		this.users = new ArrayList<User>();
		this.usernameProperty = new SimpleStringProperty();
		this.passwordProperty = new SimpleStringProperty();
		this.labelProperty = new SimpleStringProperty();
		this.verifyPasswordProperty = new SimpleStringProperty();
		this.loadUsers();
		
		this.manager = new RequestManager();
		this.activeUser = null;
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

	public User getActiveUser() {
		return this.activeUser;
	}
	
	/**
	 * Checks if the user exists
	 * @return
	 */
	public boolean checkUserExists() {
		if (users != null) {
			for (User currentUser : users) {
				if (RequestManager.verifyPassword(usernameProperty.get(), passwordProperty.get()) != null) {
					System.out.println("Sent over object to verify");
				}
				if (currentUser.verifyCredentials(usernameProperty.get(), passwordProperty.get())) {
					this.setActiveUser(currentUser);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean setActiveUser(User user) {
		this.activeUser = user;
		return this.activeUser != null;
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
		if (!this.checkUserExists()) {
			users.add(user);
		} 
	}

}
