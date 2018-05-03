package es.montanus.movie2see;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.Writer;

public class XMLMovieListWriter implements MovieListWriter {

    private final Writer destination;

    public XMLMovieListWriter(Writer destination) {
        this.destination = destination;
    }

    public void write(MovieList movieList) throws UncheckedIOException {
        try {
            destination.write("<movielist>");
            for (Movie movie: movieList)
                destination.write(getXMLMovieRepresentation(movie));
            destination.write("</movielist>");
            destination.flush();
        }
        catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    private String getXMLMovieRepresentation(Movie movie) {
        StringBuilder result = new StringBuilder();
        result.append(getMovieOpeningTag(movie));
        result.append(getXMLRatingsRepresentation(movie));
        result.append(getMovieClosingTag());
        return result.toString();
    }

    private String getMovieOpeningTag(Movie movie) {
        return String.format("<movie name=\"%s\" category=\"%s\">", movie.getName(), movie.getCategory().toString());
    }

    private String getXMLRatingsRepresentation(Movie movie) {
        StringBuilder result = new StringBuilder();
        if (movie.isRated()) {
            result.append("<ratings>");
            for (Rating rating: movie)
                result.append(getXMLRatingRepresentation(rating));
            result.append("</ratings>");
        }
        return result.toString();
    }

    private String getXMLRatingRepresentation(Rating rating) {
        return String.format("<rating value=\"%d\" source=\"%s\" />", rating.getValue(), rating.getSource());
    }

    private String getMovieClosingTag() {
        return "</movie>";
    }
}
