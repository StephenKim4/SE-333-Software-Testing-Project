package ui;

/**
 * Generic Pair class
 * 
 * @author Stephen Kim
 *
 * @param <T> Either UIMenuAction or UIFormTest
 */
final class GenericPair<T> {
	String prompt;
	UIMenuAction action;
	UIFormTest test;
	
	/**
	 * Generic Pair
	 * 
	 * @param prompt String
	 * @param t either UIMenuAction or UIFormTest
	 */
	GenericPair(String prompt, T t){
		this.prompt = prompt;
		if(t instanceof UIMenuAction)
			action = (UIMenuAction) t;
		else if(t instanceof UIFormTest)
			test = (UIFormTest) t;
		else
			throw new IllegalArgumentException();
	}
	
}
