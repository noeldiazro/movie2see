package es.montanus.movie2see.tests;

import es.montanus.movie2see.Rating;

public class RatingListTestOneRating extends RatingListTestNonEmptyList {
    @Override
    protected void populate() {
        add(new Rating(3));
    }

    @Override
    protected Rating getExpectedAverageRating() {
        return new Rating(3);
    }

    @Override
    protected int getExpectedSize() {
        return 1;
    }
}
