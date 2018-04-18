package es.montanus.movie2see;

public class UnratedException extends RuntimeException {

    public static final String MSG_FORMAT = "%s is not rated";

    public UnratedException(String name) {
        super(String.format(MSG_FORMAT, name));
    }
}
