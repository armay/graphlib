package graphlib;

import java.util.*;

import static java.lang.String.format;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

/**
 * Generic graph implementation.
 * @param <V> Vertice type.
 * @param <E> Edge type.
 */
public class GenericGraph<V, E> implements Graph<V, E> {
    private final Map<V, Integer> indices;
    private final List<V> vertices;
    private final Representation<E> representation;
    private PathFinder<E> defaultPathFinder;

    public GenericGraph(Representation<E> representation, PathFinder<E> defaultPathFinder) {
        int n = representation.expectedNumberOfVertices();
        indices = new HashMap<>(n, 1f);
        vertices = new ArrayList<>(n);
        this.representation = representation;
        this.defaultPathFinder = defaultPathFinder;
    }

    @Override
    public final void addVertex(V vertex) {
        requireNotNull(vertex);
        requireIsAbsent(vertex);
        int v = vertices.size();
        indices.put(vertex, v);
        vertices.add(vertex);
        representation.addVertex(v);
    }

    @Override
    public final void addEdge(V start, V end, E value) {
        requireNotNull(start);
        requireNotNull(end);
        int startIndex = requireExists(start);
        int endIndex = requireExists(end);
        representation.addEdge(new Edge<>(startIndex, endIndex, value));
    }

    @Override
    public final List<V> getPath(V start, V end) {
        return getPath(start, end, defaultPathFinder);
    }

    @Override
    public List<V> getPath(V start, V end, PathFinder<E> pathFinder) {
        requireNotNull(start);
        requireNotNull(end);
        int u = requireExists(start);
        int v = requireExists(end);
        if (u == v) return singletonList(start);
        return pathFinder.getPath(u, v, representation).stream().map(vertices::get).collect(toList());
    }

    private int indexOf(V vertex) {
        Integer index = indices.get(vertex);
        if (index == null) return -1;
        else return index;
    }

    private void requireNotNull(V vertex) {
        if (vertex == null) throw new IllegalArgumentException("Null vertices are not supported.");
    }

    private int requireExists(V vertex) {
        int index = indexOf(vertex);
        if (index == -1) throw new IllegalArgumentException(format("Vertex not found: %s.", vertex));
        return index;
    }

    private void requireIsAbsent(V vertex) {
        int index = indexOf(vertex);
        if (index != -1) throw new IllegalArgumentException(format("Duplicate vertex found: %s.", vertices.get(index).toString()));
    }
}
