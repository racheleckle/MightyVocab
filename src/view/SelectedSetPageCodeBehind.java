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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model_classes.Notecard;

public class SelectedSetPageCodeBehind {

	private Stage stage;
	private Scene scene;
	private Parent root;

	private final ObjectProperty<Notecard> selectedProperty;

	@FXML
	private Button addToSetButton;

	@FXML
	private Button createNotecardButton;

	@FXML
	private TableColumn<Notecard, String> definitionTableColumn;

	@FXML
	private Button deleteNotecardButton;

	@FXML
	private Button editNotecardButton;

	@FXML
	private MenuItem logoutMenuItem;

	@FXML
	private MenuButton menuMenuButton;

	@FXML
	private MenuItem notecardsMenuItem;

	@FXML
	private TextField notecardsSearchBarTextField;

	@FXML
	private Button notecardsSearchButton;

	@FXML
	private TableView<Notecard> notecardsTableView;

	@FXML
	private MenuItem setsMenuItem;

	@FXML
	private MenuItem settingsMenuItem;

	@FXML
	private MenuItem studyMenuItem;

	@FXML
	private TableColumn<Notecard, String> termTableColumn;

	@FXML
	private MenuItem userStatisticsMenuItem;

	@FXML
	private Button viewNotecardButton;

	public SelectedSetPageCodeBehind() {
		this.selectedProperty = new SimpleObjectProperty<Notecard>();
	}

	@FXML
	private void initialize() {

	}

	@FXML
	void addNotecardToSet(ActionEvent event) {

	}

	@FXML
	void createNotecard(ActionEvent event) {

		Label createNotecardLabel = new Label("Create Notecard");
		Label termLabel = new Label("Term:");
		TextField termTextField = new TextField();
		Label definitionLabel = new Label("Definition:");
		TextArea definitionTextArea = new TextArea();
		Button confirmButton = new Button("Confirm");
		Button cancelButton = new Button("Cancel");
		Font font = Font.font("Cambria", 18);
		Font fontBig = Font.font("Cambria", FontWeight.BOLD, 24);

		createNotecardLabel.setFont(fontBig);
		createNotecardLabel.setTextFill(Color.DARKSLATEGREY);
		termLabel.setFont(font);
		termLabel.setTextFill(Color.DARKSLATEGREY);
		definitionLabel.setFont(font);
		definitionLabel.setTextFill(Color.DARKSLATEGREY);
		confirmButton.setFont(font);
		confirmButton.setTextFill(Color.DARKSLATEGREY);
		cancelButton.setFont(font);
		cancelButton.setTextFill(Color.DARKSLATEGREY);

		VBox pane = new VBox(15);
		pane.setPadding(new Insets(75, 100, 75, 100));
		pane.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(createNotecardLabel, termLabel, termTextField, definitionLabel, definitionTextArea,
				confirmButton, cancelButton);

		Scene scene = new Scene(new Group(pane), Color.ALICEBLUE);
		stage = new Stage();
		stage.setTitle("Create Notecard");
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
	void deleteNotecard(ActionEvent event) {

		Dialog<String> confirmationDialog = new Dialog<String>();
		confirmationDialog.setTitle("Delete Notecard");
		ButtonType deleteButton = new ButtonType("Delete", ButtonData.NO);
		ButtonType cancelButton = new ButtonType("Cancel", ButtonData.YES);
		confirmationDialog.setContentText("Are you sure you want to delete this notecard?" + System.lineSeparator()
				+ System.lineSeparator() + "Term: " + "{notecard term}" + System.lineSeparator()
				+ System.lineSeparator() + "Definition:" + System.lineSeparator() + "{notecard definition}");
		confirmationDialog.getDialogPane().getButtonTypes().addAll(deleteButton, cancelButton);

		confirmationDialog.showAndWait();
	}

	@FXML
	void editNotecard(ActionEvent event) {

		Label createNotecardLabel = new Label("Edit Notecard");
		Label termLabel = new Label("Term:");
		TextField termTextField = new TextField("{notecard term}");
		Label definitionLabel = new Label("Definition:");
		TextArea definitionTextArea = new TextArea("{notecard definition}");
		Button confirmButton = new Button("Confirm");
		Button cancelButton = new Button("Cancel");
		Font font = Font.font("Cambria", 18);
		Font fontBig = Font.font("Cambria", FontWeight.BOLD, 24);

		createNotecardLabel.setFont(fontBig);
		createNotecardLabel.setTextFill(Color.DARKSLATEGREY);
		termLabel.setFont(font);
		termLabel.setTextFill(Color.DARKSLATEGREY);
		definitionLabel.setFont(font);
		definitionLabel.setTextFill(Color.DARKSLATEGREY);
		confirmButton.setFont(font);
		confirmButton.setTextFill(Color.DARKSLATEGREY);
		cancelButton.setFont(font);
		cancelButton.setTextFill(Color.DARKSLATEGREY);

		VBox pane = new VBox(15);
		pane.setPadding(new Insets(75, 100, 75, 100));
		pane.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(createNotecardLabel, termLabel, termTextField, definitionLabel, definitionTextArea,
				confirmButton, cancelButton);

		Scene scene = new Scene(new Group(pane), Color.ALICEBLUE);
		stage = new Stage();
		stage.setTitle("Edit Notecard");
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
	void searchNotecards(ActionEvent event) {

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
	void viewNotecard(ActionEvent event) {

		Label createNotecardLabel = new Label("Notecard");
		Label termLabel = new Label("Term: " + "{notecard term}");
		Label definitionLabel = new Label("Definition:");
		Label notecardDefinitionLabel = new Label("{notecard definition}");
		Button okButton = new Button("OK");
		Font font = Font.font("Cambria", 18);
		Font fontBig = Font.font("Cambria", FontWeight.BOLD, 24);

		createNotecardLabel.setFont(fontBig);
		createNotecardLabel.setTextFill(Color.DARKSLATEGREY);
		termLabel.setFont(font);
		termLabel.setTextFill(Color.DARKSLATEGREY);
		definitionLabel.setFont(font);
		definitionLabel.setTextFill(Color.DARKSLATEGREY);
		notecardDefinitionLabel.setFont(font);
		notecardDefinitionLabel.setTextFill(Color.DARKSLATEGREY);
		okButton.setFont(font);
		okButton.setTextFill(Color.DARKSLATEGREY);

		VBox pane = new VBox(15);
		pane.setPadding(new Insets(75, 100, 75, 100));
		pane.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(createNotecardLabel, termLabel, definitionLabel, notecardDefinitionLabel, okButton);

		Scene scene = new Scene(new Group(pane), Color.ALICEBLUE);
		stage = new Stage();
		stage.setTitle("View Notecard");
		stage.setScene(scene);
		stage.show();

		okButton.setOnAction(e -> {
			stage.close();
		});
	}

}
