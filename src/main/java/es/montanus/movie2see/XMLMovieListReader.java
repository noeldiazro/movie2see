package es.montanus.movie2see;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.List;

public class XMLMovieListReader implements MovieListReader {

    private final Reader source;

    public XMLMovieListReader(Reader source) {
        this.source = source;
    }

    @Override
    public MovieList read() {
        Document document = tryBuildDocument(new SAXBuilder());
        return process(document);
    }

    private MovieList process(Document document) {
        Element movielistElement = document.getRootElement();
        List<Element> movieElements = movielistElement.getChildren();

        MovieList movieList = new MovieList();
        for (Element movieElement: movieElements)
            movieList.add(getMovieFrom(movieElement));
        return movieList;
    }

    private Movie getMovieFrom(Element movieElement) {
        final String name = movieElement.getAttributeValue("name");
        final Category category = Category.from(movieElement.getAttributeValue("category"));
        final Movie movie = new Movie.Builder(name).setCategory(category).build();

        if (hasRatings(movieElement)) {
            addRatings(movieElement, movie);
        }
        return movie;
    }

    private boolean hasRatings(Element movieElement) {
        return movieElement.getChild("ratings") != null;
    }

    private void addRatings(Element movieElement, Movie movie) {
        for (Element ratingElement: movieElement.getChild("ratings").getChildren())
            movie.addRating(getRatingFrom(ratingElement));
    }

    private Rating getRatingFrom(Element ratingElement) {
        final int value = Integer.valueOf(ratingElement.getAttributeValue("value"));
        final String source = ratingElement.getAttributeValue("source");
        return new Rating(value, source);
    }

    private Document tryBuildDocument(SAXBuilder builder) {
        try {
            return builder.build(source);
        }
        catch (JDOMException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
