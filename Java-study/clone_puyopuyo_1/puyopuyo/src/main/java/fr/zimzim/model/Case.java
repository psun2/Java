/**
 * Contains the model of the game (all the game logic and needed data structures)
 */
package fr.zimzim.model;

import fr.zimzim.casestate.CaseEmpty;
import fr.zimzim.casestate.CaseState;
import fr.zimzim.meshe.GameItem;

/**
 * Represents an element of the game map. Holds a unique game item
 * @author Simon Jambu
 *
 */
public class Case {
	
	/**
	 * The GameItem held by the case
	 */
	private GameItem item;
	
	/**
	 * Current state of the case (can be Busy or Empty)
	 * @see CaseState
	 */
	private CaseState state;
	
	/**
	 * Current line of the case
	 */
	private int line;
	
	/**
	 * Current column of the case
	 */
	private int col;
	
	/**
	 * Case's constructor
	 * @param item: GameItem held
	 * @param line: Case's line on the game map
	 * @param col: Case's column on the game map
	 */
	public Case(GameItem item, int line, int col) {
		this.item = item;
		this.line = line;
		this.col = col;
		this.state = CaseEmpty.getInstance();
	}
	
	/**
	 * Returns the current state of this case
	 * @return state: Current state of the case
	 */
	public CaseState getState() {
		return state;
	}
	
	/**
	 * Assign a new state to the case
	 * @param state: The new state
	 */
	public void setState(CaseState state) {
		this.state = state;
	}
	
	/**
	 * Returns the GameItem held by the case
	 * @return item: The GameItem held
	 */
	public GameItem getItem() {
		return this.item;
	}
	
	/**
	 * Sets a new GameItem to the case
	 * @param item: The GameItem
	 */
	public void setItem(GameItem item) {
		this.item = item;
	}
	
	/**
	 * Returns current case's line
	 * @return line
	 */
	public int getLine() {
		return this.line;
	}
	
	/**
	 * Returns current case's column
	 * @return col
	 */
	public int getColumn() {
		return this.col;
	}

}
