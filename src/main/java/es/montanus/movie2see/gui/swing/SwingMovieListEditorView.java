package es.montanus.movie2see.gui.swing;

import es.montanus.movie2see.Category;
import es.montanus.movie2see.Movie;
import es.montanus.movie2see.MovieList;
import es.montanus.movie2see.gui.MovieListEditor;
import es.montanus.movie2see.gui.MovieListEditorView;
import org.omg.CORBA.CODESET_INCOMPATIBLE;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class SwingMovieListEditorView extends JFrame implements MovieListEditorView {

    private JList movieList;
    private MovieListEditor editor;
    private JTextField movieField;
    private JComboBox ratingField;
    private JComboBox<Category> categoryField;
    private JFileChooser fileChooser;

    private SwingMovieListEditorView() {
    }

    public void setMovies(Vector<Movie> movies) {
        movieList.setListData(movies);
    }

    @Override
    public String getNameField() {
        return movieField.getText();
    }

    public void setEditor(MovieListEditor editor) {
        this.editor = editor;
    }

    @Override
    public void setNameField(String string) {
        movieField.setText(string);
    }

    @Override
    public void duplicateException(String string) {
        JOptionPane.showMessageDialog(this,
                "That would result in a duplicate Movie.",
                "Duplicate Movie",
                JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void setRatingField(int value) {
        ratingField.setSelectedIndex(value);
    }

    @Override
    public int getRatingField() {
        return ratingField.getSelectedIndex();
    }

    @Override
    public void setCategoryField(Category category) {
        categoryField.setSelectedItem(category);
    }

    @Override
    public Category getCategoryField() {
        return (Category)categoryField.getSelectedItem();
    }

    @Override
    public File chooseFile(String pattern) {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
            return fileChooser.getSelectedFile();
        else
            return null;
    }

    private void init() {
        setTitle();
        setLayout();
        fileChooser = new JFileChooser();
        setJMenuBar(initMenuBar());
        getContentPane().add(initListPane());
        getContentPane().add(initDetailPane());
        getContentPane().add(initButtonPane());
        pack();
    }

    private void setTitle() {
        setTitle("Movie List");
    }

    private void setLayout() {
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    }

    private JMenuBar initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        final JMenuItem saveAsItem = new JMenuItem("Save As...");
        saveAsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    editor.saveAs();
                } catch (IOException ex) {
                    // TODO: deal with this
                }
            }
        });
        fileMenu.add(saveAsItem);
        menuBar.add(fileMenu);
        return menuBar;
    }

    private JPanel initListPane() {
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));
        listPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        listPane.add(initCategoryFilterField());
        listPane.add(getVerticalSeparator(5));
        listPane.add(initList());
        return listPane;
    }

    private Component initCategoryFilterField() {
        final JComboBox<Category> categoryFilterField = new JComboBox<Category>(Category.categories());
        categoryFilterField.setSelectedItem(Category.ALL);
        categoryFilterField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editor.filterOn((Category) categoryFilterField.getSelectedItem());
            }
        });
        return categoryFilterField;
    }

    private Component initList() {
        movieList = new JList();
        movieList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        movieList.setCellRenderer(new CustomMovieListRenderer());
        movieList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                editor.select(movieList.getSelectedIndex());
            }
        });
        JScrollPane scroller = new JScrollPane(movieList,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        return scroller;
    }

    private Component initDetailPane() {
        JPanel detailPane = new JPanel();
        detailPane.setLayout(new BoxLayout(detailPane, BoxLayout.Y_AXIS));
        detailPane.setBorder(BorderFactory.createEmptyBorder(1, 10, 10, 10));
        detailPane.add(initNameField());
        detailPane.add(getVerticalSeparator(5));
        detailPane.add(initRatingCombo());
        detailPane.add(getVerticalSeparator(5));
        detailPane.add(initCategoryField());
        return detailPane;
    }

    private Component getVerticalSeparator(int height) {
        return Box.createRigidArea(new Dimension(0, height));
    }

    private Component initNameField() {
        movieField = new JTextField(16);
        getContentPane().add(movieField);
        return movieField;
    }

    private Component initRatingCombo() {
        ratingField = new JComboBox(new String[] {"-", "0", "1", "2", "3", "4", "5"});
        getContentPane().add(ratingField);
        return ratingField;
    }

    private Component initCategoryField() {
        categoryField = new JComboBox<Category>(Category.categories());
        getContentPane().add(categoryField);
        return categoryField;
    }

    private Component initButtonPane() {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(1, 10 ,10, 10));
        buttonPane.add(initAddButton());
        buttonPane.add(getHorizontalSeparator(5));
        buttonPane.add(initUpdateButton());
        return buttonPane;
    }

    private Component getHorizontalSeparator(int width) {
        return Box.createRigidArea(new Dimension(width, 0));
    }

    private Component initAddButton() {
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editor.add();
            }
        });
        getContentPane().add(addButton);
        return addButton;
    }

    private Component initUpdateButton() {
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editor.update();
            }
        });
        getContentPane().add(updateButton);
        return updateButton;
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
