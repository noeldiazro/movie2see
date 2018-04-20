package es.montanus.movie2see.gui;

import es.montanus.movie2see.DuplicateMovieException;
import es.montanus.movie2see.Movie;
import es.montanus.movie2see.MovieList;
import es.montanus.movie2see.Rating;

public class MovieListEditor {

    private final MovieListEditorView view;
    private final MovieList movieList;
    private Movie selectedMovie;

    public MovieListEditor(MovieList movieList, MovieListEditorView view) {
        this.movieList = movieList;
        this.view = view;
        updateMovieList();
        view.setEditor(this);
    }

    public void add() {
        final String nameField = view.getNameField();
        final int ratingField = view.getRatingField();
        try {
            movieList.add(makeMovie(nameField, ratingField));
            updateMovieList();
        }
        catch (DuplicateMovieException ex) {
            view.duplicateException(nameField);
        }
    }

    private Movie makeMovie(String nameField, int ratingField) {
        return new Movie.Builder(nameField).setRating(makeRatingFromField(ratingField)).build();
    }

    public void select(int index) {
        if (index == -1)
            selectedMovie = null;
        else {
            selectedMovie = movieList.get(index);
            view.setNameField(selectedMovie.getName());
            view.setRatingField(getRatingValue(selectedMovie));
            view.setCategoryField(selectedMovie.getCategory());
        }
    }

    private int getRatingValue(Movie movie) {
        if (!movie.isRated()) return 0;

        return movie.getRating().getValue() + 1;
    }

    public void update() {
        if (selectedMovie == null) return;

        final String newName = view.getNameField();
        final int ratingField = view.getRatingField();
        try {
            movieList.rename(selectedMovie, newName);
            selectedMovie.setRating(makeRatingFromField(ratingField));
            updateMovieList();
        }
        catch (DuplicateMovieException ex) {
            view.duplicateException(newName);
        }
    }

    private Rating makeRatingFromField(int ratingField) {
        return ratingField == 0 ? null : new Rating(ratingField - 1);
    }

    private void updateMovieList() {
        view.setMovies(movieList.toVector());
    }
}
