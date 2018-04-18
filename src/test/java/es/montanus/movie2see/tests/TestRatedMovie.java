package es.montanus.movie2see.tests;

import es.montanus.movie2see.Movie;
import es.montanus.movie2see.Rating;
import junit.framework.TestCase;

public class TestRatedMovie extends TestCase {
    public void testCreateWithRating() {

        final String fotr = "The Fellowship of the Ring";
        final int value = 5;
        Movie movie = new Movie(fotr, new Rating(value));
        assertEquals(fotr, movie.getName());
        assertTrue(movie.isRated());
        assertEquals(new Rating(value), movie.getRating());
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestRatedMovie.class);
    }
}
