package data;


import command.UndoableCommand;


/**
 * Implementation of command to add or remove inventory.
 * @see Data
 */
final class CmdAdd implements UndoableCommand {
  private boolean _runOnce;
  private InventorySet _inventory;
  private Record _oldvalue;
  private Video _video;
  private int _change;
  CmdAdd(InventorySet inventory, Video video, int change) {
    _inventory = inventory;
    _video = video;
    _change = change;
  }
  
  /**
   * Do the command.
   * @return true if command succeeds, false otherwise
   */
  public boolean run() {
    if (_runOnce)
      return false;
    _runOnce = true;
    try {
      _oldvalue = _inventory.addNumOwned(_video, _change);
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
    _inventory.replaceEntry(_video,_oldvalue);
  }
  
  /**
   * Redo the command.
   */
  public void redo() {
    _inventory.addNumOwned(_video, _change);
  }

  //Equals Method
  public boolean equals(Object thatObject) {

      //If object is null return false
      if (thatObject == null)
        return false;

      //If object is not instance of cmdadd return false
      if (!(thatObject instanceof CmdAdd)) return false;

      //If object is the same then true
      if (thatObject == this) return true;

      //Create instance of object
      CmdAdd v = (CmdAdd) thatObject;

      //Check equality of parameters if video not in hashmap
      if (this._inventory.equals(v._inventory) && this._video == v._video && this._change == (v._change)) {
        return true;
      }
      return false;
  }
}
