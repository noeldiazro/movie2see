package es.montanus.movie2see;

public class MovieListEditor {

    private final MovieListEditorView view;
    private final MovieList movieList;
    private Movie selectedMovie;

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

    public void select(int index) {
        if (index == -1)
            selectedMovie = null;
        else {
            selectedMovie = movieList.get(index);
            view.setNewName(selectedMovie.getName());
        }
    }

    public void update() {
        if (selectedMovie != null) {
            selectedMovie.rename(view.getNewName());
            view.setMovies(movieList.toVector());
        }
    }
}
