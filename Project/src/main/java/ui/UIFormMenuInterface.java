/**
 * 
 */
package ui;

/**
 * Interface for UI forms and UI menus
 * 
 * @author Stephen Kim
 *
 */
public interface UIFormMenuInterface {

	/**
	   * Size of the form or menu
	   * 
	   * @return int size of form
	   */
	int size();
	
	/**
	   * Get the heading
	   * 
	   * @return String heading
	   */
	String getHeading();
	
	/**
	   * Get the prompt
	   * 
	   * @param i int
	   * @return String form prompt
	   */	
	String getPrompt(int i);
	
	/**
	   * Check the input
	   * 
	   * @param i int 
	   * @param input string input
	   * @return input
	   */
	boolean checkInput(int i, String input);
	
	 /**
	   * Run the menu item
	   * 
	   * @param i int for menu item
	   */
	 void runAction(int i);
	
}
