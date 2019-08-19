package graphlib;

import java.util.List;

/**
 * Generic graph interface. Does not support {@code null} and duplicate vertices.
 * @param <V> Vertex type
 * @param <E> Edge type
 */
public interface Graph<V, E> {
    /**
     * Adds {@code vertex} to the graph.
     * @param vertex New vertex.
     * @throws IllegalArgumentException if {@code vertex} is null or an equal vertex already exists.
     */
    void addVertex(V vertex);

    /**
     * Adds an edge connecting {@code start} to {@code end}.
     * @param start Start vertex.
     * @param end End vertex.
     * @param value Edge value.
     * @throws IllegalArgumentException if either {@code start} or {@code end} is null or absent.
     */
    void addEdge(V start, V end, E value);

    /**
     * Finds a path connecting {@code start} to {@code end}. Path is not necessarily optimal.
     * @param start Start vertex.
     * @param end End vertex.
     * @return A list of vertices on the path from {@code start} to {@code end}.
     * @throws IllegalArgumentException if either {@code start} or {@code end} is null or absent.
     */
    List<V> getPath(V start, V end);

    /**
     * Finds a path connecting {@code start} to {@code end} using explicitly defined {@code search} function.
     * @param start Start vertex.
     * @param end End vertex.
     * @param pathFinder Search function.
     * @return A list of vertices on the path from {@code start} to {@code end}.
     * @throws IllegalArgumentException if either {@code start} or {@code end} is null or absent.
     */
    List<V> getPath(V start, V end, PathFinder<E> pathFinder);
}
