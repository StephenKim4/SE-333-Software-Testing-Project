package data;

import command.UndoableCommand;
import java.util.Map;

/**
 * Implementation of command to clear the inventory.
 * @see Data
 */
final class CmdClear implements UndoableCommand {
  private InventorySet _inventory;
  private Map _oldvalue;
  CmdClear(InventorySet inventory) {
    _inventory = inventory;
  }
  
  /**
   * Do the command.
   * @return true if command succeeds, false otherwise
   */
  public boolean run() {
    if (_oldvalue != null)
      return false;
    try {
    	
      _oldvalue = _inventory.clear();
      _inventory.getHistory().add(this);
      return true;
    } catch (ClassCastException e) {
      return false;
    }
  }
  
  /**
   * Undo the command.
   */
  public void undo() {
    _inventory.replaceMap(_oldvalue);
  }
  
  /**
   * Redo the command.
   */
  public void redo() {
    _inventory.clear();
  }

  //Equals Method
  public boolean equals(Object thatObject) {

    //If object is null return false
    if (thatObject == null)
      return false;

    //If object is not instance of cmdclear return false
    if (!(thatObject instanceof CmdClear)) return false;

    //If object is the same then true
    if (thatObject == this) return true;

    //Create instance of object
    CmdClear v = (CmdClear) thatObject;

    //Check equality of parameters if video not in hashmap
    if (this._inventory.equals(v._inventory)) {
      return true;
    }
    return false;
  }
}
