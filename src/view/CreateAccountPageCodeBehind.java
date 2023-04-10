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
	private Label errorMessageLabel;

	@FXML
	private Hyperlink loginpageHyperlink;

	@FXML
	private PasswordField passwordPasswordField;

	@FXML
	private PasswordField verifyPasswordField;

	@FXML
	private TextField usernameTextField;

	public CreateAccountPageCodeBehind() {
		this.viewModel = new LoginViewModel();
	}

	@FXML
	private void initialize() {
		this.bindToLoginViewModel();
		this.bindButtons();
		this.setupListeners();
	}

	private void bindToLoginViewModel() {
		this.usernameTextField.textProperty().bindBidirectional(this.viewModel.usernameProperty());
		this.passwordPasswordField.textProperty().bindBidirectional(this.viewModel.passwordProperty());
		this.errorMessageLabel.textProperty().bindBidirectional(this.viewModel.labelProperty());

		this.verifyPasswordField.textProperty().bindBidirectional(this.viewModel.verifyPasswordProperty());
	}

	@FXML
	void userCreateAccount(ActionEvent event) {
		this.viewModel.createUserAccount();
		try {
			root = FXMLLoader.load(getClass().getResource("NotecardsPage.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
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

	private void setupListeners() {
		this.verifyPasswordField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				if (this.viewModel.verifyPassword()) {
					this.errorMessageLabel.textProperty().set("");
				}else if(newValue.isEmpty()) {
					this.errorMessageLabel.textProperty().set("");
				} else {
					this.errorMessageLabel.textProperty().set("Passwords do not match");
				}
			}
		});
	}

	private void bindButtons() {
		this.createAccountButton.disableProperty()
				.bind(this.usernameTextField.textProperty().isEmpty().or(this.passwordPasswordField.textProperty()
						.isEmpty().or(this.verifyPasswordField.textProperty().isEmpty().or(this.errorMessageLabel.textProperty().isNotEmpty()))));
	}

}
