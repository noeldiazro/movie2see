package es.montanus.movie2see.gui.swing;

import es.montanus.movie2see.Movie;

import javax.swing.*;
import java.awt.*;

public class CustomMovieListRenderer extends DefaultListCellRenderer {

    private static String[] iconPaths =
            {
                    "/images/middle.gif",
                    "/images/zero-stars.png",
                    "/images/one-star.png",
                    "/images/two-stars.png",
                    "/images/three-stars.png",
                    "/images/four-stars.png",
                    "/images/five-stars.png"
            };

    @Override
    public Component getListCellRendererComponent(JList<?> list,
                                                  Object value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        Movie movieToRender = (Movie)value;
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }
        else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        setText(movieToRender.getName());
        setIcon(movieToRender);

        return this;
    }

    private void setIcon(Movie movieToRender) {
        if (movieToRender.isRated())
            setIcon(new ImageIcon(getClass().getResource(iconPaths[movieToRender.getRating().getValue() + 1])));
        else
            setIcon(new ImageIcon(getClass().getResource(iconPaths[0])));
    }
}
