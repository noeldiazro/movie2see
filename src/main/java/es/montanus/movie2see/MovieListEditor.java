package es.montanus.movie2see;

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
        String newName = view.getNewName();
        try {
            movieList.add(new Movie(newName));
            updateMovieList();
        }
        catch (DuplicateMovieException ex) {
            view.duplicateException(newName);
        }
    }

    public void select(int index) {
        if (index == -1)
            selectedMovie = null;
        else {
            selectedMovie = movieList.get(index);
            view.setNewName(selectedMovie.getName());
        }
    }

    public void update() {
        if (selectedMovie == null) return;

        final String newName = view.getNewName();
        try {
            movieList.rename(selectedMovie, newName);
            updateMovieList();
        }
        catch (DuplicateMovieException ex) {
            view.duplicateException(newName);
        }
    }

    private void updateMovieList() {
        view.setMovies(movieList.toVector());
    }
}
