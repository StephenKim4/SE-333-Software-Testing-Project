package main;

import org.junit.jupiter.api.Assertions;
import junit.framework.Assert;
import junit.framework.TestCase;
import command.Command;
import data.Data;
import data.Record;
import data.Video;
import data.Inventory;
import java.util.Iterator;

// TODO:
// write an integration test that tests the data classes.
// add in some videos, check out, check in, delete videos, etc.
// check that errors are reported when necessary.
// check that things are going as expected.
public class TEST1 extends TestCase {
  private Inventory _inventory = Data.newInventory();
  public TEST1(String name) {
    super(name);
  }
  
  /**
   * Function to assertEquals the video string and the object in inventory string.
   * 
   * @param v       video
   * @param s       string
   */
  private void expect(Video v, String s) {
    assertEquals(s,_inventory.get(v).toString());
  }
  
  /**
   * 
   * 
   * @param r       record
   * @param s       string
   */
  private void expect(Record r, String s) {
    assertEquals(s,r.toString());
  }
  
  private void check(Video v, int numOwned, int numOut, int numRentals) {
    Record r = _inventory.get(v);
    assertEquals(numOwned, r.numOwned());
    assertEquals(numOut, r.numOut());
    assertEquals(numRentals, r.numRentals());
  }
    
  public void test1() {
    Command clearCmd = Data.newClearCmd(_inventory);
    clearCmd.run();
    
    Video v1 = Data.newVideo("Title1", 2000, "Director1");
    assertEquals(0, _inventory.size());
    assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
    assertEquals(1, _inventory.size());
    assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
    assertEquals(1, _inventory.size());
    System.out.println(v1);
    check(v1,10,0,0);
    
    Video v2 = Data.newVideo("Title2", 2000, "Director2");
    assertEquals(1, _inventory.size());
    assertTrue(Data.newAddCmd(_inventory, v2, 5).run());
    assertEquals(2, _inventory.size());
    assertTrue(Data.newAddCmd(_inventory, v2, 5).run());
    assertEquals(2, _inventory.size());
    System.out.println(v2);
    check(v2,10,0,0);
    assertTrue(Data.newOutCmd(_inventory, v2).run());
    check(v2,10,1,1);
    assertTrue(Data.newUndoCmd(_inventory).run());
    check(v2,10,0,0);
    assertTrue(Data.newRedoCmd(_inventory).run());
    check(v2,10,1,1);
    assertTrue(Data.newInCmd(_inventory, v2).run());
    check(v2,10,0,1);
    assertTrue(Data.newClearCmd(_inventory).run());
    assertEquals(0, _inventory.size());
      
      
  }
  
