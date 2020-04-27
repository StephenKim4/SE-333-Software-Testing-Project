package main;

import ui.UIFormTest;


/**
 * FormTest enums
 * 
 * @author Stephen Kim
 */
public enum FormTest {
	
	YEARTEST(startTest -> {
		try {
			int i = Integer.parseInt(startTest);
			return i > 1800 && i < 5000;
		} catch (NumberFormatException e) {
			return false;
		}
	}),
	NUMBERTEST( startTest -> {
			try {
				Integer.parseInt(startTest);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}),
	STRINGTEST( startTest -> {
			return ! "".equals(startTest.trim());
		});
	
	private UIFormTest test;
	//Constuctor
	private FormTest(UIFormTest test) {
		this.test = test;
	}
	//Getter
	public UIFormTest getTest() {
		return test;
	}
	
}
