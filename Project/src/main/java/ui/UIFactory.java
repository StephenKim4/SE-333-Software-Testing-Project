package ui;

/**
 * Interface factory
 * 
 * @author Stephen Kim
 *
 */
public class UIFactory {
  
	/**
	 * 
	 * Function to choose which interface to implement
	 * 
	 * @param i String
	 * @param head String
	 * @param t GenericPair
	 * 
	 * @return UIFactory Interface
	 */
	public UIFactoryInterface start(String i, String head, GenericPair[] t) {
		
		switch(i) {
		case UIFactoryInterface.popup:
			return new PopupUI();
			
		case UIFactoryInterface.textui:
			return new TextUI();
			
		case UIFactoryInterface.UIFormMenuBuilder:
			return new UIFormMenuBuilder();
			
		case UIFactoryInterface.UIFormMenu:
			return new UIFormMenu(head, t);
			
		default:
			return null;
		}
		
	}
	
}
