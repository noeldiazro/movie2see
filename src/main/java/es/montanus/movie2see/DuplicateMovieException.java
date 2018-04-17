package es.montanus.movie2see;

public class DuplicateMovieException extends RuntimeException {

    public static final String MOVIE_PRESENT_MSG = "%s was already in the list";

    DuplicateMovieException(Movie movie) {
        super(makeMessage(movie));
    }

    private static String makeMessage(Movie movie) {
        return String.format(MOVIE_PRESENT_MSG, movie.getName());
    }
}
