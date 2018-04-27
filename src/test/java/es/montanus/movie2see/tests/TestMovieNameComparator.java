package es.montanus.movie2see.tests;

import es.montanus.movie2see.Movie;
import es.montanus.movie2see.MovieNameComparator;
import junit.framework.TestCase;

public class TestMovieNameComparator extends TestCase {

    private MovieNameComparator comparator;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        comparator = new MovieNameComparator();
    }

    public void testCompareSame() {
        final String name = "A";
        Movie movie1 = getMovie(name);
        Movie movie2 = getMovie("A");

        assertEquals(0, comparator.compare(movie1, movie2));
    }

    public void testCompareBefore() {
        Movie movie1 = getMovie("A");
        Movie movie2 = getMovie("B");

        assertTrue(comparator.compare(movie1, movie2) < 0);
    }

    public void testCompareAfter() {
        Movie movie1 = getMovie("B");
        Movie movie2 = getMovie("A");

        assertTrue(comparator.compare(movie1, movie2) > 0);
    }

    private Movie getMovie(String name) {
        return new Movie.Builder(name).build();
    }
}
