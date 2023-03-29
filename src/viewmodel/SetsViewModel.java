package viewmodel;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import model_classes.Notecard;
import model_classes.Notecards;
/**
 * View Model class for Sets
 * 
 * @author Michael Lee
 * @version Spring 2023
 */
public class SetsViewModel {
	private StringProperty setsNameProperty;
	private ObjectProperty<Notecards> selectedSet;
	private ListProperty<Notecards> setsListProperty;
	
	private List<Notecards> sets;

	/**
	 * Instantiates a ViewModel
	 */
	public SetsViewModel() {
		this.setsNameProperty = new SimpleStringProperty();
		this.setsListProperty = new SimpleListProperty<Notecards>();
		this.selectedSet = new SimpleObjectProperty<Notecards>();
		
		this.sets = new ArrayList<Notecards>();
	}

	public StringProperty setsNameProperty() {
		return this.setsNameProperty;
	}

	public ObjectProperty<Notecards> selectedSetProperty() {
		return this.selectedSet;
	}

	public ListProperty<Notecards> setsListProperty() {
		return this.setsListProperty;
	}
	
	public List<Notecards> getSets(){
		return this.sets;
	}
	
	public void addNotecards() {
		String name = this.setsNameProperty.get();
		
		if (!name.isEmpty()) {
			Notecards cards = new Notecards(name);
			cards.addNotecard(new Notecard("Hello", "world"));
			
			this.sets.add(cards);
			
		}
		
		this.setsListProperty.set(FXCollections.observableArrayList(this.sets));
	}
	
	public void editNotecards() {
		
	}

}
