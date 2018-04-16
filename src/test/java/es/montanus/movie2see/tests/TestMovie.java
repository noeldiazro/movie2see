package es.montanus.movie2see.tests;

import es.montanus.movie2see.Movie;
import junit.framework.TestCase;

public class TestMovie extends TestCase {

    private Movie starWars;

    public void setUp() throws Exception {
        super.setUp();
        starWars = new Movie("Star Wars");
    }

    public void testName() {
        assertEquals("Star Wars", starWars.getName());
    }

    public void testToString() {
        assertEquals("Star Wars", starWars.toString());
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestMovie.class);
    }
}
