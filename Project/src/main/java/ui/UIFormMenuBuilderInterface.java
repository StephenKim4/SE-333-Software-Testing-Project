/**
 * 
 */
package ui;

/**
 * Interface for UIFormBuilder and UIMenuBuilder
 * 
 * @author Stephen Kim
 *
 */
public interface UIFormMenuBuilderInterface {

	/**
	 * Return a new UIMenu object.
	 * 
	 * @param heading String
	 * @return UIMenu UIMenu
	 */
	//UIFormMenu toUIMenu(String heading);
	
	/**
	 * Return a new UIForm object.
	 * 
	 * @param heading String
	 * @return UIForm UIForm
	 */
	UIFormMenu toUIFormMenu(String heading);
	
	/**
	 * Add prompt to UIMenu
	 * 
	 * @param prompt Prompt
	 * @param action Action to be taken
	 */
	void add(String prompt, UIMenuAction action);
	
	/**
	   * Add prompt to UIForm
	   * 
	   * @param prompt Prompt
	   * @param test Test for form
	   */
	void add(String prompt, UIFormTest test);
	
}
