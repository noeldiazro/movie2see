package es.montanus.movie2see.tests;

import es.montanus.movie2see.Movie;
import junit.framework.TestCase;

public class TestMovie extends TestCase {

    private static final String STAR_WARS = "Star Wars";
    private Movie starWars;

    public void setUp() throws Exception {
        super.setUp();
        starWars = new Movie(STAR_WARS);
    }

    public void testName() {
        assertEquals(STAR_WARS, starWars.getName());
    }

    public void testToString() {
        assertEquals(STAR_WARS, starWars.toString());
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestMovie.class);
    }
}
