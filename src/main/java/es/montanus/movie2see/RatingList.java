package es.montanus.movie2see;

import java.util.ArrayList;
import java.util.List;

public class RatingList {

    public static final String NULL_RATING_MSG = "Rating can't be null";
    private List<Rating> ratings = new ArrayList<>();

    public boolean isEmpty() {
        return ratings.isEmpty();
    }

    public void add(Rating rating) {
        if (rating == null) throw new IllegalArgumentException(NULL_RATING_MSG);

        ratings.add(rating);
    }

    public int size() {
        return ratings.size();
    }

    public Rating getAverageRating() {
        if (isEmpty())
            throw new EmptyRatingListException();

        return new Rating(getAverage());
    }

    private int getAverage() {
        int total = 0;
        for (Rating rating: ratings)
            total += rating.getValue();
        return total / size();
    }
}
