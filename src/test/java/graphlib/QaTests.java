package graphlib;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("QA Tests")
public class QaTests {
    @Test
    @DisplayName("Add vertex")
    public void addVertexTest() {
        Representation<Object> representation = new AdjacencyListsRepresentation<>(false, 16);
        TypedVertexGraph<Double> graph = new MutableTypedVertexGraph<>(representation);
        assertEquals(0, representation.numberOfVertices());
        graph.addVertex(1.0);
        assertEquals(1, representation.numberOfVertices());
        graph.addVertex(2.5);
        assertEquals(2, representation.numberOfVertices());
    }

    @Test
    @DisplayName("Add edge")
    public void addEdgeTest() {
        Representation<Object> representation = new AdjacencyListsRepresentation<>(false, 16);
        TypedVertexGraph<Integer> graph = new MutableTypedVertexGraph<>(representation);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        assertEquals(0, representation.numberOfEdges());
        graph.addEdge(1, 2);
        assertEquals(1, representation.numberOfEdges());
        graph.addEdge(2, 3);
        assertEquals(2, representation.numberOfEdges());
        graph.addEdge(2, 3);
        assertEquals(3, representation.numberOfEdges(), "Parallel edges support.");
        graph.addEdge(2, 2);
        assertEquals(4, representation.numberOfEdges(), "Self-loops support.");
    }

    @Test
    @DisplayName("Get path")
    public void getPathTest() {
        Representation<Object> representation = new AdjacencyListsRepresentation<>(false, 16);
        TypedVertexGraph<String> graph = new MutableTypedVertexGraph<>(representation);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        assertEquals(1, graph.getPath("A", "A").size());
        assertEquals(0, graph.getPath("A", "B").size());
        assertEquals(0, graph.getPath("A", "C").size());
        assertEquals(0, graph.getPath("A", "D").size());
        graph.addEdge("A", "C");
        graph.addEdge("C", "D");
        out.println(graph.getPath("A", "D"));
        assertEquals(3, graph.getPath("A", "D").size());
    }

    @Test
    @DisplayName("Get path (directed)")
    public void getDirectedPathTest() {
        Representation<Object> representation = new AdjacencyListsRepresentation<>(true, 16);
        TypedVertexGraph<Integer> graph = new MutableTypedVertexGraph<>(representation);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);
        graph.addVertex(7);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 1);
        graph.addEdge(5, 7);
        graph.addEdge(7, 3);
        assertEquals("[7, 3, 4, 5, 6, 1]", graph.getPath(7, 1).toString());
        assertEquals("[2, 3, 4, 5, 7]", graph.getPath(2, 7).toString());
    }
}
