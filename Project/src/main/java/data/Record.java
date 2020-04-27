package data;

/**
 * <p>Public view of an inventory record.</p>
 *
 * <p>Records are mutable, but cannot be changed outside the package.</p>
 * 
 * <p>This interface should not be implemented outside the package.</p>
 * 
 * <p><code>equals</code> and <code>hashCode</code> delegate to the
 * underlying Video object.</p>
 * @see Data
 */
public interface Record {
  /**
   * Returns the video.
   * <p><b>Invariant:</b> <code>video() != null</code>.</p>
   * 
   * @return video      video
   */
  public Video video();
  /**
   * Returns the number of copies of the video that are in the inventory.
   * Invariant: numOwned() greater than 0.
   * 
   * @return int    number of copied owned
   */
  public int numOwned();
  /**
   * Returns the number of copies of the video that are currently checked out.
   * Invariant: numOut() less than or equals to numOwned().
   * 
   * @return int number of copies rented out
   */
  public int numOut();
  /**
   * Returns the total number of times this video has ever been checked out.
   * Invariant: numRentals() greater than or equal to numOut().
   * 
   * @return int  number of total rentals
   */
  public int numRentals();
  /**
   *  Return a string representation of the object in the following format:
   * "video [numOwned,numOut,numRentals]".
   * @return string  Record as a string
   */
  public String toString();
}
