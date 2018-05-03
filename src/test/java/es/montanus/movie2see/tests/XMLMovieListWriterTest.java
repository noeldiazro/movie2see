package es.montanus.movie2see.tests;

import es.montanus.movie2see.*;
import junit.framework.TestCase;

import java.io.StringWriter;
import java.io.Writer;

public class XMLMovieListWriterTest extends TestCase {

    private Writer destination;
    private MovieListWriter writer;
    private MovieList movieList;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        destination = new StringWriter();
        writer = new XMLMovieListWriter(destination);
        movieList = new MovieList();
    }

    public void testWritingEmptyList() {
        writer.write(movieList);
        assertEquals("<movielist></movielist>", destination.toString());
    }

    public void testWritingOneUncategorizedMovieWithoutRatings() {
        Movie starWars = new Movie.Builder("Star Wars").build();
        movieList.add(starWars);
        writer.write(movieList);
        assertEquals("<movielist><movie name=\"Star Wars\" category=\"Uncategorized\"></movie></movielist>", destination.toString());
    }

    public void testWritingOneCategorizedMovieWithoutRatings() {
        Movie starWars = new Movie.Builder("Star Wars").setCategory(Category.SCIFI).build();
        movieList.add(starWars);
        writer.write(movieList);
        assertEquals("<movielist><movie name=\"Star Wars\" category=\"Science Fiction\"></movie></movielist>", destination.toString());
    }

    public void testWritingOneCategorizedMovieWithOneRating() {
        Movie starWars = new Movie.Builder("Star Wars").setCategory(Category.SCIFI).build();
        starWars.addRating(new Rating(4, "NY Times"));
        movieList.add(starWars);
        writer.write(movieList);
        assertEquals("<movielist>" +
                "<movie name=\"Star Wars\" category=\"Science Fiction\">" +
                "<ratings>" +
                "<rating value=\"4\" source=\"NY Times\" />" +
                "</ratings>" +
                "</movie>" +
                "</movielist>", destination.toString());
    }

    public void testWritingOneCategorizedMovieWithMultipleRatings() {
        Movie starWars = new Movie.Builder("Star Wars").setCategory(Category.SCIFI).build();
        starWars.addRating(new Rating(4, "NY Times"));
        starWars.addRating(new Rating(5, "Jason"));
        movieList.add(starWars);
        writer.write(movieList);
        assertEquals("<movielist>" +
                "<movie name=\"Star Wars\" category=\"Science Fiction\">" +
                "<ratings>" +
                "<rating value=\"4\" source=\"NY Times\" />" +
                "<rating value=\"5\" source=\"Jason\" />" +
                "</ratings>" +
                "</movie>" +
                "</movielist>", destination.toString());
    }

    public void testWritingMultipleMovies() {
        Movie starWars = new Movie.Builder("Star Wars").setCategory(Category.SCIFI).build();
        starWars.addRating(new Rating(4, "NY Times"));
        starWars.addRating(new Rating(5, "Jason"));

        Movie princessBride = new Movie.Builder("The Princess Bride").setCategory(Category.FANTASY).build();
        princessBride.addRating(new Rating(5, "Kent"));
        princessBride.addRating(new Rating(5, "Ron"));

        movieList.add(starWars);
        movieList.add(princessBride);
        writer.write(movieList);

        assertEquals("<movielist>" +
                "<movie name=\"Star Wars\" category=\"Science Fiction\">" +
                "<ratings>" +
                "<rating value=\"4\" source=\"NY Times\" />" +
                "<rating value=\"5\" source=\"Jason\" />" +
                "</ratings>" +
                "</movie>" +
                "<movie name=\"The Princess Bride\" category=\"Fantasy\">" +
                "<ratings>" +
                "<rating value=\"5\" source=\"Kent\" />" +
                "<rating value=\"5\" source=\"Ron\" />" +
                "</ratings>" +
                "</movie>" +
                "</movielist>", destination.toString());
    }
}
