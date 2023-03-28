package viewmodel;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SetsViewModel {
	private StringProperty setsNameProperty;
	private ObjectProperty<?> selectedSet;
	private ListProperty<String> setsListProperty;
	
	public SetsViewModel() {
		this.setsNameProperty = new SimpleStringProperty();
		this.setsListProperty = new SimpleListProperty();
		this.selectedSet = new SimpleObjectProperty();
		
	}
	
	public StringProperty setsNameProperty() {
		return this.setsNameProperty;
	}
	
	public ObjectProperty<?> selectedSetProperty() {
		return this.selectedSet;
	}
	
	public ListProperty<String> setsListProperty(){
		return this.setsListProperty;
	}

}
