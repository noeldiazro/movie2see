package es.montanus.movie2see.tests;

import es.montanus.movie2see.Category;
import es.montanus.movie2see.Movie;
import es.montanus.movie2see.MovieList;
import es.montanus.movie2see.Rating;
import junit.framework.TestCase;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class TestPersistence extends TestCase {

    private MovieList movieList;
    private Writer destination;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        movieList = new MovieList();
        destination = new StringWriter();
    }

    public void testWritingEmptyList() throws IOException {
        movieList.writeTo(destination);
        assertWrites("");
    }

    public void testWritingOneMovie() throws IOException {
        movieList.add(new Movie.Builder("Star Wars").setCategory(Category.SCIFI).setRating(new Rating(4)).build());
        movieList.writeTo(destination);
        assertWrites("Star Wars|Science Fiction|4|1\n");
    }

    public void testWritingOneMovieWithoutRating() throws IOException {
        movieList.add(new Movie.Builder("Star Wars").setCategory(Category.SCIFI).build());
        movieList.writeTo(destination);
        assertWrites("Star Wars|Science Fiction|-1|0\n");
    }

    public void testWritingMultipleMovies() throws IOException {
        movieList.add(new Movie.Builder("Star Wars").setCategory(Category.SCIFI).setRating(new Rating(4)).build());
        movieList.add(new Movie.Builder("Star Trek").build());
        movieList.writeTo(destination);
        assertWrites(
                "Star Wars|Science Fiction|4|1\n" +
                "Star Trek|Uncategorized|-1|0\n"
        );
    }

    private void assertWrites(String expected) {
        assertEquals(expected, destination.toString());
    }
}
