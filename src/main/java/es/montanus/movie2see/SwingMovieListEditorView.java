package es.montanus.movie2see;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class SwingMovieListEditorView extends JFrame implements MovieListEditorView {

    private JList movieList;
    private MovieListEditor editor;
    private JTextField movieField;

    private SwingMovieListEditorView() {
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

    @Override
    public void setNewName(String newName) {
        movieField.setText(newName);
    }

    @Override
    public void duplicateException(String string) {
        JOptionPane.showMessageDialog(this,
                "That would result in a duplicate Movie.",
                "Duplicate Movie",
                JOptionPane.ERROR_MESSAGE);
    }

    private void init() {
        setTitle();
        setLayout();
        initList();
        initField();
        initAddButton();
        initUpdateButton();
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
        movieList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        movieList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                editor.select(movieList.getSelectedIndex());
            }
        });
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

    private void initUpdateButton() {
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editor.update();
            }
        });
        getContentPane().add(updateButton);
    }

    public static SwingMovieListEditorView start() {
        SwingMovieListEditorView window = new SwingMovieListEditorView();
        window.init();
        window.setVisible(true);
        return window;
    }

    public static void main(String[] args) {
        SwingMovieListEditorView window = SwingMovieListEditorView.start();
        MovieList list = new MovieList();
        new MovieListEditor(list, window);
    }

}
