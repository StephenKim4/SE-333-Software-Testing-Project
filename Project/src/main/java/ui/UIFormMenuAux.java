package ui;

abstract class UIFormMenuAux{
	String _heading;
	GenericPair<UIFormMenuInterface>[] _form;
	
	UIFormMenuAux(String head, GenericPair<UIFormMenuInterface>[] form) {
		_heading = head;
		_form = form;
	}


	/**
	 * Size of the form
	 * 
	 * @return int size of form
	 */
	public int size() {
		return _form.length;
	}

	/**
	 * Get the heading
	 * 
	 * @return String heading
	 */
	public String getHeading() {
		return _heading;
	}

	/**
	 * Get the prompt
	 * 
	 * @param i int
	 * @return String form prompt
	 */
	public String getPrompt(int i) {
		return _form[i].prompt;
	}

}
