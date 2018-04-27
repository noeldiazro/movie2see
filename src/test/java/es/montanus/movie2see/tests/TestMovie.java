package es.montanus.movie2see.tests;

import es.montanus.movie2see.Movie;
import es.montanus.movie2see.Rating;
import junit.framework.TestCase;

public abstract class TestMovie extends TestCase {
    protected static final String MOVIE_NAME = "Movie Name";
    protected Movie.Builder builder;
    protected Movie movie;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        builder = new Movie.Builder(MOVIE_NAME);
        movie = buildMovie();
    }

    protected abstract Movie buildMovie();

    public void testName() {
        assertEquals(MOVIE_NAME, movie.getName());
    }

    public void testToString() {
        assertEquals(MOVIE_NAME, movie.toString());
    }

    public void testRenaming() {
        final String anotherName = "Another Name";
        movie.rename(anotherName);
        assertEquals(anotherName, movie.getName());
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

    public abstract void testIsRated();
    public abstract void testGetRating();

    public void testChangeRating() {
        final int newRatingValue = 5;
        movie.setRating(new Rating(newRatingValue));
        assertEquals(new Rating(newRatingValue), movie.getRating());
    }

    public abstract void testGetCategory();
}
