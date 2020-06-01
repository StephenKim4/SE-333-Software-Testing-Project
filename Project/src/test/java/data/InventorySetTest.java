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
import java.util.*;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Class to test InventorySet and Record.
 * 
 * @author Stephen Kim
 *
 */
public class InventorySetTest {

    //Create objects
    private static InventorySet inv;
    private static Video v1;
    private static Video v2;
    private static Video v3;

    //Instantiate new objects before each test
    @BeforeEach
    public void startup() {
        inv = new InventorySet();
        v1 = new VideoObj("True Romance", 1993, "Quentin Tarantino");
        v2 = new VideoObj("Fast Times at Ridgemont High", 1982, "Amy Heckerling");
        v3 = new VideoObj("Coneheads", 1993, "Steve Barron");
    }

    //addNumOwned null test
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

    //addNumOwned illegal argument exceptions tests
    @ParameterizedTest(name = "addNumOwned video illegal argument exceptions test")
    @ValueSource(ints = {0, -2})
    void addNumOwnedVideoIllegalArgumentExceptionsTest(int change) {
        inv.addNumOwned(v1, 1);
        assertThrows(IllegalArgumentException.class, () -> inv.addNumOwned(v1, change));
    }

    //addNumOwned test to add video
    @ParameterizedTest(name = "If record is null and change is greater than 0 add video to inventory")
    @ValueSource(ints = 1)
    void addNumOwnedRecordNullTest(int change) {
        inv.addNumOwned(v1, change);
        Video v = new VideoObj("True Romance", 1993, "Quentin Tarantino");
        assertEquals(v, inv.get(v1).video());
    }

    //Test addNumOwned
    @ParameterizedTest(name = "addNumOwned test if record is not not null and no exceptions")
    @MethodSource("addNumOwnedTests")
    void addNumOwnedTests(int change, int expected) {
        inv.addNumOwned(v1, 10);
        inv.addNumOwned(v1, change);
        assertEquals(expected,inv.get(v1).numOwned());
    }

    private static Stream<Arguments> addNumOwnedTests() {
        return Stream.of(
                Arguments.of(1, 11),
                Arguments.of(101, 111),
                Arguments.of(-1, 9),
                Arguments.of(-8, 2)
        );
    }
    //addNumOwned test to remove video if numowned becomes 0
    @Test
    @DisplayName("addNumOwned test if change is negative and numowned becomes 0, remove video from inventoryset")
    void addNumOwnedRemoveVideoTest() {
        inv.addNumOwned(v1, 10);
        inv.addNumOwned(v1, -10);
        assertEquals(null, inv.get(v1));
    }

    //Test size function
    @ParameterizedTest(name = "Verify size function")
    @MethodSource("sizeTests")
    void sizeTest(int initialV1, int changeV1, int initialV2, int changeV2, int expected) {
        inv.addNumOwned(v1, initialV1);
        inv.addNumOwned(v1, changeV1);
        inv.addNumOwned(v2, initialV2);
        inv.addNumOwned(v2, changeV2);
        assertEquals(expected, inv.size());
    }

    private static Stream<Arguments> sizeTests() {
        return Stream.of(
                Arguments.of(1, 1, 1, 1, 2),
                Arguments.of(1, 200, 200, 1, 2),
                Arguments.of(5, 5, 5, -5, 1)
        );
    }

    //Test clear method
    @Test
    @DisplayName("Clear removes all of the videos from inventoryset")
    void clearTest() {
        inv.addNumOwned(v1, 10);
        inv.addNumOwned(v2, 20);
        inv.addNumOwned(v3, 40);
        inv.clear();
        assertEquals(0, inv.size());
    }

    /*
    checkOut method Tests
     */
    @ParameterizedTest(name = "Test for illegal argument exceptions")
    @MethodSource("checkOutExceptionTests")
    void checkOutExceptionTest(Video v) {
        inv.addNumOwned(v1, 10);
        inv.addNumOwned(v2, 1);
        inv.checkOut(v2);
        assertThrows(IllegalArgumentException.class, () -> inv.checkOut(v));
    }

