package data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class VideoTEST {

	//Verify title is correct
	@Test
	@DisplayName("Verify title matches input")
	void testTitle() {
		VideoObj movie = new VideoObj("The Crow", 1994, "Alex Proyas");
		assertEquals("The Crow", movie.title());
	}
}