package data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.awt.*;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;



public class VideoTest {

	//Verify title is correct
	@Test
	@DisplayName("Verify title matches input")
	void testTitle() {
		VideoObj movie = new VideoObj("The Crow", 1994, "Alex Proyas");
		assertEquals("The Crow", movie.title());
	}

	//Verify year is correct
	@Test
	@DisplayName("Verify year matches input")
	void testYear() {
		VideoObj movie = new VideoObj("The Crow", 1994, "Alex Proyas");
		assertEquals(1994, movie.year());
	}

	//Verify director is correct
	@Test
	@DisplayName("Verify director matches input")
	void testDirector() {
		VideoObj movie = new VideoObj("The Crow", 1994, "Alex Proyas");
		assertEquals("Alex Proyas", movie.director());
	}

	//Verify max and min year
	@ParameterizedTest(name = "Max year is 4999 and min year is 1801")
	@ValueSource(ints = {1801, 4999})
	void minMaxYearTest(int year) {
		VideoObj movie = new VideoObj("Striptease", year, "Johnny Drama");
		assertEquals(year, movie.year());
	}

	//Verify title, year, and director invariants
	@ParameterizedTest(name = "Title, year, and directory invariant tests")
	@MethodSource("StrongRobustInvariantTests")
	void invariantsTests(String title, int year, String director) {
		assertThrows(IllegalArgumentException.class, () -> new VideoObj(title, year, director));
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

	//Verify that hashcode function works correctly
	@ParameterizedTest(name = "Hashcode tests")
	@MethodSource("hashTests")
	void hashCodeTest(String title, int year, String director, int hash) {
		VideoObj movie = new VideoObj(title, year, director);
		assertEquals(hash, movie.hashCode());
	}

	private static Stream<Arguments> hashTests() {
		return Stream.of(
				Arguments.of("Snatch", 2000, "Guy Ritchie", 1814389217),
				Arguments.of("Lock Stock and Two Smoking Barrels", 1998, "Guy Ritchie", -1594311983)
		);
	}

	//Verify toString function return correctly
	@ParameterizedTest(name = "toString tests")
	@MethodSource("toStringTests")
	void toStringTest(String title, int year, String director, String full) {
		VideoObj movie = new VideoObj(title, year, director);
		assertEquals(full, movie.toString());
	}

	private static Stream<Arguments> toStringTests() {
		return Stream.of(
				Arguments.of("Debbie Does Dallas", 1978, "Ron Jeremy", "Debbie Does Dallas (1978) : Ron Jeremy"),
				Arguments.of("Deep Throat", 1972, "Gerard Damiano", "Deep Throat (1972) : Gerard Damiano")
		);
	}

	//Check compareTo function for null pointer exception
	@ParameterizedTest(name = "Check compareTo method null pointer exceptions")
	@MethodSource("compareToNullExceptions")
	void compareToNullExceptionTests(VideoObj thisObj, VideoObj thatObj) {
		assertThrows(NullPointerException.class, () -> thatObj.compareTo(thisObj));
	}

	private static Stream<Arguments> compareToNullExceptions() {
		return Stream.of(
				Arguments.of(new VideoObj("Spawn",1997,"Mark Dippe"), null),
				Arguments.of(null, new VideoObj("Spawn",1997,"Mark Dippe"))
		);
	}

	//Check compareTo function for class cast exception
	@ParameterizedTest(name = "Check compareTo method class cast exceptions")
	@MethodSource("compareToClassExceptions")
	void compareToClassExceptionTests(VideoObj thisObj, Object thatObj) {
		assertThrows(ClassCastException.class, () -> thisObj.compareTo(thatObj));
	}

	private static Stream<Arguments> compareToClassExceptions() {
		return Stream.of(
				Arguments.of(new VideoObj("Spawn",1997,"Mark Dippe"), new Object())
		);
	}

	//Check attribute comparison in compareTo function
	@ParameterizedTest(name = "Check attribute comparison in compareTo method")
	@MethodSource("compareToTests")
	void compareToAttributeTests(VideoObj thisObj, VideoObj thatObj, int result) {
		assertEquals(result, thatObj.compareTo(thisObj));
	}

	private static Stream<Arguments> compareToTests() {
		return Stream.of(
				Arguments.of(new VideoObj("Spawn",1997,"Mark Dippe"), new VideoObj("Spaw",1997,"Mark Dippe"), -1),
				Arguments.of(new VideoObj("Spaw",1997,"Mark Dippe"), new VideoObj("Spawn",1997,"Mark Dippe"), 1),
				Arguments.of(new VideoObj("Spawn",2000,"Mark Dippe"), new VideoObj("Spawn",1997,"Mark Dippe"), -3),
				Arguments.of(new VideoObj("Spawn",1997,"Mark Dippe"), new VideoObj("Spawn",1990,"Mark Dippe"), -7),
				Arguments.of(new VideoObj("Spawn",1997,"Mark Dippe"), new VideoObj("Spawn",1997,"Mark Dipp"), -1),
				Arguments.of(new VideoObj("Spawn",1997,"Mark Dipper"), new VideoObj("Spawn",1997,"Mark Dippe"), -1),
				Arguments.of(new VideoObj("Spawn",1997,"Mark Dippe"), new VideoObj("Spawn",1997,"Mark Dippe"), 0)
		);
	}

	//Verify equals method null pointer exception
	@Test
	@DisplayName("Throw exception if object is null")
	void equalsNullTest() {
		VideoObj movie = new VideoObj("Striptease", 1996, "Andrew Bergman");
		assertThrows(NullPointerException.class, () -> movie.equals(null));
	}

	//Verify equals method
	@ParameterizedTest(name = "Check compareTo method class cast exceptions")
	@MethodSource("equalsTestsFull")
	void equalsTests(VideoObj thisObj, Object thatObj, boolean b) {
		assertEquals(b, thatObj.equals(thisObj));
	}

	private static Stream<Arguments> equalsTestsFull() {
		return Stream.of(
				Arguments.of(new VideoObj("Rounders",1998,"John Dahl"), new Object(), false),
				Arguments.of(new VideoObj("Rounders",1998,"John Dahl"), new VideoObj("Rounders",1998,"John Dahl"), true),
				Arguments.of(new VideoObj("Rounders",1998,"John Dahl"), new VideoObj("Rounders",1998,"John Dahly"), false),
				Arguments.of(new VideoObj("Rounders",1998,"John Dahl"), new VideoObj("Rounders",2000,"John Dahl"), false),
				Arguments.of(new VideoObj("Rounders",1998,"John Dahl"), new VideoObj("Rounder",1998,"John Dahl"), false)
				);
	}
}