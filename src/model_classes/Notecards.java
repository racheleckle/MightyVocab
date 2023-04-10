package model_classes;

import java.util.ArrayList;
import java.util.List;

public class Notecards {
	private List<Notecard> notecards;
	private String name;
	
	public Notecards() {
		this.notecards = new ArrayList<Notecard>();
		this.name = "Default";
	}

	/**
	 * Instantiates an instance of a collectible of notecard
	 */
	public Notecards(String name) {
		if (name == null) {
			throw new IllegalArgumentException("name cannot be null");
		}
		if (name.isEmpty()) {
			throw new IllegalArgumentException("name cannot be empty");
		}
		this.notecards = new ArrayList<Notecard>();
		this.name = name;
	}

	/**
	 * Get notecards
	 * 
	 * @return get the notecards
	 */
	public List<Notecard> getNotecards() {
		return this.notecards;
	}
	
	public void setNotecards(ArrayList<Notecard> notecards) {
		this.notecards = notecards;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the amount of notecards
	 * 
	 * @return amount of notecards
	 */
	public int getNumberOfNotecards() {
		return this.notecards.size();
	}

	/**
	 * Add a notecard
	 * 
	 * @param notecard
	 * @return true if successfully added, false otherwise
	 */
	public boolean addNotecard(Notecard notecard) {
		if (notecard == null) {
			return false;
		}
		return this.notecards.add(notecard);
	}

	/**
	 * Add all notecards
	 * 
	 * @param notecard
	 * @return true if successfully added, false otherwise
	 */
	public boolean addNotecards(List<Notecard> notecards) {
		if (notecards == null) {
			return false;
		}
		return this.notecards.addAll(notecards);
	}

	/**
	 * Remove an unwanted notecard
	 * 
	 * @param notecard
	 * @return true if successfully removed, false otherwise
	 */
	public boolean removeNotecard(Notecard unwantedNotecard) {
		if (unwantedNotecard == null) {
			return false;
		}
		return this.notecards.remove(unwantedNotecard);
	}

	/**
	 * Remove all unwanted notecards
	 * 
	 * @param unwantedNotecards
	 * @return true if removed, false otherwise
	 */
	public boolean removeNotecards(List<Notecard> unwantedNotecards) {
		if (unwantedNotecards == null) {
			return false;
		}
		return this.notecards.removeAll(unwantedNotecards);
	}

	/***
	 * Get the notecard by the index
	 * 
	 * @param notecardIndex
	 * @return notecard
	 */
	public Notecard getNotecardByIndex(int notecardIndex) {
		return this.notecards.get(notecardIndex);
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}