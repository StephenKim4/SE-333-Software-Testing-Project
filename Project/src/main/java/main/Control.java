package main;

import ui.UI;
import ui.UIFormMenuInterface;
import ui.UIMenuAction;
import ui.UIFormMenuBuilderInterface;
import ui.UIError;
import ui.UIFactory;
import ui.UIFormTest;
import data.Data;
import data.Inventory;
import data.Video;
import data.Record;
import command.Command;
import java.util.Iterator;
import java.util.Comparator;


/**
 * Control class to set up the user interface.
 * 
 * @author Stephen Kim
 */
class Control {


	static UIFormMenuInterface[] _menus;
	static int _state;

	static UIFormMenuInterface _getVideoForm;
	static UIFormTest _numberTest;
	static UIFormTest _stringTest;

	static Inventory _inventory;
	static UI _ui;
	static UIFactory uiFactory = new UIFactory();

	Control(Inventory inventory, UI ui) {
		_inventory = inventory;
		_ui = ui;

		_menus = new UIFormMenuInterface[State.NUMSTATES.getValue()];
		_state = State.START.getValue();
		addSTART(State.START.getValue());
		addEXIT(State.EXIT.getValue()
				);

		UIFormTest yearTest = FormTest.YEARTEST.getTest();
		_numberTest = FormTest.NUMBERTEST.getTest();
		_stringTest = FormTest.STRINGTEST.getTest();

		UIFormMenuBuilderInterface f = (UIFormMenuBuilderInterface) uiFactory.start("UIFMB", null, null);
		f.add("Title", _stringTest);
		f.add("Year", yearTest);
		f.add("Director", _stringTest);
		_getVideoForm = f.toUIFormMenu("Enter Video");
	}

	void run() {
		try {
			while (_state != State.EXITED.getValue()) {
				_ui.processMenu(_menus[_state]);
			}
		} catch (UIError e) {
			_ui.displayError("UI closed");
		}
	}

	private void addSTART(int stateNum) {
		UIFormMenuBuilderInterface m = (UIFormMenuBuilderInterface) uiFactory.start("UIFMB", null, null);;

		m.add("Default", MenuAction.DEFAULT.getAction());
		m.add("Add/Remove copies of a video", MenuAction.ADDREMOVE.getAction());
		m.add("Check in a video", MenuAction.CHECKIN.getAction());
		m.add("Check out a video", MenuAction.CHECKOUT.getAction());
		m.add("Print the inventory", MenuAction.PRINT.getAction());
		m.add("Clear the inventory", MenuAction.CLEAR.getAction());
		m.add("Undo", MenuAction.UNDO.getAction());
		m.add("Redo", MenuAction.REDO.getAction());
		m.add("Print top ten all time rentals in order", MenuAction.TOPTEN.getAction());
		m.add("Exit", MenuAction.EXIT.getAction());
		m.add("Initialize with bogus contents", MenuAction.BOGUSINIT.getAction());
		_menus[stateNum] = m.toUIFormMenu("Bob's Video");
	}
	
	private void addEXIT(int stateNum) {
		UIFormMenuBuilderInterface m = (UIFormMenuBuilderInterface) uiFactory.start("UIFMB", null, null);

		m.add("Default", new UIMenuAction() { public void run() {} });
		m.add("Yes", MenuAction.YES.getAction());
		m.add("No", MenuAction.NO.getAction());

		_menus[stateNum] = m.toUIFormMenu("Are you sure you want to exit?");
	}
}
