package es.montanus.movie2see;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class SwingMovieListEditorView extends JFrame implements MovieListEditorView {

    private JList movieList;

    public SwingMovieListEditorView() {
        init();
        setVisible(true);
    }

    public void setMovies(Vector<Movie> movies) {
        movieList.setListData(movies);
    }

    private void init() {
        setTitle("Movie List");
        getContentPane().setLayout(new FlowLayout());
        movieList = new JList();
        JScrollPane scroller = new JScrollPane(movieList,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        getContentPane().add(scroller);
        pack();
    }
}
