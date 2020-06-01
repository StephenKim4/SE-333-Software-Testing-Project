package data;

import data.InventorySet.RecordObj;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//Test RecordObj
public class RecordTest {


    //Create new objects
    private static VideoObj v1;
    private static VideoObj v2;
    private static RecordObj r;
    private static RecordObj r2;

    //Instantiate new recordObj before each test
    @BeforeEach
    public void start() {
        v1 = new VideoObj("True Romance", 1993, "Quentin Tarantino");
        v2 = new VideoObj("Fast Times at Ridgemont High", 1982, "Amy Heckerling");
        r = new RecordObj(v1, 10, 0, 0);
        r2 = new RecordObj(v2, 5, 3, 3);
    }
    /*
    Test recordObject constructor parameters
    */
    //Verify video is correct
    @Test
    @DisplayName("Verify video is correct in constructor")
    void testRecordObjConstructorVideo() {
        assertEquals(v1, r.video);
        assertEquals(v2, r2.video);
    }

    //Verify numOut is correct
    @Test
    @DisplayName("Verify numOut is correct in constructor")
    void testRecordObjConstructorNumOut() {
        assertEquals(0, r.numOut);
        assertEquals(3, r2.numOut);
    }

    //Verify numOwned is correct
    @Test
    @DisplayName("Verify numOwned is correct in constructor")
    void testRecordObjConstructorNumOwned() {
        assertEquals(10, r.numOwned);
        assertEquals(5, r2.numOwned);
    }

    //Verify numRentals is correct
    @Test
    @DisplayName("Verify numRentals is correct in constructor")
    void testRecordObjConstructorNumRentals() {
        assertEquals(0, r.numRentals);
        assertEquals(3, r2.numRentals);
    }

    //Verify that hashcode function works correctly
    @ParameterizedTest(name = "Hashcode tests")
    @MethodSource("hashTests")
    void hashCodeRecordTest(RecordObj rec, int hash) {
        assertEquals(hash, rec.hashCode());
    }

    private static Stream<Arguments> hashTests() {
        return Stream.of(
                Arguments.of(r, 763282579),
                Arguments.of(r2, -1772911188)
        );
    }

    //Verify that numOwned function works correctly
    @Test
    @DisplayName("numOwned function is correct using Mockito")
    void numOwnedRecordTest() {
        RecordObj r = mock(RecordObj.class);
        when(r.numOwned()).thenReturn(5);
        assertEquals(5, r.numOwned());
    }

    //Verify that numOut function works correctly
    @Test
    @DisplayName("numOut function is correct using Mockito")
    void numOutRecordTest() {
        RecordObj r = mock(RecordObj.class);
        when(r.numOut()).thenReturn(5);
        assertEquals(5, r.numOut());
    }

    //Verify that numRentals function works correctly
    @Test
    @DisplayName("numRentals function is correct using Mockito")
    void numRentalsRecordTest() {
        RecordObj r = mock(RecordObj.class);
        when(r.numRentals()).thenReturn(5);
        assertEquals(5, r.numRentals());
    }

    //Verify that numRentals function works correctly
    @ParameterizedTest(name = "video function tests")
    @MethodSource("videoTests")
    void videoRecordTest(RecordObj rec, Video vid) {
        assertEquals(vid, rec.video());
    }

    private static Stream<Arguments> videoTests() {
        return Stream.of(
                Arguments.of(r, v1),
                Arguments.of(r2, v2)
        );
    }

    //Verify toString function return correctly
    @ParameterizedTest(name = "toString tests")
    @MethodSource("toStringTests")
    void toStringRecordTest(String expected, Record rec) {
        assertEquals(expected, rec.toString());
    }

    private static Stream<Arguments> toStringTests() {
        return Stream.of(
                Arguments.of("True Romance (1993) : Quentin Tarantino [10,0,0]", r),
                Arguments.of("Fast Times at Ridgemont High (1982) : Amy Heckerling [5,3,3]", r2)
        );
    }

    //Verify copy function works correctly
    @ParameterizedTest(name = "copy function tests")
    @MethodSource("copyTests")
    void toStringRecordTest(VideoObj v3, int a, int b, int c, RecordObj rec) {
        RecordObj r3 = new RecordObj(v3, a, b, c);
        assertEquals(r3, rec.copy());
    }

    private static Stream<Arguments> copyTests() {
        return Stream.of(
                Arguments.of(v1, 10, 0, 0 , r),
                Arguments.of(v2, 5, 3, 3, r2)
        );
    }
}
