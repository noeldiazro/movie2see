package es.montanus.movie2see.tests;

import es.montanus.movie2see.Rating;

public abstract class RatingListTestNonEmptyList extends RatingListTest {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        populate();
    }

    protected abstract void populate();

    @Override
    public void testIsEmpty() {
        assertFalse(getRatings().isEmpty());
    }

    @Override
    public void testAverageRating() {
        assertEquals(getExpectedAverageRating(), getRatings().getAverageRating());
    }

    protected abstract Rating getExpectedAverageRating();
}
