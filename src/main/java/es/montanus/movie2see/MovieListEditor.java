package es.montanus.movie2see;

public class MovieListEditor {
    public MovieListEditor(MovieList movieList, MovieListEditorView view) {
        view.setMovies(movieList.toVector());
    }
}
