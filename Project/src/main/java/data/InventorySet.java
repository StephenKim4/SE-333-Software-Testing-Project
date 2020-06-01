package data;

import command.CommandHistory;
import command.CommandHistoryFactory;

import java.util.*;

/**
 * Implementation of Inventory interface.
 * @see Data
 */
public class InventorySet implements Inventory {
  private Map<Video,Record> _data;
  private final CommandHistory _history;

  InventorySet() {
      
    _data = new HashMap<Video, Record>();
    _history = CommandHistoryFactory.newCommandHistory();
     
  }

  /**
   * If <code>record</code> is null, then delete record for <code>video</code>;
   * otherwise replace record for <code>video</code>.
   * 
   * @param video     video 
   * @param record    record
   */
  void replaceEntry(Video video, Record record) {
    _data.remove(video);
    if (record != null)
      _data.put(video,((RecordObj)record).copy());
  }

  /**
   * Overwrite the map.
   * 
   * @param data inventory map
   */
  void replaceMap(Map<Video,Record> data) {
    _data = data;
  }

  public int size() {
    return _data.size();
  }

  public Record get(Video v) {
    return _data.get(v);
  }

  public Iterator<Record> iterator() {
    return Collections.unmodifiableCollection(_data.values()).iterator();
  }

  public Iterator<Record> iterator(Comparator<Record> comparator) {
    
	  List<Record> l = new ArrayList<Record>(_data.values());
	  l.sort(comparator);
	  //Collections.sort(l, comparator);
	  
	  return Collections.unmodifiableCollection(l).iterator();
  }

  /**
   * Add or remove copies of a video from the inventory.
   * If a video record is not already present (and change is
   * positive), a record is created. 
   * If a record is already present, <code>numOwned</code> is
   * modified using <code>change</code>.
   * If <code>change</code> brings the number of copies to be less
   * than one, the record is removed from the inventory.
   * @param video the video to be added.
   * @param change the number of copies to add (or remove if negative).
   * @return A copy of the previous record for this video (if any)
   * @throws IllegalArgumentException if video null or change is zero
   */
  Record addNumOwned(Video video, int change) {
    if (video == null)
      throw new NullPointerException();
    if (change == 0)
      throw new IllegalArgumentException();
    RecordObj r = (RecordObj) _data.get(video);
    if (r == null && change < 1) {
      throw new IllegalArgumentException();
    } else if (r == null) {
      _data.put(video, new RecordObj(video, change, 0, 0));
    } else if (r.numOwned+change < r.numOut) {
      throw new IllegalArgumentException();
    } else if (r.numOwned+change < 1) {
      _data.remove(video);
    } else {
      _data.put(video, new RecordObj(video, r.numOwned + change, r.numOut, r.numRentals));
    }
    return r;
  }

  /**
   * Check out a video.
   * @param video the video to be checked out.
   * @return A copy of the previous record for this video
   * @throws IllegalArgumentException if video has no record or numOut
   * equals numOwned.
   */
  Record checkOut(Video video) {
    
	  if (!_data.containsKey(video)) {
	    	throw new IllegalArgumentException("Video has no record");
	    }
	    if (_data.get(video).numOut() == _data.get(video).numOwned() ) {
	    	throw new IllegalArgumentException("All videos are checked out");
	    }
	Record c = new RecordObj(video, _data.get(video).numOwned(), _data.get(video).numOut(), _data.get(video).numRentals());    
  	Record r = new RecordObj(video, _data.get(video).numOwned(), _data.get(video).numOut() + 1, _data.get(video).numRentals() + 1);
  	_data.replace(video, r);
	
  	return c;
  }
  
  /**
   * Check in a video.
   * @param video the video to be checked in.
   * @return A copy of the previous record for this video
   * @throws IllegalArgumentException if video has no record or numOut
   * non-positive.
   */
  Record checkIn(Video video) {
	  
	  if (!_data.containsKey(video)) {
		  	throw new IllegalArgumentException("Video has no record");
		  }
		  if (_data.get(video).numOut() < 1 ) {
		   	throw new IllegalArgumentException("There are no videos checked out");
		  }
		  
      Record c = new RecordObj(video, _data.get(video).numOwned(), _data.get(video).numOut(), _data.get(video).numRentals());    
	  Record r = new RecordObj(video, _data.get(video).numOwned(), _data.get(video).numOut() - 1, _data.get(video).numRentals());
	  _data.replace(video, r);
	  
	  return c;
  }
  
  /**
   * Remove all records from the inventory.
   * @return A copy of the previous inventory as a Map
   */
  Map clear() {
	  
	Map<Video, Record> newMap = new HashMap<Video, Record>();
	
	newMap.putAll(_data);
	
    _data.clear();
    
	return newMap;
  }

  /**
   * Return a reference to the history.
   * 
   * @return CommandHistory history
   */
  CommandHistory getHistory() {
     
    return _history;
  }
  
  public String toString() {
    StringBuffer buffer = new StringBuffer();
    buffer.append("Database:\n");
    Iterator i = _data.values().iterator();
    while (i.hasNext()) {
      buffer.append("  ");
      buffer.append(i.next());
      buffer.append("\n");
    }
    return buffer.toString();
  }


  /**
   * Implementation of Record interface.
   *
   * <p>This is a utility class for Inventory.  Fields are mutable and
   * package-private.</p>
   *
   * <p><b>Class Invariant:</b> No two instances may reference the same Video.</p>
   *
   * @see Record
   */
  static class RecordObj implements Record {
    Video video; // the video
    int numOwned;   // copies owned
    int numOut;     // copies currently rented
    int numRentals; // total times video has been rented
    
    RecordObj(Video video, int numOwned, int numOut, int numRentals) {
      this.video = video;
      this.numOwned = numOwned;
      this.numOut = numOut;
      this.numRentals = numRentals;
    }

    RecordObj copy() {
      return new RecordObj(video, numOwned, numOut, numRentals);
    }

    public Video video() {
      return video;
    }

    public int numOwned() {
      return numOwned;
    }

    public int numOut() {
      return numOut;
    }

    public int numRentals() {
      return numRentals;
    }

    public boolean equals(Object thatObject) {
      if (thatObject == null) {
        return false;
      }
      return video.equals(((Record)thatObject).video());
    }


    public int hashCode() {
      return video.hashCode();
    }


    public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append(video);
      buffer.append(" [");
      buffer.append(numOwned);
      buffer.append(",");
      buffer.append(numOut);
      buffer.append(",");
      buffer.append(numRentals);
      buffer.append("]");
      return buffer.toString();
    }
  }
}
