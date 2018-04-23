package es.montanus.movie2see.tests;

import es.montanus.movie2see.Category;
import es.montanus.movie2see.DuplicateMovieException;
import es.montanus.movie2see.Movie;
import es.montanus.movie2see.MovieList;
import junit.framework.TestCase;

public class TestMovieListWithOneMovie extends TestCase {
    private MovieList movieList;

    public void setUp() throws Exception {
        super.setUp();
        movieList = getEmptyMovieList();
        movieList.add(new Movie.Builder("Movie Name").setCategory(Category.FANTASY).build());
    }

    public void testSizeShouldBeOne() {
        assertEquals(1, movieList.size());
    }

    public void testAddingDuplicate() {
        try {
            movieList.add(new Movie.Builder("Movie Name").build());
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

    public void testToString() {
        assertEquals("[\"Movie Name\"]", movieList.toString());
    }

    public void testFilterByCategory_MovieIsFromGivenCategory() {
        MovieList expected = getEmptyMovieList();
        expected.add(new Movie.Builder("Movie Name").setCategory(Category.FANTASY).build());
        assertEquals(expected, movieList.filterByCategory(Category.FANTASY));
    }

    public void testFilterByCategory_MovieIsNotFromGivenCategory() {
        assertEquals(getEmptyMovieList(), movieList.filterByCategory(Category.HORROR));
    }

    private MovieList getEmptyMovieList() {
        return new MovieList();
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestMovieListWithOneMovie.class);
    }
}
