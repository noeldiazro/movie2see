package es.montanus.movie2see;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class SwingMovieListEditorView extends JFrame implements MovieListEditorView {

    private JList movieList;
    private MovieListEditor editor;
    private JTextField movieField;

    public SwingMovieListEditorView() {
        init();
        setVisible(true);
    }

    public void setMovies(Vector<Movie> movies) {
        movieList.setListData(movies);
    }

    public String getNewName() {
        return movieField.getText();
    }

    public void setEditor(MovieListEditor editor) {
        this.editor = editor;
    }

    private void init() {
        setTitle();
        setLayout();
        initList();
        initField();
        initAddButton();
        pack();
    }

    private void setTitle() {
        setTitle("Movie List");
    }

    private void setLayout() {
        getContentPane().setLayout(new FlowLayout());
    }

    private void initList() {
        movieList = new JList();
        JScrollPane scroller = new JScrollPane(movieList,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        getContentPane().add(scroller);
    }

    private void initField() {
        movieField = new JTextField(16);
        getContentPane().add(movieField);
    }

    private void initAddButton() {
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editor.add();
            }
        });
        getContentPane().add(addButton);
    }

}
