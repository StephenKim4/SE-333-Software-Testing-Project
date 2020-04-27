/**
 * 
 */
package ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Repetitive code in builders
 * 
 * @author Stephen Kim
 *
 */
public abstract class UIBuilderAux {
	
	List<GenericPair> _menu;
	public UIBuilderAux() {
		_menu = new ArrayList<GenericPair>();
	}

	/**
	 * Return a new UIMenu object.
	 * 
	 * @param heading String
	 * @return UIMenu UIMenu
	 */
	/*
	public UIFormMenu toUIMenu(String heading) {
		if (null == heading)
			throw new IllegalArgumentException();
		if (_menu.size() <= 1)
			throw new IllegalStateException();
		GenericPair[] array = new GenericPair[_menu.size()];
		for (int i = 0; i < _menu.size(); i++)
			array[i] = (_menu.get(i));
		return new UIFormMenu(heading, array);
	}*/

	/**
	 * Get the UIForm or UIMenu to build UI
	 * 
	 * @param heading string for heading
	 * @return UIForm new UIForm
	 */
	public UIFormMenu toUIFormMenu(String heading) {
		if (null == heading)
			throw new IllegalArgumentException();
		if (_menu.size() < 1)
			throw new IllegalStateException();
		GenericPair[] array = new GenericPair[_menu.size()];
		for (int i = 0; i < _menu.size(); i++)
			array[i] = (_menu.get(i));
		return new UIFormMenu(heading, array);
	}

}
