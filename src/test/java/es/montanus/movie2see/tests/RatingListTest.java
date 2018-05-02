package es.montanus.movie2see.tests;

import es.montanus.movie2see.Rating;
import es.montanus.movie2see.RatingList;
import junit.framework.TestCase;

public abstract class RatingListTest extends TestCase {
    private RatingList ratings;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        ratings = new RatingList();
    }

    protected RatingList getRatings() {
        return ratings;
    }

    protected void add(Rating rating) {
        ratings.add(rating);
    }

    public void testAddNull() {
        try {
            add(null);
            fail("IllegalArgumentException should have been thrown.");
        }
        catch (IllegalArgumentException ex) {
            assertEquals(RatingList.NULL_RATING_MSG, ex.getMessage());
        }
    }

    public abstract void testIsEmpty();

    public void testSize() {
        assertEquals(getExpectedSize(), ratings.size());
    }

    protected abstract int getExpectedSize();

    public abstract void testAverageRating();
}
