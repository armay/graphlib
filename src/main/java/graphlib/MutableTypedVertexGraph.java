package graphlib;

import java.util.List;

/**
 * Implementation for graphs without values for edges.
 * @param <V> Vertex type.
 */
public class MutableTypedVertexGraph<V> implements TypedVertexGraph<V> {
    private static final int DEFAULT_CAPACITY = 16;

    public MutableTypedVertexGraph() {
        this(false);
    }

    public MutableTypedVertexGraph(boolean directed) {
        this(directed, DEFAULT_CAPACITY);
    }

    public MutableTypedVertexGraph(boolean directed, int initialCapacity) {
        Representation<Object> representation = new AdjacencyListsRepresentation<Object>(directed, initialCapacity);
        PathFinder<Object> pathFinder = new BidirectionalBreadthFirstSearch<>();
        delegate = new GenericGraph<>(representation, pathFinder);
    }

    public MutableTypedVertexGraph(Representation<Object> representation) {
        this(representation, new BidirectionalBreadthFirstSearch<>());
    }

    public MutableTypedVertexGraph(Representation<Object> representation, PathFinder<Object> pathFinder) {
        delegate = new GenericGraph<>(representation, pathFinder);
    }

    private Graph<V, Object> delegate;

    private static final Object PRESENT = new Object();

    @Override
    public void addVertex(V vertex) {
        delegate.addVertex(vertex);
    }

    @Override
    public void addEdge(V start, V end) {
        delegate.addEdge(start, end, PRESENT);
    }

    @Override
    public List<V> getPath(V start, V end) {
        return delegate.getPath(start, end);
    }

    @Override
    public List<V> getPath(V start, V end, PathFinder<Object> pathFinder) {
        return delegate.getPath(start, end, pathFinder);
    }
}
