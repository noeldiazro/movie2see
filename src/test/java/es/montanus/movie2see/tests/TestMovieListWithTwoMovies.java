package es.montanus.movie2see.tests;

import es.montanus.movie2see.Movie;
import es.montanus.movie2see.MovieList;
import junit.framework.TestCase;

public class TestMovieListWithTwoMovies extends TestCase {
    private MovieList movieList;
    private Movie starWars;
    private Movie starTrek;

    public void setUp() throws Exception {
        super.setUp();
        movieList = new MovieList();
        starWars = new Movie("Star Wars");
        starTrek = new Movie("Star Trek");
        movieList.add(starWars);
        movieList.add(starTrek);
    }

    public void testSizeShouldBeTwo() {
        assertEquals(2, movieList.size());
    }

    public void testShouldContainAddedMovies() {
        assertTrue(movieList.contains(starWars));
        assertTrue(movieList.contains(starTrek));
    }

    public void testShouldNotContainMovieThatWasNotAdded() {
        Movie stargate = new Movie("Stargate");
        assertFalse(movieList.contains(stargate));
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestMovieListWithTwoMovies.class);
    }
}
