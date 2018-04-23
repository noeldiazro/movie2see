package es.montanus.movie2see.tests;

import es.montanus.movie2see.Category;
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

    public void testFilterByCategory_GivenNull_ThrowsIllegalArgumentException() {
        try {
            movieList.filterByCategory(null);
            fail("IllegalArgumentException should have been thrown.");
        }
        catch (IllegalArgumentException expected) {
            assertEquals(MovieList.NULL_CATEGORY_MSG, expected.getMessage());
        }
    }

    public void testToString() {
        assertEquals("[]", movieList.toString());
    }

    public void testFilterByCategory_GivenCategory_ReturnsEmptyMovieList() {
        assertEquals(new MovieList(), movieList.filterByCategory(Category.FANTASY));
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestEmptyMovieList.class);
    }
}
