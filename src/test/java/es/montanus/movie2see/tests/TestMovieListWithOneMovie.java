package es.montanus.movie2see.tests;

import es.montanus.movie2see.DuplicateMovieException;
import es.montanus.movie2see.Movie;
import es.montanus.movie2see.MovieList;
import junit.framework.TestCase;

public class TestMovieListWithOneMovie extends TestCase {
    private MovieList movieList;

    public void setUp() throws Exception {
        super.setUp();
        movieList = new MovieList();
        movieList.add(new Movie("Movie Name"));
    }

    public void testSizeShouldBeOne() {
        assertEquals(1, movieList.size());
    }

    public void testAddingDuplicate() {
        try {
            movieList.add(new Movie("Movie Name"));
            fail();
        }
        catch (DuplicateMovieException expected) {
            assertEquals(1, movieList.size());
            assertEquals(
                    String.format(DuplicateMovieException.MOVIE_PRESENT_MSG, "Movie Name"),
                    expected.getMessage()
            );
        }

    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestMovieListWithOneMovie.class);
    }
}
