package graphlib;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static java.lang.String.format;
import static java.util.stream.Collectors.toSet;

/**
 * Adjacency lists graph representation. Supports self-loops and parallel edges.
 * @param <E> Edge type.
 */
public class AdjacencyListsRepresentation<E> implements Representation<E> {
    private final List<LinkedList<Edge<E>>> edges;
    private final boolean directed;
    private final int initialCapacity;
    private int numberOfEdges = 0;

    public AdjacencyListsRepresentation(boolean directed, int initialCapacity) {
        this.directed = directed;
        this.initialCapacity = initialCapacity;
        this.edges = new ArrayList<>(initialCapacity);
    }

    @Override
    public int numberOfEdges() {
        return numberOfEdges;
    }

    @Override
    public int numberOfVertices() {
        return edges.size();
    }

    @Override
    public int expectedNumberOfVertices() {
        return initialCapacity;
    }

    @Override
    public boolean isDirected() {
        return directed;
    }

    @Override
    public void addVertex(int v) {
        checkNumberOfVertices(v);
        edges.add(v, new LinkedList<>());
    }

    @Override
    public void addEdge(Edge<E> edge) {
        checkIncidentEdges(edge.start()).add(edge);
        if (edge.start() != edge.end()) checkIncidentEdges(edge.end()).add(edge);
        numberOfEdges++;
    }

    @Override
    public List<Edge<E>> getEdges(int v) {
        return checkIncidentEdges(v);
    }

    @Override
    public Set<Integer> getNeighbors(int v) {
        return checkIncidentEdges(v).stream().map(edge -> edge.other(v)).collect(toSet());
    }

    @Override
    public Set<Integer> getBackwardNeighbors(int v) {
        if (!directed) return getNeighbors(v);
        return checkIncidentEdges(v).stream().filter(edge -> edge.end() == v).map(Edge::start).collect(toSet());
    }

    @Override
    public Set<Integer> getForwardNeighbors(int v) {
        if (!directed) return getNeighbors(v);
        return checkIncidentEdges(v).stream().filter(edge -> edge.start() == v).map(Edge::end).collect(toSet());
    }

    private void checkNumberOfVertices(int v) {
        int n = edges.size();
        if (n != v) throw new IllegalStateException(format("Expected number of vertices is %s, but the actual is %s.", v, n));
    }

    private List<Edge<E>> checkIncidentEdges(int v) {
        List<Edge<E>> incident = edges.get(v);
        if (incident == null) throw new IllegalStateException(format("The list of incident edges for vertex with index %s is null.", v));
        return incident;
    }
}
