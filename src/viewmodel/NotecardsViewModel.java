package viewmodel;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model_classes.Notecard;
import model_classes.Notecards;

public class NotecardsViewModel {

	private ObservableList<Notecard> notecardsList;
	private TableView<Notecard> notecardsTableView;
	private TableColumn<Notecard, String> termTableColumn;
	private TableColumn<Notecard, String> definitionTableColumn;
	private StringProperty notecardsSearchTextField;
	private Label createNotecardLabel;
	private Button addButton;
	private Button doneButton;
	
	private Notecards notecards;
	
	private StringProperty termTextProperty;
	private StringProperty definitionTextProperty;
	
	private StringProperty searchTextProperty;
	
	private ListProperty<Notecard> notecardsProperty;
	
	

	public NotecardsViewModel() {
		
		this.termTextProperty = new SimpleStringProperty();
		this.definitionTextProperty = new SimpleStringProperty();
		this.notecardsProperty = new SimpleListProperty<Notecard>();
		this.searchTextProperty = new SimpleStringProperty();
		
		this.notecards = new Notecards();
		
		
		this.setNotecards(notecardsList);
		this.setNotecardsTableView(notecardsTableView);
		this.setTermTableColumn(termTableColumn);
		this.setDefinitionTableColumn(definitionTableColumn);
		this.setCreateNotecardLabel(createNotecardLabel);
		this.setNotecardsSearchTextField(notecardsSearchTextField);
		//this.setAddButton(addButton);
		//this.setDoneButton(doneButton);
	}
	
	public StringProperty definitionTextProperty() {
		return this.definitionTextProperty;
	}
	
	public StringProperty termTextProperty() {
		return this.termTextProperty;
	}
	
	public ListProperty<Notecard> notecardsProperty(){
		return this.notecardsProperty;
	}

	public ObservableList<Notecard> getNotecardsList() {
		return this.notecardsList;
	}
	
	public StringProperty searchTextProperty() {
		return this.searchTextProperty;
	}

	public void setNotecards(ObservableList<Notecard> notecards) {
		this.notecardsList = notecards;
	}
	
	public Notecards getNotecards() {
		return this.notecards;
	}

	public TableView<Notecard> getNotecardsTableView() {
		return notecardsTableView;
	}

	public void setNotecardsTableView(TableView<Notecard> notecardsTableView) {
		this.notecardsTableView = notecardsTableView;
	}

	public TableColumn<Notecard, String> getTermTableColumn() {
		return termTableColumn;
	}

	public void setTermTableColumn(TableColumn<Notecard, String> termTableColumn) {
		this.termTableColumn = termTableColumn;
	}

	public TableColumn<Notecard, String> getDefinitionTableColumn() {
		return definitionTableColumn;
	}

	public void setDefinitionTableColumn(TableColumn<Notecard, String> definitionTableColumn) {
		this.definitionTableColumn = definitionTableColumn;
	}

	public Label getCreateNotecardLabel() {
		return createNotecardLabel;
	}

	public void setCreateNotecardLabel(Label createNotecardLabel) {
		this.createNotecardLabel = createNotecardLabel;
	}

	public Button getAddButton() {
		return addButton;
	}

	public void setAddButton(Button addButton) {
		this.addButton = addButton;
	}

	public Button getDoneButton() {
		return doneButton;
	}

	public void setDoneButton(Button doneButton) {
		this.doneButton = doneButton;
	}
	
	public StringProperty getNotecardsSearchTextField() {
		return notecardsSearchTextField;
	}

	public void setNotecardsSearchTextField(StringProperty notecardsSearchTextField) {
		this.notecardsSearchTextField = notecardsSearchTextField;
	}

	public void createNotecard() {
		
	}

	public void viewNotecard() {

	}

	public void editNotecard() {

	}

	public void deleteNotecard() {

	}

	public void addNotecardToSet() {
		String term = this.termTextProperty.getValue();
		String definition = this.definitionTextProperty.getValue();
		
		if (!term.isEmpty() && !definition.isEmpty()) {
			Notecard notecard = new Notecard(term, definition);
			this.notecards.addNotecard(notecard);
		}
		
		this.notecardsProperty.set(FXCollections.observableArrayList(this.notecards.getNotecards()));
		
	}

	public void searchNotecards() {
		ObservableList<Notecard> notecards = notecardsTableView.getItems();
		FilteredList<Notecard> filteredNotecards = new FilteredList<>(notecards, predicate -> true);
		notecardsTableView.setItems(filteredNotecards);
		try {
			notecardsSearchTextField.addListener((observable, oldValue, newValue) -> {
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