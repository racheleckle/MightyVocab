package view;

import java.io.IOException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import viewmodel.CreateAccountViewModel;
import viewmodel.LoginViewModel;

public class CreateAccountPageCodeBehind {

	private CreateAccountViewModel viewModel;
	private LoginViewModel loginViewModel;

	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	private Button createAccountButton;

	@FXML
	private Label invalidCredentialsLabel;

	@FXML
	private Hyperlink loginpageHyperlink;

	@FXML
	private PasswordField passwordPasswordField;

	@FXML
	private TextField usernameTextField;

	@FXML
	private Label errorMessageLabel;

	@FXML
	private PasswordField verifyPasswordField;

	private StringProperty confirmPassword = new SimpleStringProperty();

	public CreateAccountPageCodeBehind() {
		this.loginViewModel = new LoginViewModel();
		this.viewModel = new CreateAccountViewModel();
	}

	@FXML
	private void initialize() {
		this.bindToLoginViewModel();
		this.confirmPassword.bindBidirectional(this.viewModel.confirmPasswordProperty());
	}

	private void bindToLoginViewModel() {
		this.usernameTextField.textProperty().bindBidirectional(this.loginViewModel.usernameProperty());
		this.passwordPasswordField.textProperty().bindBidirectional(this.loginViewModel.passwordProperty());
		this.invalidCredentialsLabel.textProperty().bindBidirectional(this.loginViewModel.labelProperty());
	}

	@FXML
	void userCreateAccount(ActionEvent event) {
		if (!this.viewModel.passwordMatchesConfirmation()) {
			this.viewModel.setLabel("Passwords do not match");
			return;
		}
		if (this.viewModel.doesUsernameExist()) {
			this.viewModel.setLabel("Username already exists");
			return;
		}
		this.viewModel.createUserAccount();
		try {
			System.out.println("Account created successfully!");
			this.goToLoginPage(event);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void goToLoginPage(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void setViewModel(CreateAccountViewModel viewModel) {
		this.viewModel = viewModel;
	}

	public CreateAccountViewModel getViewModel() {
		return this.viewModel;
	}

	@FXML
	public void addUserToText(ActionEvent event) {

	}
}