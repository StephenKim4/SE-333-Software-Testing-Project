
package main;

import ui.UI;
import ui.UIFormMenuInterface;
import ui.UIMenuAction;
import ui.UIFormMenuBuilderInterface;
import ui.UIError;
import ui.UIFormTest;
import data.Data;
import data.Inventory;
import data.Video;
import data.Record;
import command.Command;
import java.util.Iterator;
import java.util.Comparator;

/**
 * MenuAction enums
 * 
 * @author Stephen Kim
 *
 */
public enum MenuAction {
	
	
	
	DEFAULT(new UIMenuAction() {
		public void run() {
			Control._ui.displayError("doh!");
		}
	}),
	ADDREMOVE(new UIMenuAction() {
		public void run() {
			String[] result1 = Control._ui.processForm(Control._getVideoForm);
			Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);

			UIFormMenuBuilderInterface f = (UIFormMenuBuilderInterface) Control.uiFactory.start("UIFMB", null, null);;
			f.add("Number of copies to add/remove", Control._numberTest);
			String[] result2 = Control._ui.processForm(f.toUIFormMenu(""));

			Command c = Data.newAddCmd(Control._inventory, v, Integer.parseInt(result2[0]));
			if (! c.run()) {
				Control._ui.displayError("Command failed");
			}
		}
	}),
	CHECKIN(new UIMenuAction() {
		public void run() {
			String[] result1 = Control._ui.processForm(Control._getVideoForm);
			Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);

			Command c = Data.newInCmd(Control._inventory, v);
			if (! c.run()) {
				Control._ui.displayError("Command failed");
			}
		}
	}),
	CHECKOUT(new UIMenuAction() {
		public void run() {
			String[] result1 = Control._ui.processForm(Control._getVideoForm);
			Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);

			Command c = Data.newOutCmd(Control._inventory, v);
			if (! c.run()) {
				Control._ui.displayError("Command failed");
			} 
		}
	}),
	PRINT(new UIMenuAction() {
		public void run() {
			Control._ui.displayMessage(Control._inventory.toString());
		}
	}),
	CLEAR(new UIMenuAction() {
		public void run() {
			if (!Data.newClearCmd(Control._inventory).run()) {
				Control._ui.displayError("Command failed");
			}
		}
	}),
	UNDO(new UIMenuAction() {
		public void run() {
			if (!Data.newUndoCmd(Control._inventory).run()) {
				Control._ui.displayError("Command failed");
			}
		}
	}),
	REDO(new UIMenuAction() {
		public void run() {
			if (!Data.newRedoCmd(Control._inventory).run()) {
				Control._ui.displayError("Command failed");
			}
		}
	}),
	TOPTEN(new UIMenuAction() {
		public void run() {
			if (Control._inventory.size() > 0)
			{
				Iterator<Record> it = Control._inventory.iterator(new Comparator<Record>()
				{

					public int compare(Record r1, Record r2)
					{
						return r2.numOut() - r1.numOut();
					}
				});

				StringBuilder b = new StringBuilder();
				int counter = 1;
				b.append("top ten all time rentals in order: \n");
				while (it.hasNext() && counter < 11)
				{
					Record r = it.next();
					b.append(" " + r.video().title() + " [" + r.numOwned() + "]\n");

					counter++;
				}
				Control._ui.displayMessage(b.toString());
			} else
			{
				Control._ui.displayError("Inventory is Empty!");
			}    
		}
	}),
	EXIT(new UIMenuAction() {
		public void run() {
			Control._state = State.EXIT.getValue();
		}
	}),
	BOGUSINIT(new UIMenuAction() {
		public void run() {
			Data.newAddCmd(Control._inventory, Data.newVideo("a", 2000, "m"), 1).run();
			Data.newAddCmd(Control._inventory, Data.newVideo("b", 2000, "m"), 2).run();
			Data.newAddCmd(Control._inventory, Data.newVideo("c", 2000, "m"), 3).run();
			Data.newAddCmd(Control._inventory, Data.newVideo("d", 2000, "m"), 4).run();
			Data.newAddCmd(Control._inventory, Data.newVideo("e", 2000, "m"), 5).run();
			Data.newAddCmd(Control._inventory, Data.newVideo("f", 2000, "m"), 6).run();
			Data.newAddCmd(Control._inventory, Data.newVideo("g", 2000, "m"), 7).run();
			Data.newAddCmd(Control._inventory, Data.newVideo("h", 2000, "m"), 8).run();
			Data.newAddCmd(Control._inventory, Data.newVideo("i", 2000, "m"), 9).run();
			Data.newAddCmd(Control._inventory, Data.newVideo("j", 2000, "m"), 10).run();
			Data.newAddCmd(Control._inventory, Data.newVideo("k", 2000, "m"), 11).run();
			Data.newAddCmd(Control._inventory, Data.newVideo("l", 2000, "m"), 12).run();
			Data.newAddCmd(Control._inventory, Data.newVideo("m", 2000, "m"), 13).run();
			Data.newAddCmd(Control._inventory, Data.newVideo("n", 2000, "m"), 14).run();
			Data.newAddCmd(Control._inventory, Data.newVideo("o", 2000, "m"), 15).run();
			Data.newAddCmd(Control._inventory, Data.newVideo("p", 2000, "m"), 16).run();
			Data.newAddCmd(Control._inventory, Data.newVideo("q", 2000, "m"), 17).run();
			Data.newAddCmd(Control._inventory, Data.newVideo("r", 2000, "m"), 18).run();
			Data.newAddCmd(Control._inventory, Data.newVideo("s", 2000, "m"), 19).run();
			Data.newAddCmd(Control._inventory, Data.newVideo("t", 2000, "m"), 20).run();
			Data.newAddCmd(Control._inventory, Data.newVideo("u", 2000, "m"), 21).run();
			Data.newAddCmd(Control._inventory, Data.newVideo("v", 2000, "m"), 22).run();
			Data.newAddCmd(Control._inventory, Data.newVideo("w", 2000, "m"), 23).run();
			Data.newAddCmd(Control._inventory, Data.newVideo("x", 2000, "m"), 24).run();
			Data.newAddCmd(Control._inventory, Data.newVideo("y", 2000, "m"), 25).run();
			Data.newAddCmd(Control._inventory, Data.newVideo("z", 2000, "m"), 26).run();
		}
	}),
	YES(new UIMenuAction() {
		public void run() {
			Control._state = State.EXITED.getValue();
		}
	}),
	NO(new UIMenuAction() {
		public void run() {
			Control._state = State.START.getValue();
		}
	});
	;
	private UIMenuAction action;
	//Constructor
	private MenuAction(UIMenuAction action) {
		this.action = action;
	}
	//Getter
	public UIMenuAction getAction() {
		return action;
	}
	
}
