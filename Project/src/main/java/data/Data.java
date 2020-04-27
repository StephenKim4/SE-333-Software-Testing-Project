package data;

import java.util.HashMap;
import java.util.WeakHashMap;

import command.RerunnableCommand;
import command.UndoableCommand;

/**
 * A static class for accessing data objects.
 */
public class Data {
	
  private Data() {}
  
  
  /**
   * Returns a new Inventory.
   * 
   * @return InventorySet new inventory set
   */
  static public final Inventory newInventory() {
    return new InventorySet();
  }
  
  //Create bag for to store all newVideos created
  final public static HashMap<Video, Video> bag = new HashMap<Video, Video>();
  

  /**
   * Factory method for Video objects.
   * Title and director are "trimmed" to remove leading and final space.
   * @throws IllegalArgumentException if Video invariant violated.
   * @param title       title
   * @param year          year
   * @param director     director
   * 
   * @return Video       video
   */
  static public Video newVideo(String title, int year, String director) {
    
	//Check Invariants
	if (title == null || title == "" || title == " ")
			throw new IllegalArgumentException("Title must be non-null, have no leading or "
					+ "final spaces, and not be an empty string");
	if (director == null || director == "" || director == " ")
			throw new IllegalArgumentException("Director must be non-null, have no leading or "
					+ "final spaces, and not be an empty string");
	if (year <= 1800 || year >= 5000)
		throw new IllegalArgumentException("Year must be greater than 1800 or less than 5000");
	  
	//Create video object with trimmed parameters
	int y = Integer.parseInt(String.valueOf(year).trim());
	Video v = new VideoObj(title.trim(), y, director.trim());
	
	//Add video to bag
	bag.put(v, v);
	
	return v;
  }

  /**
   * Returns a command to add or remove copies of a video from the inventory.
   * <p>The returned command has the following behavior:</p>
   * <ul>
   * <li>If a video record is not already present (and change is
   * positive), a record is created.</li>
   * <li>If a record is already present, <code>numOwned</code> is
   * modified using <code>change</code>.</li>
   * <li>If <code>change</code> brings the number of copies to be less
   * than one, the record is removed from the inventory.</li>
   * </ul>
   * @param inventory inventory
   * @param video the video to be added.
   * @param change the number of copies to add (or remove if negative).
   * @throws IllegalArgumentException if inventory not created by a call to newInventory.
   * @return CmdAdd new inventory set
   */
  static public UndoableCommand newAddCmd(Inventory inventory, Video video, int change) {
    if (!(inventory instanceof InventorySet))
      throw new IllegalArgumentException();
    return new CmdAdd((InventorySet) inventory, video, change);
  }

  /**
   * Returns a command to check out a video.
   * @param inventory inventory
   * @param video the video to be checked out.
   * @return CmdOut new inventory set
   */
  static public UndoableCommand newOutCmd(Inventory inventory, Video video) {
	  if (!(inventory instanceof InventorySet))
	      throw new IllegalArgumentException();
	    return new CmdOut((InventorySet) inventory, video);
  }
  
  /**
   * Returns a command to check in a video.
   * @param inventory inventory
   * @param video the video to be checked in.
   * @return CmdIn new inventory set
   */
  static public UndoableCommand newInCmd(Inventory inventory, Video video) {
	  if (!(inventory instanceof InventorySet))
	      throw new IllegalArgumentException();
	  return new CmdIn((InventorySet) inventory, video);
  }
  
  /**
   * Returns a command to remove all records from the inventory.
   * @param inventory inventory
   * @return CmdClear new inventory set
   */
  static public UndoableCommand newClearCmd(Inventory inventory) {
    if (!(inventory instanceof InventorySet))
      throw new IllegalArgumentException();
    return new CmdClear((InventorySet) inventory);
  }

  /**
   * Returns a command to undo that will undo the last successful UndoableCommand. 
   * @param inventory inventory
   * @return inventory new inventory set
   */
  static public RerunnableCommand newUndoCmd(Inventory inventory) {
	  if (!(inventory instanceof InventorySet))
	      throw new IllegalArgumentException();
	  return ((InventorySet)inventory).getHistory().getUndo();
  }

  /**
   * Returns a command to redo that last successfully undone command. 
   * 
   * @param inventory inventory
   * @return inventory new inventory set
   */
  static public RerunnableCommand newRedoCmd(Inventory inventory) {
	  if (!(inventory instanceof InventorySet))
	      throw new IllegalArgumentException();
	  return ((InventorySet)inventory).getHistory().getRedo();
  }
}  
