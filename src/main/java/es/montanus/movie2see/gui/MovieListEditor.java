package es.montanus.movie2see.gui;

import es.montanus.movie2see.*;

import java.io.*;
import java.util.Comparator;

public class MovieListEditor {

    private final MovieListEditorView view;
    private MovieList movieList;
    private Movie selectedMovie;
    private MovieList filteredList;
    private Category filterCategory = Category.ALL;
    private File outputFile;

    public MovieListEditor(MovieList movieList, MovieListEditorView view) {
        this.movieList = movieList;
        this.view = view;
        this.filteredList = movieList;
        this.view.setMovies(filteredList.toVector());
        view.setEditor(this);
    }

    public void add() {
        final String name = view.getNameField();
        try {
            movieList.add(makeMovie(name));
            updateMovieList();
        }
        catch (DuplicateMovieException ex) {
            view.duplicateException(name);
        }
    }

    private Movie makeMovie(String name) {

        return new Movie.Builder(name).
                setRating(makeRatingFromField(view.getRatingField())).
                setCategory(view.getCategoryField()).
                build();
    }

    public void select(int index) {
        if (index == -1)
            selectedMovie = null;
        else {
            selectedMovie = filteredList.get(index);
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
        try {
            movieList.rename(selectedMovie, newName);
            selectedMovie.setRating(makeRatingFromField(view.getRatingField()));
            selectedMovie.setCategory(view.getCategoryField());
            updateMovieList();
        }
        catch (DuplicateMovieException ex) {
            view.duplicateException(newName);
        }
    }

    private Rating makeRatingFromField(int ratingField) {
        return ratingField == 0 ? null : new Rating(ratingField - 1);
    }

    public void filterOn(Category category) {
        filterCategory = category;
        updateMovieList();
    }

    private void updateMovieList() {
        filteredList = movieList.filterByCategory(filterCategory);
        view.setMovies(filteredList.toVector());
    }

    public boolean saveAs() throws IOException {
        outputFile = view.chooseSaveFile("*.dat");
        return save();
    }

    public boolean save() throws IOException {
        if (isFileChosen(outputFile))
            tryListWriting(outputFile);
        return isFileChosen(outputFile);
    }

    private boolean isFileChosen(File file) {
        return file != null;
    }

    private void tryListWriting(File outputFile) throws IOException {
        try (FileWriter writer = new FileWriter(outputFile)) {
            MovieListWriter movieListWriter = new XMLMovieListWriter(writer);
            movieListWriter.write(movieList);
        }
    }

    public void open() throws IOException {
        final FileReader source = new FileReader(view.chooseOpenFile("*.dat"));
        movieList = new XMLMovieListReader(source).read();
        updateMovieList();
    }

    public void sortUsing(Comparator<Movie> comparator) {
        movieList.sortUsing(comparator);
        updateMovieList();
    }
}
