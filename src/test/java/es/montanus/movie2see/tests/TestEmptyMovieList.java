package es.montanus.movie2see.tests;

import es.montanus.movie2see.Category;
import es.montanus.movie2see.MovieList;

public class TestEmptyMovieList extends TestMovieList {

    @Override
    protected void populate(MovieList list) { }

    public void testSizeShouldBeZero() {
        assertEquals(0, movieList.size());
    }

    public void testToString() {
        assertEquals("[]", movieList.toString());
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

    public void testFilterByCategory_GivenCategory_ReturnsEmptyMovieList() {
        assertEquals(getEmptyMovieList(), movieList.filterByCategory(Category.FANTASY));
    }

    public void testFilterByCategory_All() {
        assertEquals(getEmptyMovieList(), movieList.filterByCategory(Category.ALL));
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestEmptyMovieList.class);
    }
}
