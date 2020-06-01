package data;

import command.UndoableCommand;

/**
 * Implementation of command to check out a video.
 * @see Data
 */
final class CmdOut implements UndoableCommand {
  private InventorySet _inventory;
  private Record _oldvalue;
  private Video _video;
  CmdOut(InventorySet inventory, Video video) {
    _inventory = inventory;
    _video = video;
  }
  
  /**
   * Do the command.
   * @return true if command succeeds, false otherwise
   */
  public boolean run() {
	  if (_oldvalue != null)
	      return false;
	    try {
	      _oldvalue = _inventory.checkOut(_video);
	      _inventory.getHistory().add(this);
	      return true;
	    } catch (IllegalArgumentException e) {
	      return false;
	    } catch (ClassCastException e) {
	      return false;
	    }
  }
  
  /**
   * Undo the command.
   */
  public void undo() {
    
	  _inventory.replaceEntry(_video, _oldvalue);
	  
  }
  
  /**
   * Redo the command.
   */
  public void redo() {
    
	  _inventory.checkOut(_video);
	  
  }

    //Equals Method
    public boolean equals(Object thatObject) {

        //If object is null return false
        if (thatObject == null)
            return false;

        //If object is not instance of cmdout return false
        if (!(thatObject instanceof CmdOut)) return false;

        //If object is the same then true
        if (thatObject == this) return true;

        //Create instance of object
        CmdOut v = (CmdOut) thatObject;

        //Check equality of parameters if video not in hashmap
        if (this._inventory.equals(v._inventory) && this._video == v._video) {
            return true;
        }
        return false;
    }
}
