package es.montanus.movie2see;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MovieListReader {
    public MovieList readFrom(Reader reader) throws IOException {
        final MovieList result = new MovieList();
        for (String line: new MyFileReader(reader))
            result.add(parseMovie(line));
        return result;
    }

    private Movie parseMovie(String line) {
        final String[] strings = line.split("\\|");

        Movie.Builder builder = new Movie.Builder(strings[0]).
                setCategory(Category.from(strings[1]));

        if (hasRate(strings[2])) {
            builder.setRating(Rating.from(strings[2]));

        }

        final Movie movie = builder.build();

        addRatings(movie, Integer.valueOf(strings[3]));

        return movie;
    }

    private void addRatings(Movie movie, int numberOfRatings) {
        for (int i=1; i < numberOfRatings; i++) {
            movie.addRating(new Rating(0));
        }
    }

    private boolean hasRate(String string) {
        return !"-1".equals(string);
    }

    private class MyFileReader implements Iterable<String> {
        private final BufferedReader reader;

        MyFileReader(Reader reader) {
            this.reader = new BufferedReader(reader);
        }

        @Override
        public Iterator<String> iterator() {
            return new MyFileReaderIterator();
        }

        private class MyFileReaderIterator implements Iterator<String> {
            private String next;

            MyFileReaderIterator() {
                nextLine();
            }

            @Override
            public boolean hasNext() {
                return !isEndOfFileReached();
            }

            private boolean isEndOfFileReached() {
                return next == null;
            }

            @Override
            public String next() {
                if (isEndOfFileReached()) throw new NoSuchElementException();

                String result = next;
                nextLine();
                return result;
            }

            private void nextLine() {
                try {
                    next = reader.readLine();
                }
                catch (IOException ex) {
                    throw new UncheckedIOException(ex);
                }
            }
        }
    }
}
