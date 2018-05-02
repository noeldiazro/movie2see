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

    public void testMakeRatingFromString() {
        assertEquals(new Rating(4), Rating.from("4"));
    }

    public void testCompare() {
        assertEquals(0, new Rating(4).compareTo(new Rating(4)));
        assertTrue(new Rating(1).compareTo(new Rating(2)) < 0);
        assertTrue(new Rating(2).compareTo(new Rating(1)) > 0);
    }

    public void testAnonymousRating() {
        Rating rating = new Rating(3);
        assertEquals("Anonymous", rating.getSource());
    }

    public void testRatingWithSource() {
        final int value = 3;
        final String source = "NY Times";
        Rating rating = new Rating(value, source);
        assertEquals(value, rating.getValue());
        assertEquals(source, rating.getSource());
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestRating.class);
    }
}