    private static Stream<Arguments> checkOutExceptionTests() {
        return Stream.of(
                Arguments.of(v3),
                Arguments.of(v2)
        );
    }

    @ParameterizedTest(name = "checkOut method increases numOut and numRentals by 1")
    @MethodSource("checkOutTests")
    void checkOutTest(Video v, int expected) {
        inv.addNumOwned(v1, 10);
        inv.addNumOwned(v2, 20);
        inv.checkOut(v);
        assertEquals(expected, inv.get(v).numOut());
        assertEquals(expected, inv.get(v).numRentals());
    }

    private static Stream<Arguments> checkOutTests() {
        return Stream.of(
                Arguments.of(v1, 1),
                Arguments.of(v2, 1)
        );
    }

    /*
    checkIn method Tests
     */
    @ParameterizedTest(name = "Test for illegal argument exceptions")
    @MethodSource("checkInExceptionTests")
    void checkInExceptionTest(Video v) {
        inv.addNumOwned(v1, 10);
        inv.addNumOwned(v2, 1);
        assertThrows(IllegalArgumentException.class, () -> inv.checkIn(v));
    }

    private static Stream<Arguments> checkInExceptionTests() {
        return Stream.of(
                Arguments.of(v3),
                Arguments.of(v2)
        );
    }

    @ParameterizedTest(name = "checkIn method decreases numOut by 1")
    @MethodSource("checkInTests")
    void checkInTest(Video v, int expected) {
        inv.addNumOwned(v1, 10);
        inv.addNumOwned(v2, 20);
        inv.checkOut(v2);
        inv.checkOut(v);
        inv.checkIn(v);
        assertEquals(expected, inv.get(v).numOut());
    }

    private static Stream<Arguments> checkInTests() {
        return Stream.of(
                Arguments.of(v1, 0),
                Arguments.of(v2, 1)
        );
    }

    //Test get method
    @ParameterizedTest(name = "get method returns record")
    @MethodSource("getRecordTests")
    void getRecordTest(Video v, InventorySet.RecordObj expected) {
        inv.addNumOwned(v1, 10);
        inv.addNumOwned(v2, 1);
        assertEquals(expected, inv.get(v));
    }

    private static Stream<Arguments> getRecordTests() {
        return Stream.of(
                Arguments.of(v1, new InventorySet.RecordObj(v1, 10, 1, 1)),
                Arguments.of(v2, new InventorySet.RecordObj(v2, 1, 1, 1)),
                Arguments.of(v3, null)
        );
    }

    //Verify toString function returns correct InventorySet
    @ParameterizedTest(name = "toString tests")
    @MethodSource("toStringTests")
    void toStringTest(Video v, String expected) {
        inv.addNumOwned(v, 10);
        assertEquals(expected, inv.toString());
    }

    private static Stream<Arguments> toStringTests() {
        return Stream.of(
                Arguments.of(v1, "Database:" + "\n" +"  True Romance (1993) : Quentin Tarantino [10,0,0]\n"),
                Arguments.of(v2, "Database:" + "\n" +"  Fast Times at Ridgemont High (1982) : Amy Heckerling [10,0,0]\n")
        );
    }

    //Verify replaceMap function returns correct map
    @ParameterizedTest(name = "replaceMap tests")
    @MethodSource("replaceMapTests")
    void replaceMapTest(Video v, Record r, String s) {
        inv.addNumOwned(v1, 2);
        inv.addNumOwned(v2, 2);
        Map<Video, Record> map = new HashMap<Video, Record>();
        map.put(v, r);
        inv.replaceMap(map);
        assertEquals(s, inv.toString());
    }

    private static Stream<Arguments> replaceMapTests() {
        return Stream.of(
                Arguments.of(v1, new InventorySet.RecordObj(v1, 10, 0, 0), "Database:" + "\n" +"  True Romance (1993) : Quentin Tarantino [10,0,0]\n"),
                Arguments.of(v2, new InventorySet.RecordObj(v2, 1, 0, 0), "Database:" + "\n" +"  Fast Times at Ridgemont High (1982) : Amy Heckerling [1,0,0]\n")
        );
    }




    /*

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
