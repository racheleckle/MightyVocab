package view;

import java.io.IOException;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model_classes.Notecards;
import viewmodel.SetsViewModel;

public class SetsPageCodeBehind {

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private SetsViewModel viewmodel;
	private ObjectProperty<Notecards> selectedNotecards;
	
	@FXML
	private Button addSetsButton;

	@FXML
	private Button editCardsButton;

	@FXML
	private TextField setNameTextfield;

	@FXML
	private ComboBox<Notecards> setsComboBox;

	@FXML
	private Button studyButton;
  
  @FXML
	private Button createSetButton;

	@FXML
	private Button deleteSetButton;

	@FXML
	private Button editSetButton;

	@FXML
	private MenuItem logoutMenuItem;

	@FXML
	private MenuButton menuMenuButton;

	@FXML
	private MenuItem notecardsMenuItem;

	@FXML
	private ListView<?> setsListView;

	@FXML
	private MenuItem setsMenuItem;

	@FXML
	private TextField setsSearchBarTextField;

	@FXML
	private Button setsSearchButton;

	@FXML
	private MenuItem settingsMenuItem;

	@FXML
	private Button shareSetButton;

	@FXML
	private MenuItem studyMenuItem;

	@FXML
	private MenuItem userStatisticsMenuItem;

	@FXML
	private Button viewSetButton;
	
	public SetsPageCodeBehind() {
		this.viewmodel = new SetsViewModel();
		this.selectedNotecards = new SimpleObjectProperty<Notecards>();
	}
	
	@FXML
	private void initialize() {
		this.bindComponentsToViewModel();
	}
	
	private void bindComponentsToViewModel() {
		this.setNameTextfield.textProperty().bindBidirectional(this.viewmodel.setsNameProperty());
		this.setsComboBox.itemsProperty().bind(this.viewmodel.setsListProperty());
		this.selectedNotecards.bindBidirectional(this.viewmodel.selectedSetProperty());
		
	}

	@FXML
	void addSetOnClick(ActionEvent event) {
		this.viewmodel.addNotecards();
	}

	@FXML
	void editCardsInSetOnAction(ActionEvent event) {
		// Go to Manage Sets Page
	}

