package commons;

/**
 * A simple pair of elements.
 * TODO Might be replaced by a library class.
 * @param <A> the type of the first element
 * @param <B> the type of the second element
 * @param elem1 the first element
 * @param elem2 the second element
 */
public record Pair<A, B>(A elem1, B elem2) {
}
