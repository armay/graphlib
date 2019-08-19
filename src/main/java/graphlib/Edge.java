package graphlib;

import static java.lang.String.format;

/**
 * Generic edge data class. Can hold value of user defined type (e.g. weight).
 * The same class is used for both directed and undirected graphs.
 * @param <E> Type of value associated with the edge.
 */
public class Edge<E> {
    private final int u;
    private final int v;
    private final E value;

    public final int start() {
        return u;
    }

    public final int end() {
        return v;
    }

    public final int either() {
        return u;
    }

    public final int other(int w) {
        requireStartsOrEndsWith(w);
        if (w == u) return v;
        else return u;
    }

    public final E value() {
        return value;
    }

    public Edge(int u, int v, E value) {
        this.u = u;
        this.v = v;
        this.value = value;
    }

    private void requireStartsOrEndsWith(int w) {
        if (w != u && w != v) throw new IllegalArgumentException(format(
                "Edge %s neither starts nor ends with vertex %s", this.toString(), w
        ));
    }
}