	@FXML
	void goStudySet(ActionEvent event) throws IOException {
		// Go to Study page; Not sure which study page; and have to send the name of the list and list itself
		root = FXMLLoader.load(getClass().getResource("Studying.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	}

	@FXML
	void shareSet(ActionEvent event) {
		Label shareLabel = new Label("Share with who?");
		Label usernameLabel = new Label("Username: ");
		TextField usernameTextField = new TextField();
		Button confirmButton = new Button("Confirm");
		Button cancelButton = new Button("Cancel");
		Font font = Font.font("Cambria", 18);
		Font fontBig = Font.font("Cambria", FontWeight.BOLD, 24);

		shareLabel.setFont(fontBig);
		shareLabel.setTextFill(Color.DARKSLATEGREY);
		usernameLabel.setFont(font);
		usernameLabel.setTextFill(Color.DARKSLATEGREY);
		confirmButton.setFont(font);
		confirmButton.setTextFill(Color.DARKSLATEGREY);
		cancelButton.setFont(font);
		cancelButton.setTextFill(Color.DARKSLATEGREY);

		VBox pane = new VBox(15);
		pane.setPadding(new Insets(75, 100, 75, 100));
		pane.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(shareLabel, usernameLabel, usernameTextField, confirmButton, cancelButton);

		Scene scene = new Scene(new Group(pane), Color.ALICEBLUE);
		stage = new Stage();
		stage.setTitle("Share Set");
		stage.setScene(scene);
		stage.show();

		confirmButton.setOnAction(e -> {
			stage.close();
		});

		cancelButton.setOnAction(e -> {
			stage.close();
		});
	}

	@FXML
	void createSet(ActionEvent event) {
		Label createSetLabel = new Label("Create Set");
		Label nameLabel = new Label("Name:");
		TextField nameTextField = new TextField();
		Button confirmButton = new Button("Confirm");
		Button cancelButton = new Button("Cancel");
		Font font = Font.font("Cambria", 18);
		Font fontBig = Font.font("Cambria", FontWeight.BOLD, 24);

		createSetLabel.setFont(fontBig);
		createSetLabel.setTextFill(Color.DARKSLATEGREY);
		nameLabel.setFont(font);
		nameLabel.setTextFill(Color.DARKSLATEGREY);
		confirmButton.setFont(font);
		confirmButton.setTextFill(Color.DARKSLATEGREY);
		cancelButton.setFont(font);
		cancelButton.setTextFill(Color.DARKSLATEGREY);

		VBox pane = new VBox(15);
		pane.setPadding(new Insets(75, 100, 75, 100));
		pane.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(createSetLabel, nameLabel, nameTextField, confirmButton, cancelButton);

		Scene scene = new Scene(new Group(pane), Color.ALICEBLUE);
		stage = new Stage();
		stage.setTitle("Create Set");
		stage.setScene(scene);
		stage.show();

		confirmButton.setOnAction(e -> {
			stage.close();
		});
		cancelButton.setOnAction(e -> {
			stage.close();
		});
	}

	@FXML
	void deleteSet(ActionEvent event) {
		Dialog<String> confirmationDialog = new Dialog<String>();
		confirmationDialog.setTitle("Delete Set");
		ButtonType deleteButton = new ButtonType("Delete", ButtonData.NO);
		ButtonType cancelButton = new ButtonType("Cancel", ButtonData.YES);
		confirmationDialog.setContentText("Are you sure you want to delete this set?" + System.lineSeparator()
				+ System.lineSeparator() + "Name: " + "{set name}");
		confirmationDialog.getDialogPane().getButtonTypes().addAll(deleteButton, cancelButton);

		confirmationDialog.showAndWait();
	}

	@FXML
	void editSet(ActionEvent event) {
		Label createSetLabel = new Label("Edit Set");
		Label nameLabel = new Label("Name:");
		TextField nameTextField = new TextField("{set name}");
		Button confirmButton = new Button("Confirm");
		Button cancelButton = new Button("Cancel");
		Font font = Font.font("Cambria", 18);
		Font fontBig = Font.font("Cambria", FontWeight.BOLD, 24);

		createSetLabel.setFont(fontBig);
		createSetLabel.setTextFill(Color.DARKSLATEGREY);
		nameLabel.setFont(font);
		nameLabel.setTextFill(Color.DARKSLATEGREY);
		confirmButton.setFont(font);
		confirmButton.setTextFill(Color.DARKSLATEGREY);
		cancelButton.setFont(font);
		cancelButton.setTextFill(Color.DARKSLATEGREY);

		VBox pane = new VBox(15);
		pane.setPadding(new Insets(75, 100, 75, 100));
		pane.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(createSetLabel, nameLabel, nameTextField, confirmButton, cancelButton);

		Scene scene = new Scene(new Group(pane), Color.ALICEBLUE);
		stage = new Stage();
		stage.setTitle("Edit Set");
		stage.setScene(scene);
		stage.show();

		confirmButton.setOnAction(e -> {
			stage.close();
		});
		cancelButton.setOnAction(e -> {
			stage.close();
		});
	}

	@FXML
	void goToNotecardsPage(ActionEvent event) throws IOException {

		root = FXMLLoader.load(getClass().getResource("NotecardsPage.fxml"));
		stage = (Stage) this.menuMenuButton.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void goToSetsPage(ActionEvent event) throws IOException {

		root = FXMLLoader.load(getClass().getResource("SetsPage.fxml"));
		stage = (Stage) this.menuMenuButton.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void goToSettingsPage(ActionEvent event) {

	}

	@FXML
	void goToStudyPage(ActionEvent event) throws IOException {

		root = FXMLLoader.load(getClass().getResource("Study.fxml"));
		stage = (Stage) this.menuMenuButton.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void goToUserStatisticsPage(ActionEvent event) throws IOException {

		root = FXMLLoader.load(getClass().getResource("UserStatisticsPage.fxml"));
		stage = (Stage) this.menuMenuButton.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void searchSets(ActionEvent event) {

	}

	@FXML
	void userLogout(ActionEvent event) throws IOException {

		root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
		stage = (Stage) this.menuMenuButton.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

		Dialog<String> confirmationDialog = new Dialog<String>();
		confirmationDialog.setTitle("Logged Out");
		ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
		confirmationDialog.setContentText("Successfully logged out. See you later!");
		confirmationDialog.getDialogPane().getButtonTypes().addAll(okButton);

		confirmationDialog.showAndWait();
	}

	@FXML
	void viewSet(ActionEvent event) throws IOException {

		root = FXMLLoader.load(getClass().getResource("SelectedSetPage.fxml"));
		stage = (Stage) this.menuMenuButton.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
