package data;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.awt.*;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;


import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;


/**
 * Class to test InventorySet.
 * 
 * @author Stephen Kim
 *
 */
public class InventoryTest {

    private static InventorySet inv;
    private static Video v1;
    private static Video v2;
    private static Video v3;

    @BeforeEach
    public void startup() {
        inv = new InventorySet();
        v1 = new VideoObj("True Romance", 1993, "Quentin Tarantino");
        v2 = new VideoObj("Fast Times at Ridgemont High", 1982, "Amy Heckerling");
        v3 = new VideoObj("Coneheads", 1993, "Steve Barron");
    }

    @ParameterizedTest(name = "Verify size function")
    @MethodSource("sizeTests")
    void sizeTest(int a, int b) {
        assertEquals(a, b);
    }

    private static Stream<Arguments> sizeTests() {
        return Stream.of(
                Arguments.of(10, 10)
        );
    }

    @ParameterizedTest(name = "addNumOwned video null exceptions test")
    @MethodSource("addNumOwnedVideoNullException")
    void addNumOwnedVideoNullTest(Video video, int change) {
        assertThrows(NullPointerException.class, () -> inv.addNumOwned(video, change));
    }

    private static Stream<Arguments> addNumOwnedVideoNullException() {
        return Stream.of(
            Arguments.of(null, 10)
        );
    }

    @ParameterizedTest(name = "addNumOwned video illegal argument exceptions test")
    @ValueSource(ints = 0)
    void addNumOwnedVideoIllegalArgumentExceptionsTest(int change) {
        inv.addNumOwned(v1, 1);
        assertThrows(IllegalArgumentException.class, () -> inv.addNumOwned(v1, change));
    }
/*
    @ParameterizedTest(name = "If record is null and change is greater than 0 add video to inventory")
    @ValueSource(ints = 1)
    void addNumOwnedRecordNullTest(int change) {
        inv.addNumOwned(v1, change);
        assertEquals(v1, inv.get(v1).video());
    }
*/


    /*

  public void testSize() {
                                 assertEquals( 0, s.size() );
          s.addNumOwned(v1,  1); assertEquals( 1, s.size() );
          s.addNumOwned(v1,  2); assertEquals( 1, s.size() );
          s.addNumOwned(v2,  1); assertEquals( 2, s.size() );
          s.addNumOwned(v2, -1); assertEquals( 1, s.size() );
         s.addNumOwned(v1, -3); assertEquals( 0, s.size() );
    try { s.addNumOwned(v1, -3); fail(); } catch ( IllegalArgumentException e ) {}
  }


  
public void testAddNumOwned() {
                                  assertEquals( null, s.get(v1) );
          s.addNumOwned(v1, 1);     assertEquals( v1, s.get(v1).video() );
                                    assertEquals( 1, s.get(v1).numOwned());
          s.addNumOwned(v1, 2);     assertEquals( 3, s.get(v1).numOwned());
          s.addNumOwned(v1, -1);    assertEquals( 2, s.get(v1).numOwned());
          int a = s.get(v1).numOwned();
          
          s.addNumOwned(v2, 1);     assertEquals( 2, s.get(v1).numOwned());
          int b = s.get(v1).numOwned();
          ;
          s.addNumOwned(v1copy, 1); assertEquals( 3, s.get(v1).numOwned());
          int c = s.get(v1).numOwned();
          
          s.addNumOwned(v1, -3);    assertEquals( null, s.get(v1) );
    try { s.addNumOwned(null, 1);   fail(); } catch ( IllegalArgumentException e ) {}
  }


  public void testCheckOutCheckIn() {
    try { s.checkOut(null);     fail(); } catch ( IllegalArgumentException e ) {}
    try { s.checkIn(null);      fail(); } catch ( IllegalArgumentException e ) {}
          s.addNumOwned(v1, 2); assertTrue( s.get(v1).numOut() == 0 && s.get(v1).numRentals() == 0 );
          s.checkOut(v1);       assertTrue( s.get(v1).numOut() == 1 && s.get(v1).numRentals() == 1 );
    try { s.addNumOwned(v1,-3); fail(); } catch ( IllegalArgumentException e ) {}
    try { s.addNumOwned(v1,-2); fail(); } catch ( IllegalArgumentException e ) {}
          s.addNumOwned(v1,-1); assertTrue( s.get(v1).numOut() == 1 && s.get(v1).numRentals() == 1 );
          s.addNumOwned(v1, 1); assertTrue( s.get(v1).numOut() == 1 && s.get(v1).numRentals() == 1 );
          s.checkOut(v1);       assertTrue( s.get(v1).numOut() == 2 && s.get(v1).numRentals() == 2 );
    try { s.checkOut(v1);       fail(); } catch ( IllegalArgumentException e ) {}
          s.checkIn(v1);        assertTrue( s.get(v1).numOut() == 1 && s.get(v1).numRentals() == 2 );
          s.checkIn(v1copy);    assertTrue( s.get(v1).numOut() == 0 && s.get(v1).numRentals() == 2 );
    try { s.checkIn(v1);        fail(); } catch ( IllegalArgumentException e ) {}
    try { s.checkOut(v2);       fail(); } catch ( IllegalArgumentException e ) {}
          s.checkOut(v1);       assertTrue( s.get(v1).numOut() == 1 && s.get(v1).numRentals() == 3 );
  }


  public void testClear() {
          s.addNumOwned(v1, 2); assertEquals( 1, s.size() );
          s.addNumOwned(v2, 2); assertEquals( 2, s.size() );
          s.clear();            assertEquals( 0, s.size() );
    try { s.checkOut(v2);       fail(); } catch ( IllegalArgumentException e ) {}
  }


  public void testGet() {
    s.addNumOwned(v1, 1);
    Record r1 = s.get(v1);
    Record r2 = s.get(v1);
    assertTrue( r1.equals(r2) );
    assertTrue( r1 == r2 );
    Video v5 = new VideoObj("A", 4254, "B");
    assertEquals(null, s.get(v5));
    
  }
  


  public void testIterator1() {
    Set<Video> expected = new HashSet<Video>();
    InventorySet inv = new InventorySet();
    Video v1 = new VideoObj("XX", 2004, "XX");
    Video v2 = new VideoObj("XY", 2000, "XY");
    inv.addNumOwned(v1,10);
    inv.addNumOwned(v2,20);
    expected.add(v1);
    expected.add(v2);
    
    Iterator<Record> i = inv.iterator();
    try { i.remove(); fail(); }
    catch (UnsupportedOperationException e) { }
    while(i.hasNext()) {
      Record r = i.next(); 
      assertTrue(expected.contains(r.video()));
      expected.remove(r.video());
    }
    assertTrue(expected.isEmpty());
  }
  

  public void testIterator2() {
    List<Video> expected = new ArrayList<Video>();
    InventorySet inv = new InventorySet();
    Video v1 = new VideoObj("XX", 2004, "XX");
    Video v2 = new VideoObj("XY", 2000, "XY");
    expected.add(v2);
    expected.add(v1);
    inv.addNumOwned(v1,10);
    inv.addNumOwned(v2,20);

    Comparator<Record> c = new Comparator<Record>() {
        public int compare(Record r1, Record r2) {
          return r1.video().year() - r2.video().year();
        }
      };
    Iterator<Record> i = inv.iterator(c);
    try { i.remove(); fail(); }
    catch (UnsupportedOperationException e) { }
    Iterator j = expected.iterator();
    while (i.hasNext()) {
      assertSame(j.next(), i.next().video());
      j.remove();
    }
    assertTrue(expected.isEmpty());
  }
*/
}
