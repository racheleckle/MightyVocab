package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model_classes.Notecard;
import model_classes.Notecards;
import viewmodel.NotecardsViewModel;

public class IndividualNotecardCodeBehind {

	private Stage stage;
	private Scene scene;
	private Parent root;

	private NotecardsViewModel viewmodel;
	
	@FXML
	private Button addToSetButton;

	@FXML
	private Button cancelButton;

	@FXML
	private Label definitionLabel;

	@FXML
	private TextArea definitionTextArea;

	@FXML
	private Label termLabel;

	@FXML
	private TextField termTextField;
	
	public IndividualNotecardCodeBehind() {
		this.viewmodel = new NotecardsViewModel();
	}
	
	@FXML
	private void initialize() {
		this.bindToViewModel();
	}
	
	private void bindToViewModel() {
		this.termTextField.textProperty().bindBidirectional(this.viewmodel.termTextProperty());
		this.definitionTextArea.textProperty().bindBidirectional(this.viewmodel.definitionTextProperty());
	}

	@FXML
	void addToSet() {
		String term = termTextField.getText();
		String definition = this.definitionTextArea.getText();
		if (!term.isEmpty() && !definition.isEmpty()) {
			Notecard notecard = new Notecard(term, definition);
			Notecards selectedSet = new Notecards();
			selectedSet.addNotecard(notecard);
//			saveSetToDatabase(selectedSet);
//			try {
//				cancel(null);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
	}

	@FXML
	void cancel(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("ManageSetsPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

//	private Notecards getSelectedSet() {
//		// read the JSON file containing the sets
//		String json = null;
//		try {
//			json = new String(Files.readAllBytes(Paths.get("path/to/database.json")));
//		} catch (IOException e) {
//			e.printStackTrace();
//			return null;
//		}
// parse the JSON array into a List of Notecards objects
//	    Gson gson = new Gson();
//	    Type notecardsListType = new TypeToken<List<Notecards>>() {}.getType();
//	    List<Notecards> notecardsList = gson.fromJson(json, notecardsListType);
// find the selected set
//		Notecards selectedSet = null;
//	    int selectedSetId = ...;
//	    for (Notecards notecards : notecardsList) {
//	        if (notecards.getId() == selectedSetId) {
//	            selectedSet = notecards;
//	            break;
//	        }
//	    }
//		return selectedSet;
//	}

//	private void saveSetToDatabase(Notecards set) {
//		Notecards selectedSet = this.getSelectedSet();
//
//		// merge the new set with the existing one
//		if (selectedSet != null) {
//			selectedSet.merge(set);
//		} else {
//			selectedSet = set;
//		}
//
//		// serialize the merged set to JSON
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		String json = gson.toJson(selectedSet);
//
//		// save the JSON to a file (replace "path/to/database.json" with the actual file
//		// path)
//		try (FileWriter writer = new FileWriter("path/to/database.json")) {
//			writer.write(json);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}