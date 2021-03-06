package es.montanus.movie2see;

public class Rating implements Comparable<Rating> {

    private final int value;
    private final String source;

    public Rating(int value) {
        this(value, "Anonymous");
    }

    public Rating(int value, String source) {
        this.value = value;
        this.source = source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rating rating = (Rating) o;

        return value == rating.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    public int getValue() {
        return value;
    }

    public static Rating from(String string) {
        return new Rating(Integer.valueOf(string));
    }

    @Override
    public int compareTo(Rating that) {
        return this.value - that.value;
    }

    @Override
    public String toString() {
        return String.format("Value: %d", getValue());
    }

    public String getSource() {
        return source;
    }
}
