package es.montanus.movie2see;

import java.util.Vector;

public class MovieListEditor {

    private final MovieListEditorView view;
    private final MovieList movieList;

    public MovieListEditor(MovieList movieList, MovieListEditorView view) {
        this.movieList = movieList;
        this.view = view;
        this.view.setMovies(this.movieList.toVector());
        view.setEditor(this);
    }

    public void add() {
        String newName = view.getNewName();
        movieList.add(new Movie(newName));
        view.setMovies(movieList.toVector());
    }
}
