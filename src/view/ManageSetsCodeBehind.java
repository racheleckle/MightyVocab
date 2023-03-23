package view;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import model_classes.Notecard;
import model_classes.Notecards;

public class ManageSetsCodeBehind {

	private Stage stage;
	private Scene scene;
	private Parent root;
	private Notecards currentSet;
	private ObservableList<Notecard> cardsList;

	@FXML
	private Button addCardsButton;

	@FXML
	private Label cardsLabel;

	@FXML
	private ListView<Notecard> cardsListView;

	@FXML
	private Label invalidCredentialsLabel;

	@FXML
	private Button previousButton;

	@FXML
	private Button removeCardsButton;

	@FXML
	private Label setLabel;

	@FXML
	private Label setManagerLabel;

	@FXML
	private TextField setNameTextField;

	@FXML
	void addCards(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("IndividualNotecardPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void previous(ActionEvent event) throws IOException {
//		root = FXMLLoader.load(getClass().getResource("SetsPage.fxml"));
//		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//		scene = new Scene(root);
//		stage.setScene(scene);
//		stage.show();
	}

	@FXML
	void removeCards(ActionEvent event) {
		Notecard selectedCard = cardsListView.getSelectionModel().getSelectedItem();
		if (selectedCard != null) {
			currentSet.removeNotecard(selectedCard);
			cardsList.remove(selectedCard);
		}
	}

	@FXML
	void saveSet(ActionEvent event) {
		String setName = setNameTextField.getText();
		if (setName.isEmpty()) {
			invalidCredentialsLabel.setText("Please enter a name for the set.");
			return;
		}
//		currentSet.setSetName(setName);
//		DatabaseManager.saveSet(currentSet);
	}

	public void initialize() {
		currentSet = new Notecards();
		cardsList = FXCollections.observableArrayList(currentSet.getNotecards());
		cardsListView.setItems(cardsList);
		cardsListView.setCellFactory(new Callback<ListView<Notecard>, ListCell<Notecard>>() {
			@Override
			public ListCell<Notecard> call(ListView<Notecard> param) {
				return new ListCell<Notecard>() {
					@Override
					protected void updateItem(Notecard item, boolean empty) {
						super.updateItem(item, empty);
						if (item != null && !empty) {
							setText(item.getTerm() + " - " + item.getDefinition());
						} else {
							setText(null);
						}
					}
				};
			}
		});
	}
}
