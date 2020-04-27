package ui;

import java.util.ArrayList;
import java.util.List;

/**
 * UI Menu Builder and UI Form Builder
 * 
 * @author Stephen Kim
 *
 */
final class UIFormMenuBuilder extends UIBuilderAux implements UIFactoryInterface, UIFormMenuBuilderInterface{
	//private final List<GenericPair> _menu;
	public UIFormMenuBuilder() {
		super();
		//_menu = new ArrayList<GenericPair>();

	}


	/**
	 * Add prompt to UIMenu
	 * 
	 * @param prompt Prompt
	 * @param action Action to be taken
	 */
	public void add(String prompt, UIMenuAction action) {
		if (null == action)
			throw new IllegalArgumentException();
		_menu.add(new GenericPair(prompt, action));
	}
	/**
	 * Add prompt to UIForm
	 * 
	 * @param prompt Prompt
	 * @param test Test for form
	 */
	public void add(String prompt, UIFormTest test) {
		_menu.add(new GenericPair(prompt, test));
	}
}
