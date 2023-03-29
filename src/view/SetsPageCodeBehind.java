package view;

import java.io.IOException;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
