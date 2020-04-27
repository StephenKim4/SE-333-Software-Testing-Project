/**
 * 
 */
package ui;

/**
 * Form and Menu class
 * 
 * @see UIFormMenuBuilder
 * @author Stephen Kim
 *
 */
final class UIFormMenu extends UIFormMenuAux implements UIFactoryInterface, UIFormMenuInterface {
	  //private final String _heading;
	  private final GenericPair<UIFormMenuInterface>[] _form;
	  
	  UIFormMenu(String heading, GenericPair<UIFormMenuInterface>[] menu) {
	    super(heading, menu);
	    _form = menu;
	  }
	  
	  /**
	   * Check the input
	   * 
	   * @param i int 
	   * @param input string input
	   * @return input
	   */
	  public boolean checkInput(int i, String input) {
	    if (null == _form[i])
	      return true;
	    return _form[i].test.run(input);
	    
	  }
	  
	  /**
	   * Run the menu item
	   * 
	   * @param i int for menu item
	   */
	  public void runAction(int i) {
	    _form[i].action.run();
	  }
	}
