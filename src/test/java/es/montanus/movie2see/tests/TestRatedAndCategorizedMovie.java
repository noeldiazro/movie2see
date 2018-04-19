package es.montanus.movie2see.tests;

import es.montanus.movie2see.Category;
import es.montanus.movie2see.Movie;
import es.montanus.movie2see.Rating;

public class TestRatedAndCategorizedMovie extends TestMovie {
    private static final int RATING_VALUE = 5;

    @Override
    protected Movie getMovie() {
        return new Movie.Builder(MOVIE_NAME).
                setRating(new Rating(RATING_VALUE)).
                setCategory(Category.SCIFI).
                build();
    }

    @Override
    public void testIsRated() {
        assertTrue(movie.isRated());
    }

    @Override
    public void testGetRating() {
        assertEquals(new Rating(RATING_VALUE), movie.getRating());
    }

    @Override
    public void testGetCategory() {
        assertEquals(Category.SCIFI, movie.getCategory());
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestRatedAndCategorizedMovie.class);
    }
}
