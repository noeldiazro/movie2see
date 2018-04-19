package es.montanus.movie2see.tests;

import es.montanus.movie2see.Movie;
import junit.framework.TestCase;

public class TestIllegalArgumentMovie extends TestCase {

    public void testMovieCreationWithNullNameShouldThrowIllegalArgumentException() {
        try {
            new Movie.Builder(null).build();
            fail();
        }
        catch (IllegalArgumentException expected) {
            assertEquals(Movie.NULL_NAME_MSG, expected.getMessage());
        }
    }

    public void testMovieCreationWithEmptyNameThrowsIllegalArgumentException() {
        try {
            new Movie.Builder("").build();
            fail();
        }
        catch (IllegalArgumentException expected) {
            assertEquals(Movie.EMPTY_NAME_MSG, expected.getMessage());
        }
    }

    public void testBadCategory() {
        final String validName = "Valid Name";
        final String invalidCategory = "Invalid Category";
        try {
            new Movie.Builder(validName).setCategory(null).build();
            fail();
        }
        catch (IllegalArgumentException expected) {
            assertEquals(Movie.NULL_CATEGORY_MSG, expected.getMessage());
        }
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestIllegalArgumentMovie.class);
    }
}
