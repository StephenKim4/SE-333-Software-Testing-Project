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
}
