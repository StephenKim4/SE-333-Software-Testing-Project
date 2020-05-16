package data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

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
	@ParameterizedTest(name = "Title, Year, and Directory Invariant Tests")
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


}