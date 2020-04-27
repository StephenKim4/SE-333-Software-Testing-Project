package data;

import command.Command;
import java.util.Comparator;
import java.util.Iterator;

/**
 * A collection of Records.
 * Records can only be created and destroyed using the Inventory.
 * @see Data
 */
public interface Inventory extends Iterable<Record> {
	
  /**
   *  Return the number of Records.
   *  
   *  @return int size of inventory
   */
  public int size();

  /**
   *  Get the record for a given Video.
   *  
   *  @param v     video object
   *  @return record  video object
   */
  public Record get(Video v);

  /**
   *  Return an iterator over Records in the Inventory.
   *  <p>The iterator returns objects that implement the Record interface.</p>
   *  <p>The Records are unordered.</p>
   *  
   *  @return Iterator   iterator for inventory
   */
  public Iterator<Record> iterator();

  /**
   *  Return an iterator over the Inventory, sorted according the
   *  Comparator.
   *  <p>The iterator returns objects that implement the
   *  <code>Record</code> interface.</p>
   *  <p>The iteration order is determined by the comparator (least first).</p>
   *  <p>The comparator may assume that its arguments implement
   *  <code>Record</code>.</p>
   *  @param comparator determines the order of the records returned.
   *  
   *  @return Iterator    sorted iterator for inventory
   */
  public Iterator<Record> iterator(Comparator<Record> comparator);

  /**
   * Returns the inventory as a string; one record per line.
   */
  public String toString();
}
