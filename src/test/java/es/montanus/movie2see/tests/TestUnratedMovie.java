package es.montanus.movie2see.tests;

import es.montanus.movie2see.Movie;
import es.montanus.movie2see.UnratedException;
import junit.framework.TestCase;

public class TestUnratedMovie extends TestCase {

    private static final String STAR_WARS = "Star Wars";
    private Movie movie;

    public void setUp() throws Exception {
        super.setUp();
        movie = new Movie(STAR_WARS);
    }

    public void testName() {
        assertEquals(STAR_WARS, movie.getName());
    }

    public void testToString() {
        assertEquals(STAR_WARS, movie.toString());
    }

    public void testRenaming() {
        final String newName = "Star Trek";
        movie.rename(newName);
        assertEquals(newName, movie.getName());
    }

    public void testMovieCreationWithNullNameShouldThrowIllegalArgumentException() {
        try {
            new Movie(null);
            fail();
        }
        catch (IllegalArgumentException expected) {
            assertEquals(Movie.NULL_NAME_MSG, expected.getMessage());
        }
    }

    public void testMovieCreationWithEmptyNameThrowsIllegalArgumentException() {
        try {
            new Movie("");
            fail();
        }
        catch (IllegalArgumentException expected) {
            assertEquals(Movie.EMPTY_NAME_MSG, expected.getMessage());
        }
    }

    public void testNullRename() {
        try {
            movie.rename(null);
            fail();
        }
        catch (IllegalArgumentException expected) {
            assertEquals(Movie.NULL_NAME_MSG, expected.getMessage());
        }
    }

    public void testEmptyRename() {
        try {
            movie.rename("");
            fail();
        }
        catch (IllegalArgumentException expected) {
            assertEquals(Movie.EMPTY_NAME_MSG, expected.getMessage());
        }
    }

    public void testMovieIsUnrated() {
        assertFalse(movie.isRated());
    }

    public void testUnratedException() {
        try {
            movie.getRating();
            fail();
        }
        catch (UnratedException expected) {
            assertEquals(String.format(UnratedException.MSG_FORMAT, STAR_WARS), expected.getMessage());
        }
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestUnratedMovie.class);
    }
}
