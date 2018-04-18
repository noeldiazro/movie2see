package es.montanus.movie2see.tests;

import es.montanus.movie2see.Rating;
import junit.framework.TestCase;

public class TestRating extends TestCase {
    public void testCreateRating() {
        final int value = 3;
        Rating rating = new Rating(value);
        assertEquals(new Rating(value), rating);
        assertEquals(value, rating.getValue());
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestRating.class);
    }
}
