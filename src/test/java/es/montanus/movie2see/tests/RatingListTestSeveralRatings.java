package es.montanus.movie2see.tests;

import es.montanus.movie2see.Rating;

public class RatingListTestSeveralRatings extends RatingListTestNonEmptyList {
    @Override
    protected void populate() {
        add(new Rating(3));
        add(new Rating(4));
        add(new Rating(4));
        add(new Rating(5));
    }

    @Override
    protected Rating getExpectedAverageRating() {
        return new Rating(4);
    }

    @Override
    protected int getExpectedSize() {
        return 4;
    }
}
