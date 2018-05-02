package es.montanus.movie2see.tests;

import es.montanus.movie2see.Category;
import es.montanus.movie2see.Movie;
import es.montanus.movie2see.Rating;
import es.montanus.movie2see.UnratedException;

public class TestCategorizedMovie extends TestMovie {

    @Override
    protected Movie buildMovie() {
        return builder.setCategory(Category.SCIFI).build();
    }

    @Override
    public void testIsRated() {
        assertFalse(movie.isRated());
    }

    @Override
    public void testGetRating() {
        try {
            movie.getRating();
            fail();
        }
        catch (UnratedException expected) {
            assertEquals(String.format(UnratedException.MSG_FORMAT, MOVIE_NAME), expected.getMessage());
        }
    }

    @Override
    public void testGetCategory() {
        assertEquals(Category.SCIFI, movie.getCategory());
    }

    public void testAddingOneRating() {
        movie.addRating(new Rating(3));
        assertEquals(new Rating(3), movie.getRating());
    }

    public void testAddingSeveralRatings() {
        movie.addRating(new Rating(3));
        movie.addRating(new Rating(5));
        movie.addRating(new Rating(5));
        movie.addRating(new Rating(3));

        assertEquals(new Rating(4), movie.getRating());
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestCategorizedMovie.class);
    }
}
