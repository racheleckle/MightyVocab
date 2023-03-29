package view;

import java.io.IOException;
import java.util.ListIterator;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model_classes.Notecard;
import model_classes.Notecards;

public class NotecardsPageCodeBehind {

	private Stage stage;
	private Scene scene;
	private Parent root;

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

	/**
	 * Notecards Page Code Behind
	 */
	public NotecardsPageCodeBehind() {

	}

	@FXML
	private void initialize() {

	}

	/**
	 * Go to Notecards Page
	 * 
	 * @param event
	 */
	@FXML
	void goToNotecardsPage(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("NotecardsPage.fxml"));
		stage = (Stage) this.menuMenuButton.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Go to Notecard Sets Page
	 * 
	 * @param event
	 */
	@FXML
	void goToSetsPage(ActionEvent event) {

	}

	/**
	 * Go to Settings Page
	 * 
	 * @param event
	 */
	@FXML
	void goToSettingsPage(ActionEvent event) {

	}

	/**
	 * Go to Study Page
	 * 
	 * @param event
	 */
	@FXML
	void goToStudyPage(ActionEvent event) {

	}

	/**
	 * Go to User Statistics Page
	 * 
	 * @param event
	 */
	@FXML
	void goToUserStatisticsPage(ActionEvent event) {

	}

	/**
	 * Logs out the user and redirects them to the login page. Displays a
	 * confirmation dialog.
	 */
	@FXML
	void userLogout(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
		stage = (Stage) menuMenuButton.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

		Dialog<String> confirmationDialog = new Dialog<String>();
		confirmationDialog.setTitle("Logged Out");
		confirmationDialog.setContentText("Successfully logged out. See you later!");
		confirmationDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
		confirmationDialog.showAndWait();
	}

	/**
	 * Creates stage for creating notecards.
	 */
	@FXML
	void createNotecard(ActionEvent event) {
		ObservableList<Notecard> notecards = FXCollections.observableArrayList();

		Stage stage = new Stage();
		Font font = Font.font("Cambria", 18);
		Font fontBig = Font.font("Cambria", FontWeight.BOLD, 24);
		Label createNotecardLabel = new Label("Create Notecards");
		createNotecardLabel.setFont(fontBig);
		createNotecardLabel.setTextFill(Color.DARKSLATEGREY);

		Button addButton = new Button("Add Notecard");
		addButton.setFont(font);
		addButton.setTextFill(Color.DARKSLATEGREY);

		try {
			addButton.setOnAction(e -> {
				Stage notecardStage = new Stage();
				Label notecardLabel = new Label("Create Notecard");
				notecardLabel.setFont(fontBig);
				notecardLabel.setTextFill(Color.DARKSLATEGREY);

				Label termLabel = new Label("Term:");
				termLabel.setFont(font);
				termLabel.setTextFill(Color.DARKSLATEGREY);
				TextField termTextField = new TextField();

				Label definitionLabel = new Label("Definition:");
				definitionLabel.setFont(font);
				definitionLabel.setTextFill(Color.DARKSLATEGREY);
				TextArea definitionTextArea = new TextArea();

				Button confirmButton = new Button("Confirm");
				confirmButton.setFont(font);
				confirmButton.setTextFill(Color.DARKSLATEGREY);
				confirmButton.setOnAction(e2 -> {
					String term = termTextField.getText();
					String definition = definitionTextArea.getText();
					Notecard notecard = new Notecard(term, definition);
					notecards.add(notecard);

					notecardsTableView.setItems(notecards);
					termTableColumn.setCellValueFactory(new PropertyValueFactory<>("term"));
					definitionTableColumn.setCellValueFactory(new PropertyValueFactory<>("definition"));

					notecardStage.close();
				});

				Button cancelButton = new Button("Cancel");
				cancelButton.setFont(font);
				cancelButton.setTextFill(Color.DARKSLATEGREY);
				cancelButton.setOnAction(e2 -> notecardStage.close());

				VBox notecardPane = new VBox(15, notecardLabel, termLabel, termTextField, definitionLabel,
						definitionTextArea, confirmButton, cancelButton);
				notecardPane.setPadding(new Insets(75, 100, 75, 100));
				notecardPane.setAlignment(Pos.CENTER);

				notecardStage.setTitle("Create Notecard");
				notecardStage.setScene(new Scene(new Group(notecardPane), Color.ALICEBLUE));
				notecardStage.show();
			});

			Button doneButton = new Button("Done");
			doneButton.setFont(font);
			doneButton.setTextFill(Color.DARKSLATEGREY);
			doneButton.setOnAction(e -> stage.close());

			VBox pane = new VBox(15, createNotecardLabel, addButton, doneButton);
			pane.setPadding(new Insets(75, 100, 75, 100));
			pane.setAlignment(Pos.CENTER);

			stage.setScene(new Scene(new Group(pane), Color.ALICEBLUE));
			stage.show();
		} catch (Exception exception) {
			System.err.println(exception.getMessage());
		}
	}

