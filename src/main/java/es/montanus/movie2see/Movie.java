package es.montanus.movie2see;

public class Movie {
    public static final String NULL_NAME_MSG = "Movie name can't be null";
    public static final String EMPTY_NAME_MSG = "Movie name can't be empty";
    public static final String NULL_CATEGORY_MSG = "Category can't be null";

    private String name;
    private Rating rating;
    private Category category;

    private Movie(Builder builder) {
        setName(builder.name);
        this.rating = builder.rating;
        setCategory(builder.category);
    }

    public void setCategory(Category category) {
        checkCategory(category);
        this.category = category;
    }

    private void checkCategory(Category category) {
        if (!isValidCategory(category))
            throw new IllegalArgumentException(NULL_CATEGORY_MSG);
    }

    private boolean isValidCategory(Category category) {
        return category != null;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return name.equals(movie.getName());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public void rename(String newName) {
        setName(newName);
    }

    private void setName(String name) {
        checkNull(name);
        checkEmpty(name);
        this.name = name;
    }

    private void checkNull(String name) throws IllegalArgumentException {
        if (name == null)
            throw new IllegalArgumentException(NULL_NAME_MSG);
    }

    private void checkEmpty(String name) {
        if (name.isEmpty())
            throw new IllegalArgumentException(EMPTY_NAME_MSG);
    }

    public boolean isRated() {
        return rating != null;
    }

    public Rating getRating() {
        if (!isRated())
            throw new UnratedException(getName());
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isOfCategory(Category category) {
        return category == Category.ALL || getCategory() == category;
    }

    public static class Builder {

        private final String name;

        private Rating rating = null;
        private Category category = Category.UNCATEGORIZED;

        public Builder(String name) {
            this.name = name;
        }

        public Builder setRating(Rating rating) {
            this.rating = rating;
            return this;
        }

        public Builder setCategory(Category category) {
            this.category = category;
            return this;
        }
        public Movie build() {
            return new Movie(this);
        }
    }
}
