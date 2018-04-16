package es.montanus.movie2see.tests;

import es.montanus.movie2see.MovieList;
import junit.framework.TestCase;

public class TestEmptyMovieList extends TestCase {

    private MovieList movieList;

    public void setUp() throws Exception {
        super.setUp();
        movieList = new MovieList();
    }

    public void testSizeShouldBeZero() {
        assertEquals(0, movieList.size());
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestEmptyMovieList.class);
    }
}