	/**
	 * Displays single notecard at a time and allows user to navigate them. Method
	 * takes a stage and a table view of notecards as parameters, and displays new
	 * stage with current notecard and navigation functionality
	 */
	@FXML
	private void viewNotecard(ActionEvent event) {
		ObservableList<Notecard> notecards = notecardsTableView.getItems();

		if (notecards.isEmpty()) {
			return;
		}

		try {
			Label createNotecardLabel = new Label("Notecard");
			Label termLabel = new Label();
			Label definitionLabel = new Label();
			Font font = Font.font("Cambria", 18);
			createNotecardLabel.setFont(Font.font("Cambria", FontWeight.BOLD, 24));
			createNotecardLabel.setTextFill(Color.DARKSLATEGREY);
			termLabel.setFont(font);
			termLabel.setTextFill(Color.DARKSLATEGREY);
			definitionLabel.setFont(font);
			definitionLabel.setTextFill(Color.DARKSLATEGREY);

			ListIterator<Notecard> iterator = notecards.listIterator();
			Button backButton = new Button("Back");
			backButton.setOnAction(e -> {
				if (iterator.hasPrevious()) {
					Notecard currentNotecard = iterator.previous();
					termLabel.setText("Term: " + currentNotecard.getTerm());
					definitionLabel.setText("Definition: " + currentNotecard.getDefinition());
				}
			});
			Button nextButton = new Button("Next");
			nextButton.setOnAction(e -> {
				if (iterator.hasNext()) {
					Notecard currentNotecard = iterator.next();
					termLabel.setText("Term: " + currentNotecard.getTerm());
					definitionLabel.setText("Definition: " + currentNotecard.getDefinition());
				}
			});
			Button closeButton = new Button("Close");
			closeButton.setOnAction(e -> ((Stage) closeButton.getScene().getWindow()).close());

			HBox buttonsPane = new HBox(20);
			buttonsPane.getChildren().addAll(backButton, nextButton, closeButton);
			buttonsPane.setAlignment(Pos.CENTER);

			VBox mainPane = new VBox(20);
			mainPane.getChildren().addAll(createNotecardLabel, termLabel, definitionLabel, buttonsPane);
			mainPane.setPadding(new Insets(75, 100, 75, 100));
			mainPane.setAlignment(Pos.CENTER);

			Scene scene = new Scene(mainPane, Color.ALICEBLUE);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("View Notecards");
			stage.show();
		} catch (Exception exception) {
			System.err.println(exception.getMessage());
		}
	}

