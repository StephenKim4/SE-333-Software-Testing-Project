package main;

/**
 * State enums
 */
public enum State {
	EXITED(0),
	EXIT(1),
	START(2),
	NUMSTATES(10);

	private int value;
	//Constructor
	private State (int value) {
		this.value = value;
	}
	//Getter
	public int getValue() {
		return value;
	}
}
