package data;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

//Test rest of data package
public class DataTest {

    //Test newInventory method
    @Test
    @DisplayName("newInventory method creates new InventorySet with size 0")
    void newInventoryTest() {
        Inventory i = Data.newInventory();
        assertEquals(0, i.size());
    }

    /*
    Test newVideo method
     */
    //Verify title is correct
    @Test
    @DisplayName("Verify title matches input")
    void testTitle() {
        Video movie = Data.newVideo("The Crow", 1994, "Alex Proyas");
        assertEquals("The Crow", movie.title());
    }

    //Verify year is correct
    @Test
    @DisplayName("Verify year matches input")
    void testYear() {
        Video movie = Data.newVideo("The Crow", 1994, "Alex Proyas");
        assertEquals(1994, movie.year());
    }

    //Verify director is correct
    @Test
    @DisplayName("Verify director matches input")
    void testDirector() {
        Video movie = Data.newVideo("The Crow", 1994, "Alex Proyas");
        assertEquals("Alex Proyas", movie.director());
    }

    //Verify max and min year
    @ParameterizedTest(name = "Max year is 4999 and min year is 1801")
    @ValueSource(ints = {1801, 4999})
    void minMaxYearTest(int year) {
        Video movie = Data.newVideo("Striptease", year, "Johnny Drama");
        assertEquals(year, movie.year());
    }

    //Verify title, year, and director invariants
    @ParameterizedTest(name = "Title, year, and directory invariant tests")
    @MethodSource("StrongRobustInvariantTests")
    void invariantsTests(String title, int year, String director) {
        assertThrows(IllegalArgumentException.class, () -> Data.newVideo(title, year, director));
    }

    private static Stream<Arguments> StrongRobustInvariantTests() {
        return Stream.of(
                Arguments.of(null, 2000, "Spielberg"),
                Arguments.of("", 2000, "Spielberg"),
                Arguments.of(" ", 2000, "Spielberg"),
                Arguments.of("Nerds", 1800, "George Lucas"),
                Arguments.of("Nerds II", 5000, "George Lucas"),
                Arguments.of("Jumanji", 2020, null),
                Arguments.of("Jumanji", 2020, ""),
                Arguments.of("Jumanji", 2020, " ")
        );
    }

    /*
    newAddCmd Tests
     */
    //newAddCmd IllegalArgumentException
    @Test
    @DisplayName("newAddCmd Illegal Argument Exception Test")
    void newAddCmdTest() {
        Inventory i = mock(Inventory.class);
        Video v = mock(Video.class);
        assertThrows(IllegalArgumentException.class, () -> Data.newAddCmd(i, v, 4));
    }

    //newAddCmd Test
    @Test
    @DisplayName("newAddCmd Test")
    void newAddCmdTest2() {
        Video v = mock(Video.class);
        InventorySet is = mock(InventorySet.class);
        CmdAdd ca = new CmdAdd(is, v, 10);
        assertEquals(ca, Data.newAddCmd(is, v, 10));
    }

    //newOutCmd IllegalArgumentException
    @Test
    @DisplayName("newOutCmd Illegal Argument Exception Test")
    void newOutCmdTest() {

        Inventory i = mock(Inventory.class);
        Video v = mock(Video.class);
        assertThrows(IllegalArgumentException.class, () -> Data.newOutCmd(i, v));
    }

    //newOutCmd Test
    @Test
    @DisplayName("newOutCmd Test")
    void newOutCmdTest2() {
        Video v = mock(Video.class);
        InventorySet is = mock(InventorySet.class);
        CmdOut co = new CmdOut(is, v);
        assertEquals(co, Data.newOutCmd(is, v));
    }

    //newInCmd IllegalArgumentException
    @Test
    @DisplayName("newInCmd Illegal Argument Exception Test")
    void newInCmdTest() {

        Inventory i = mock(Inventory.class);
        Video v = mock(Video.class);
        assertThrows(IllegalArgumentException.class, () -> Data.newInCmd(i, v));
    }

    //newInCmd Test
    @Test
    @DisplayName("newInCmd Test")
    void newInCmdTest2() {
        Video v = mock(Video.class);
        InventorySet is = mock(InventorySet.class);
        CmdIn ci = new CmdIn(is, v);
        assertEquals(ci, Data.newInCmd(is, v));
    }

    //newClearCmd IllegalArgumentException
    @Test
    @DisplayName("newClearCmd Illegal Argument Exception Test")
    void newClearCmdTest() {
        Inventory i = mock(Inventory.class);
        assertThrows(IllegalArgumentException.class, () -> Data.newClearCmd(i));
    }

    //newInCmd Test
    @Test
    @DisplayName("newClearCmd Test")
    void newClearCmdTest2() {
        InventorySet is = mock(InventorySet.class);
        CmdClear cc = new CmdClear(is);
        assertEquals(cc, Data.newClearCmd(is));
    }

    //newUndoCmd IllegalArgumentException
    @Test
    @DisplayName("newUndoCmd Illegal Argument Exception Test")
    void newUndoCmdTest() {

        Inventory i = mock(Inventory.class);
        Video v = mock(Video.class);
        assertThrows(IllegalArgumentException.class, () -> Data.newUndoCmd(i));
    }

    //newRedoCmd IllegalArgumentException
    @Test
    @DisplayName("newRedoCmd Illegal Argument Exception Test")
    void newRedoCmdTest() {

        Inventory i = mock(Inventory.class);
        Video v = mock(Video.class);
        assertThrows(IllegalArgumentException.class, () -> Data.newRedoCmd(i));
    }

}