	/**
	 * Opens dialog to edit selected notecard in the TableView. Dialog contains a
	 * form to edit term/definition of selected notecard. Changes made to notecard
	 * are reflected in the table view after confirmation.
	 * 
	 * @param notecardsTableView    TableView containing the notecards
	 * @param termTableColumn       TableColumn representing term of notecard
	 * @param definitionTableColumn TableColumn representing definition of notecard
	 */
	@FXML
	void editNotecard(ActionEvent event) {
		Notecard selectedNotecard = notecardsTableView.getSelectionModel().getSelectedItem();

		if (selectedNotecard == null) {
			return;
		}

		try {
			Label createNotecardLabel = new Label("Edit Notecard");
			Label termLabel = new Label("Term:");
			TextField termTextField = new TextField(selectedNotecard.getTerm());
			Label definitionLabel = new Label("Definition:");
			TextArea definitionTextArea = new TextArea(selectedNotecard.getDefinition());
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

			VBox pane = new VBox(15, createNotecardLabel, termLabel, termTextField, definitionLabel, definitionTextArea,
					confirmButton, cancelButton);
			pane.setPadding(new Insets(75, 100, 75, 100));
			pane.setAlignment(Pos.CENTER);

			Scene scene = new Scene(new Group(pane), Color.ALICEBLUE);
			Stage stage = new Stage();
			stage.setTitle("Edit Notecard");
			stage.setScene(scene);

			confirmButton.setOnAction(e -> {
				selectedNotecard.setTerm(termTextField.getText());
				selectedNotecard.setDefinition(definitionTextArea.getText());
				notecardsTableView.refresh();
				stage.close();
			});
			cancelButton.setOnAction(e -> stage.close());
			stage.show();
		} catch (Exception exception) {
			System.err.println(exception.getMessage());
		}
	}

	/**
	 * Deletes a selected notecard from a TableView of notecards if the user
	 * confirms deletion from within dialog.
	 * 
	 * @param notecardsTableView TableView of notecards to delete from
	 */
	@FXML
	void deleteNotecard(ActionEvent event) {
		Notecard selectedNotecard = notecardsTableView.getSelectionModel().getSelectedItem();
		try {
			Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
			confirmationAlert.setTitle("Delete Notecard");
			confirmationAlert.setContentText("Are you sure you want to delete this notecard?\n\nTerm: "
					+ selectedNotecard.getTerm() + "\n\nDefinition:\n" + selectedNotecard.getDefinition());
			Optional<ButtonType> result = confirmationAlert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				notecardsTableView.getItems().remove(selectedNotecard);
			}
		} catch (Exception exception) {
			System.err.println(exception.getMessage());
		}
	}

	/**
	 * Notecard is added to the set and the fields are cleared. An information alert
	 * is displayed to confirm the addition.
	 */
	@FXML
	void addNotecardToSet(ActionEvent event) {
		// TO-DO: fix method when appropriate UI is attached and setup
		Notecard selectedNotecard = notecardsTableView.getSelectionModel().getSelectedItem();
		if (selectedNotecard != null) {
			try {
				Alert confirmationDialog = new Alert(AlertType.CONFIRMATION);
				confirmationDialog.setTitle("Add Notecard to Set");
				confirmationDialog.setHeaderText(null);
				confirmationDialog.setContentText("Are you sure you want to add this notecard to the set?");

				ButtonType addButton = new ButtonType("Add", ButtonData.YES);
				ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
				confirmationDialog.getButtonTypes().setAll(addButton, cancelButton);

				Optional<ButtonType> result = confirmationDialog.showAndWait();
				if (result.isPresent() && result.get() == addButton) {
					Notecards notecardSet = new Notecards();
					notecardSet.addNotecard(selectedNotecard);

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Notecard added");
					alert.setHeaderText(null);
					alert.setContentText("The notecard has been added to the set.");
					alert.showAndWait();
				}
			} catch (Exception exception) {
				System.err.println(exception.getMessage());
			}
		}
	}

	/**
	 * Searches the TableView of Notecards based on the text entered into the
	 * TextField search bar.
	 */
	@FXML
	void searchNotecards(ActionEvent event) {
		ObservableList<Notecard> notecards = notecardsTableView.getItems();
		FilteredList<Notecard> filteredNotecards = new FilteredList<>(notecards, predicate -> true);
		notecardsTableView.setItems(filteredNotecards);
		try {
			notecardsSearchBarTextField.textProperty().addListener((observable, oldValue, newValue) -> {
				String lowerCaseFilter = newValue.toLowerCase();
				filteredNotecards.setPredicate(notecard -> {
					if (newValue == null || newValue.isEmpty()) {
						return true;
					}
					String term = notecard.getTerm().toLowerCase();
					String definition = notecard.getDefinition().toLowerCase();
					return term.contains(lowerCaseFilter) || definition.contains(lowerCaseFilter);
				});
			});
		} catch (Exception exception) {
			System.err.println(exception.getMessage());
		}
	}
}
