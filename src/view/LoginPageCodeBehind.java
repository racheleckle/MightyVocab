package view;

import java.io.IOException;

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
import viewmodel.LoginViewModel;

public class LoginPageCodeBehind {

	private LoginViewModel viewModel;
	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	private Hyperlink createaccountHyperlink;

	@FXML
	private Label invalidCredentialsLabel;

	@FXML
	private Button loginButton;

	@FXML
	private PasswordField passwordPasswordField;

	@FXML
	private TextField usernameTextField;

	public LoginPageCodeBehind() {
		this.viewModel = new LoginViewModel();
	}

	@FXML
	private void initialize() {
		this.bindToLoginViewModel();
	}

	private void bindToLoginViewModel() {
		this.usernameTextField.textProperty().bindBidirectional(this.viewModel.usernameProperty());
		this.passwordPasswordField.textProperty().bindBidirectional(this.viewModel.passwordProperty());
		this.invalidCredentialsLabel.textProperty().bindBidirectional(this.viewModel.labelProperty());

	}

	@FXML
	void userLogin(ActionEvent event) throws IOException {
		if (this.viewModel.checkUserExists()) {
			root = FXMLLoader.load(getClass().getResource("NotecardsPage.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} else {
			this.invalidCredentialsLabel.setVisible(true);
			this.invalidCredentialsLabel.textProperty().set("Invalid credentials, if you don't have an account please create one now.");
		}
	}

	@FXML
	void goToCreateAccountPage(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("CreateAccountPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
