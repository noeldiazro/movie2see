package es.montanus.movie2see;

public class App {
    public static void main(String[] args) {
        MovieList movies = new MovieList();
        movies.add(new Movie("Star Wars"));
        movies.add(new Movie("Star Trek"));
        movies.add(new Movie("Stargate"));

        SwingMovieListEditorView view = new SwingMovieListEditorView();
        MovieListEditor editor = new MovieListEditor(movies, view);
    }
}