  /**
   * Function to test the data classes.
   */
  public void test2() {
	  
	  //Create a command object
	  Command test = Data.newClearCmd(_inventory);
	  test.run();
	  
	  //Assert that inventory exists and not null
	  assertTrue(_inventory != null);
	  assertEquals(0, _inventory.size());
	  
	  //Create 3 video objects
	  Video v = Data.newVideo("Girls Gone Wild", 2019, "Steven Spielberg");
	  Video v2 = Data.newVideo("Debbie Does Dallas", 1999, "Martin Scorcese");
	  Video v3 = Data.newVideo("BangBus", 2015, "Christopher Nolan");
	  
	  //Add video v and test parameters
      assertTrue(Data.newAddCmd(_inventory, v, 5).run());
	  assertEquals(1, _inventory.size());
	  assertEquals("Girls Gone Wild", _inventory.get(v).video().title());
	  assertEquals("Steven Spielberg", _inventory.get(v).video().director());
	  assertEquals(5, _inventory.get(v).numOwned());
	  assertEquals(2019, _inventory.get(v).video().year());
	  
	  //Add video v2 and test parameters
	  assertTrue(Data.newAddCmd(_inventory, v2, 3).run());
	  assertEquals(2, _inventory.size());
	  assertEquals("Debbie Does Dallas", _inventory.get(v2).video().title());
	  assertEquals("Martin Scorcese", _inventory.get(v2).video().director());
	  assertEquals(3, _inventory.get(v2).numOwned());
	  assertEquals(1999, _inventory.get(v2).video().year());
	  
	  //Add video v3 and test parameters
	  assertTrue(Data.newAddCmd(_inventory, v3, 11).run());
	  assertEquals(3, _inventory.size());
	  assertEquals("BangBus", _inventory.get(v3).video().title());
	  assertEquals("Christopher Nolan", _inventory.get(v3).video().director());
	  assertEquals(11, _inventory.get(v3).numOwned());
	  assertEquals(2015, _inventory.get(v3).video().year());
	  
	  //Check videos
	  expect(v,"Girls Gone Wild (2019) : Steven Spielberg [5,0,0]");
	  expect(v2, "Debbie Does Dallas (1999) : Martin Scorcese [3,0,0]");
	  expect(v3, "BangBus (2015) : Christopher Nolan [11,0,0]");
	  try {
		  Data.newAddCmd(_inventory, null, 5).run();
	  } catch (IllegalArgumentException e) {}
	  try {
		  Data.newAddCmd(_inventory, v, 0).run();
	  } catch (IllegalArgumentException e) {}
	  
	  // Test newCmdIn and newCmdOut
	  try {
		  Data.newInCmd(_inventory, v).run();
	  } catch (IllegalArgumentException e) {}
	  try {
		  Data.newOutCmd(_inventory, null).run();
	  } catch (IllegalArgumentException e) {}
	  
	  //Test check in and check out for video v
	  assertEquals(5, _inventory.get(v).numOwned());
	  assertTrue(Data.newOutCmd(_inventory, v).run());
	  assertTrue(Data.newOutCmd(_inventory, v).run());
	  expect(v,"Girls Gone Wild (2019) : Steven Spielberg [5,2,2]");
	  assertEquals(2, _inventory.get(v).numOut());
	  assertTrue(Data.newInCmd(_inventory, v).run());
	  expect(v,"Girls Gone Wild (2019) : Steven Spielberg [5,1,2]");
	  assertTrue(Data.newOutCmd(_inventory, v).run());
	  expect(v,"Girls Gone Wild (2019) : Steven Spielberg [5,2,3]");
	  assertEquals(2,_inventory.get(v).numOut());
	  assertEquals(3, _inventory.get(v).numRentals());
	  
	  //Test invariants with video v2
	  
	  assertTrue(Data.newOutCmd(_inventory, v2).run());
	  expect(v2, "Debbie Does Dallas (1999) : Martin Scorcese [3,1,1]");
	  assertEquals(1, _inventory.get(v2).numOut());
	  assertTrue(Data.newOutCmd(_inventory, v2).run());
	  assertTrue(Data.newOutCmd(_inventory, v2).run());
	  expect(v2, "Debbie Does Dallas (1999) : Martin Scorcese [3,3,3]");
	  try {
		  Data.newOutCmd(_inventory, v2).run();
	  } catch (IllegalArgumentException e) {}
	  
	  //Test check in and check out for video v3
	  assertTrue(Data.newOutCmd(_inventory, v3).run());
	  assertTrue(Data.newOutCmd(_inventory, v3).run());
	  assertTrue(Data.newOutCmd(_inventory, v3).run());
	  assertTrue(Data.newOutCmd(_inventory, v3).run());
	  expect(v3, "BangBus (2015) : Christopher Nolan [11,4,4]");
	  assertEquals(4, _inventory.get(v3).numOut());
	  assertTrue(Data.newInCmd(_inventory, v3).run());
	  assertTrue(Data.newInCmd(_inventory, v3).run());
	  expect(v3, "BangBus (2015) : Christopher Nolan [11,2,4]");
	  assertTrue(Data.newOutCmd(_inventory, v3).run());
	  assertTrue(Data.newOutCmd(_inventory, v3).run());
	  assertTrue(Data.newOutCmd(_inventory, v3).run());
	  expect(v3, "BangBus (2015) : Christopher Nolan [11,5,7]");
	  assertTrue(Data.newInCmd(_inventory, v3).run());
	  expect(v3, "BangBus (2015) : Christopher Nolan [11,4,7]");
	  assertTrue(Data.newOutCmd(_inventory, v3).run());
	  expect(v3, "BangBus (2015) : Christopher Nolan [11,5,8]");
	  assertEquals(5, _inventory.get(v3).numOut());
	  assertEquals(8, _inventory.get(v3).numRentals());

	  //Test newClearCmd and check if records are erased
	  assertEquals(3, _inventory.size());
	  assertTrue(Data.newClearCmd(_inventory).run());
	  assertEquals(0, _inventory.size());
	  
	  
	  //Repopulate inventory
	  assertTrue(Data.newAddCmd(_inventory, v, 4).run());
	  assertTrue(Data.newAddCmd(_inventory, v2, 12).run());
	  assertTrue(Data.newAddCmd(_inventory, v3, 6).run());
	  Video v5 = Data.newVideo("Penthouse Playmates", 2013, "Guy Ritchie");
	  assertTrue(Data.newAddCmd(_inventory, v5, 2).run());
	  
	  //Create and test generic iterator
	  Iterator<Record> is = _inventory.iterator();
	  expect(is.next(), "BangBus (2015) : Christopher Nolan [6,0,0]");
	  expect(is.next(), "Penthouse Playmates (2013) : Guy Ritchie [2,0,0]");
	  expect(is.next(), "Debbie Does Dallas (1999) : Martin Scorcese [12,0,0]");
	  expect(is.next(),"Girls Gone Wild (2019) : Steven Spielberg [4,0,0]");
	  assertFalse(is.hasNext());
	  
	  //Create and test iterator with comparator
	  assertTrue(Data.newOutCmd(_inventory, v5).run());
	  assertTrue(Data.newOutCmd(_inventory, v2).run());
	  assertTrue(Data.newOutCmd(_inventory, v2).run());
	  assertTrue(Data.newOutCmd(_inventory, v3).run());
	  assertTrue(Data.newOutCmd(_inventory, v3).run());
	  assertTrue(Data.newOutCmd(_inventory, v3).run());
	  Iterator<Record> it = _inventory.iterator(new java.util.Comparator<Record>() {
	        public int compare (Record r1, Record r2) {
	          return r2.numRentals() - r1.numRentals();
	        }
	      });
	  expect(it.next(),"BangBus (2015) : Christopher Nolan [6,3,3]");
	  expect(it.next(),"Debbie Does Dallas (1999) : Martin Scorcese [12,2,2]");
	  expect(it.next(), "Penthouse Playmates (2013) : Guy Ritchie [2,1,1]");
	  expect(it.next(),"Girls Gone Wild (2019) : Steven Spielberg [4,0,0]");
	    
	  assertFalse(it.hasNext());
	  
  }
  

  
}
