
package main;

import ui.UIFactory;

import java.util.Scanner;

import data.Data;
import ui.UI;

/**
 * Main class to run the user interface.
 * 
 * @author Stephen Kim
 *
 */
public class Main {
	private Main() {}
	public static void main(String[] args) {
		UIFactory ui = new UIFactory();

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter 1 for TextUI, 2 for PopUpUI, or another number if you dare:");
		int input = Integer.parseInt(scan.next());

		while (input != 1 && input != 2) {
			System.out.println("You've been rejected. No poonany for you tonight! Pick again!");
			input = Integer.parseInt(scan.next());
		}


		if (input == 1) {
			Control control = new Control(Data.newInventory(), (UI) ui.start("text", null, null));
			control.run();
		}
		if (input == 2) {
			Control control = new Control(Data.newInventory(), (UI) ui.start("popup", null, null));;
			control.run();
		}

		scan.close();

	}
}
