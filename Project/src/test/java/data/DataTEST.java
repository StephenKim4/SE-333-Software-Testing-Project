package data;

import junit.framework.TestCase;


public class DataTEST extends TestCase {
  public DataTEST(String name) {
    super(name);
  }
  
  /**
   * Function to test newVideo constructor
   */
  public void testConstructorAndAttributes() {
    String title1 = "XX";
    String director1 = "XY";
    String title2 = " XX ";
    String director2 = " XY ";
    int year = 2002;

    Video v1 = Data.newVideo(title1, year, director1);
    assertSame(title1, v1.title());
    assertEquals(year, v1.year());
    assertSame(director1, v1.director());

    Video v2 = Data.newVideo(title2, year, director2);
    assertEquals(title1, v2.title());
    assertEquals(director1, v2.director());
  }

  /**
   * Function to test exceptions for Year
   */
  public void testConstructorExceptionYear() {
    try {
      Data.newVideo("X", 1800, "Y");
      fail();
    } catch (IllegalArgumentException e) { }
    try {
      Data.newVideo("X", 5000, "Y");
      fail();
    } catch (IllegalArgumentException e) { }
    try {
      Data.newVideo("X", 1801, "Y");
      Data.newVideo("X", 4999, "Y");
    } catch (IllegalArgumentException e) {
      fail();
    }
  }
  
  /**
   * Function to test exceptions for Title
   */
  public void testConstructorExceptionTitle() {
    try {
      Data.newVideo(null, 2002, "Y");
      fail();
    } catch (IllegalArgumentException e) { }
    try {
      Data.newVideo("", 2002, "Y");
      fail();
    } catch (IllegalArgumentException e) { }
    
    try {
      Data.newVideo(" ", 2002, "Y");
      fail();
    } catch (IllegalArgumentException e) { }
  }

  /**
   * Function to test exceptions for Director
   */
  public void testConstructorExceptionDirector() {
	  try {
	      Data.newVideo("Y", 2002, null);
	      fail();
	    } catch (IllegalArgumentException e) { }
	    try {
	      Data.newVideo("Y", 2002, "");
	      fail();
	    } catch (IllegalArgumentException e) { }
	    
	    try {
	      Data.newVideo("Y", 2002, " ");
	      fail();
	    } catch (IllegalArgumentException e) { }
  }

  
  /**
   * Function to test toString method
   */
  public void testToString() { 
    String s = Data.newVideo("A",2000,"B").toString();
    assertEquals( s, "A (2000) : B" );
    s = Data.newVideo(" A ",2000," B ").toString();
    assertEquals( s, "A (2000) : B" );
    
  }
  
  /**
   * Test hash consing
   */
  public void testHashConsing() {
	  Video v = Data.newVideo("Z", 2000, "Z");
	  Video v1 = Data.newVideo("Y", 2000, "Y");
	  Video v3 = Data.newVideo("Z", 2000, "Z");
	  Video v4 = Data.newVideo("Y", 2000, "Y");
	  
	  
	  assertTrue (Data.bag.get(v) == v3);
	  assertTrue (Data.bag.get(v1) == v4);
	  assertFalse (Data.bag.get(v) == v1);
	  
  }
  
  
}

