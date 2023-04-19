package model_classes;

import java.util.Collection;
import java.util.HashMap;

/**
 * Account manager class
 * 
 * @author mlee16
 * @version Spring 2023
 */
public class AccountManager {
	private HashMap<User, Notecards> accounts;

	/**
	 * Constructor for Account Manager
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public AccountManager() {
		this.accounts = new HashMap<User, Notecards>(); 
	}

	/**
	 * Clears the hash map
	 */
	public void clear() {
		this.accounts.clear();
	}

	/**
	 * Gets the value
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param key key with which the specified value is associated with
	 * @return the value associated with the key
	 */
	public Notecards get(User key) {
		if (key == null) {
			throw new IllegalArgumentException(resources.ResourceMessages.KEY_NULL);
		}
		return this.accounts.get(key);
	}

	/**
	 * Checks if the map contains a mapping for the specified key
	 * 
	 * @precondition key != null
	 * @postcondition none
	 * 
	 * @param key key with which the specified value is associated with
	 * @return true if does contain key, false otherwise
	 */
	public boolean containsKey(User key) {
		if (key == null) {
			throw new IllegalArgumentException(resources.ResourceMessages.KEY_NULL);
		}
		return this.accounts.containsKey(key);
	}

	/**
	 * Checks to see if the map is empty
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return true if map contains no key-value mappings
	 */
	public boolean isEmpty() {
		return this.accounts.isEmpty();
	}

	/**
	 * Adds to the map
	 * 
	 * @precondition key != null && value != null
	 * @postcondition size() = size() @pre + 1
	 * 
	 * @param key   key with which the specified value is associated
	 * @param value value to be associated with specified key
	 * @return true if added, false otherwise
	 */
	public boolean add(User key, Notecards value) {
		if (key == null) {
			throw new IllegalArgumentException(resources.ResourceMessages.KEY_NULL);
		}
		if (value == null) {
			throw new IllegalArgumentException(resources.ResourceMessages.VALUE_NULL);
		}
		if (!this.accounts.containsKey(key)) {
			this.accounts.put(key, value);
			return true;
		}
		return false;
	}

	/**
	 * Updates
	 * 
	 * @precondition key != null && value != null
	 * @postcondition none
	 * 
	 * @param key   key with which the specified value is associated
	 * @param value value to be associated with specified key
	 */
	public void update(User key, Notecards value) {
		if (key == null) {
			throw new IllegalArgumentException(resources.ResourceMessages.KEY_NULL);
		}
		if (value == null) {
			throw new IllegalArgumentException(resources.ResourceMessages.VALUE_NULL);
		}
		this.accounts.replace(key, value);
	}

	/**
	 * Removes
	 * 
	 * @precondition key != null
	 * @postcondition none
	 * 
	 * @param key the key to be removed
	 * @return true if value is removed, false otherwise
	 */
	public boolean remove(User key) {
		if (key == null) {
			throw new IllegalArgumentException(resources.ResourceMessages.KEY_NULL);
		}
		if (this.accounts.containsKey(key)) {
			this.accounts.remove(key);
			return true;
		}
		return false;
	}

	/**
	 * The number of values in the map
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the number of values in the map
	 */
	public int size() {
		return this.accounts.size();
	}

	/**
	 * Gets a collection of values
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return a collection of values
	 */
	public Collection<Notecards> values() {
		return this.accounts.values();
	}

	/**
	 * Gets a collection of keys
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return a collection of keys
	 */
	public Collection<User> keys() {
		return this.accounts.keySet();
	}
}
