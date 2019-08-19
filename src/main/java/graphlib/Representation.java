package graphlib;

import java.util.List;
import java.util.Set;

/**
 * Generic interface for graph representation (the vertices are enumerated starting from 0).
 * @param <E> Edge type.
 */
public interface Representation<E> {
    /**
     * Number of edges.
     */
    int numberOfEdges();

    /**
     * Expected number of vertices.
     */
    int numberOfVertices();

    /**
     * Expected number of vertices.
     */
    int expectedNumberOfVertices();

    /**
     * True iff the represented graph is directed.
     */
    boolean isDirected();

    /**
     * Adds new vertex to the representation.
     * @param v Vertex index.
     */
    void addVertex(int v);

    /**
     * Adds new edge to the representation.
     * @param e New edge.
     */
    void addEdge(Edge<E> e);

    /**
     * Returns all edges (both outgoing and ingoing) for a given vertex index.
     * @param v Index of the vertex.
     * @return List of edges adjoint to the {@code v}-th vertex.
     */
    List<Edge<E>> getEdges(int v);

    /**
     * Returns the indices of all vertices adjoint to the {@code v}-th vertex.
     * @param v Index of the vertex.
     * @return Set of neighbors.
     */
    Set<Integer> getNeighbors(int v);

    /**
     * Returns the indices of all direct predecessors of the {@code v}-th vertex.
     * @param v Index of the vertex.
     * @return Set of backward neighbors.
     */
    Set<Integer> getBackwardNeighbors(int v);

    /**
     * Returns the indices of all direct successors of the {@code v}-th vertex.
     * @param v Index of the vertex.
     * @return Set of forward neighbors.
     */
    Set<Integer> getForwardNeighbors(int v);
}
