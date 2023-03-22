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

public class CreateAccountPageCodeBehind {

	private LoginViewModel viewModel;

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

	public CreateAccountPageCodeBehind() {
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
	void addUserToText(ActionEvent event) {
		this.viewModel.createUserAccount();
		try {
			root = FXMLLoader.load(getClass().getResource("NotecardsPage.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void userCreateAccount(ActionEvent event) {

	}

	@FXML
	void goToLoginPage(ActionEvent event) throws IOException {

		root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
