package es.montanus.movie2see.tests;

import es.montanus.movie2see.EmptyRatingListException;

public class RatingListTestEmptyList extends RatingListTest {

    @Override
    public void testIsEmpty() {
        assertTrue(getRatings().isEmpty());
    }

    @Override
    protected int getExpectedSize() {
        return 0;
    }

    @Override
    public void testAverageRating() {
        try {
            getRatings().getAverageRating();
            fail();
        }
        catch (EmptyRatingListException exception) {}
    }
}
