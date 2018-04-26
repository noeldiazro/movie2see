package es.montanus.movie2see;

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

    public static final String NO_CATEGORY_MSG = "There is no category for given string";

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

    public static Category from(String string) {
        for (Category category: categories())
            if (category.toString().equals(string))
                return category;
        throw new IllegalArgumentException(NO_CATEGORY_MSG);
    }
}
