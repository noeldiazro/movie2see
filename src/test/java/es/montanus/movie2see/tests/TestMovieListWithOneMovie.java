package es.montanus.movie2see.tests;

import es.montanus.movie2see.Movie;
import es.montanus.movie2see.MovieList;
import junit.framework.TestCase;

public class TestMovieListWithOneMovie extends TestCase {
    private MovieList movieList;

    public void setUp() throws Exception {
        super.setUp();
        movieList = new MovieList();
        movieList.add(new Movie());
    }

    public void testSizeShouldBeOne() {
        assertEquals(1, movieList.size());
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestMovieListWithOneMovie.class);
    }
}
