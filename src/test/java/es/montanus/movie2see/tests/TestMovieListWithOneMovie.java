package es.montanus.movie2see.tests;

import es.montanus.movie2see.Category;
import es.montanus.movie2see.DuplicateMovieException;
import es.montanus.movie2see.Movie;
import es.montanus.movie2see.MovieList;

public class TestMovieListWithOneMovie extends TestMovieList {

    @Override
    protected void populate(MovieList list) {
        list.add(new Movie.Builder("Movie Name").setCategory(Category.FANTASY).build());
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
        MovieList expected = getPopulatedMovieList();
        assertEquals(expected, movieList.filterByCategory(Category.FANTASY));
    }

    public void testFilterByCategory_MovieIsNotFromGivenCategory() {
        assertEquals(getEmptyMovieList(), movieList.filterByCategory(Category.HORROR));
    }

    public void testFilterByCategory_All() {
        assertEquals(getPopulatedMovieList(), movieList.filterByCategory(Category.ALL));
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestMovieListWithOneMovie.class);
    }
}
