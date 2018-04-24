package es.montanus.movie2see;

import java.util.HashMap;

public class Category {

    public static final Category UNCATEGORIZED = new Category("Uncategorized");
    public static final Category SCIFI = new Category("Science Fiction");
    public static final Category HORROR = new Category("Horror");
    public static final Category COMEDY = new Category("Comedy");
    public static final Category WESTERN = new Category("Western");
    public static final Category DRAMA = new Category("Drama");
    public static final Category FANTASY = new Category("Fantasy");
    public static final Category KIDS = new Category("Kids");
    public static final Category ADULT = new Category("Adult");
    public static final Category MYSTERY = new Category("Mystery");
    public static final Category THRILLER = new Category("Thriller");
    public static final Category ALL = new Category("All");

    private final String name;

    private Category(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Category[] categories() {
        return new Category[] {
                ALL,
                UNCATEGORIZED,
                SCIFI,
                HORROR,
                COMEDY,
                WESTERN,
                DRAMA,
                FANTASY,
                KIDS,
                ADULT,
                MYSTERY,
                THRILLER
        };
    }
}
