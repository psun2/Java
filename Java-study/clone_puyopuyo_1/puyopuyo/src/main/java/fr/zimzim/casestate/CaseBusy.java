/**
 * This package contains all classes to define and represent a game map-case state
 */
package fr.zimzim.casestate;

/**
 * 
 * @author Simon Jambu
 * State instance of a map case. Means that the current case holds something (a game item)
 * @see GameItem
 */
public class CaseBusy implements CaseState {
	
	/**
	 * Static instance
	 */
	private static final CaseState instance = new CaseBusy();
	
	/**
	 * Returns static class instance
	 * @return class instance
	 */
	public static CaseState getInstance() {
		return instance;
	}

}
