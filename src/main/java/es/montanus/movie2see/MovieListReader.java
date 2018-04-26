package es.montanus.movie2see;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class MovieListReader {
    public MovieList readFrom(Reader reader) throws IOException {
        final MovieList result = new MovieList();
        try (BufferedReader bufferedReader = new BufferedReader(reader)) {
            while (true) {
                final String line = bufferedReader.readLine();
                if (isEndOfFileReached(line))
                    break;
                result.add(parseMovie(line));
            }
        }
        return result;
    }

    private boolean isEndOfFileReached(String line) {
        return line == null;
    }

    private Movie parseMovie(String line) {
        final String[] strings = line.split("\\|");

        Movie.Builder builder = new Movie.Builder(strings[0]).
                setCategory(Category.from(strings[1]));

        if (hasRate(strings[2]))
            builder.setRating(Rating.from(strings[2]));

        return builder.build();
    }

    private boolean hasRate(String string) {
        return !"-1".equals(string);
    }
}
