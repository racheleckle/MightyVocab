package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model_classes.Notecards;
import viewmodel.SetsViewModel;

public class SetsPageCodeBehind {

	private SetsViewModel viewmodel;
	
	@FXML
	private Button addSetsButton;

	@FXML
	private Button editCardsButton;

	@FXML
	private TextField setNameTextfield;

	@FXML
	private ChoiceBox<Notecards> setsChoiceBox;

	@FXML
	private ComboBox<Notecards> setsComboBox;

	@FXML
	private Button studyButton;
	
	public SetsPageCodeBehind() {
		this.viewmodel = new SetsViewModel();
	}
	
	@FXML
	private void initialize() {
		this.bindComponentsToViewModel();
	}
	
	private void bindComponentsToViewModel() {
		this.setNameTextfield.textProperty().bindBidirectional(this.viewmodel.setsNameProperty());
		//this.setsChoiceBox.itemsProperty().bind(this.viewmodel.setsListProperty());
		this.setsComboBox.itemsProperty().bind(this.viewmodel.setsListProperty());
	}

	@FXML
	void addSetOnClick(ActionEvent event) {
		this.viewmodel.addNotecards();
		// Reset fields in viewmodel
	}

	@FXML
	void editCardsInSetOnAction(ActionEvent event) {
		
	}

	@FXML
	void goStudySet(ActionEvent event) {
		// Go to Study page
	}

}
