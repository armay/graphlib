package graphlib;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static java.util.Collections.emptyList;

/**
 * Simple DFS implementation.
 * @param <E> Edge type.
 */
public class DepthFirstSearch<E> extends AbstractPathFinder<E> {
    @Override
    public List<Integer> getPath(int u, int v, Representation<E> graph) {
        int n = graph.numberOfVertices();
        int[] edgeTo = new int[n];
        boolean[] marked = new boolean[n];
        Deque<Integer> stack = new LinkedList<>();
        marked[u] = true;
        stack.addFirst(u);
        search: while (!stack.isEmpty()) {
            int head = stack.removeFirst();
            if (head == v) break;
            Set<Integer> neighbors = graph.getForwardNeighbors(head);
            for (int neighbor : neighbors) {
                if (!marked[neighbor]) {
                    edgeTo[neighbor] = head;
                    marked[neighbor] = true;
                    if (neighbor == v) break search;
                    stack.push(neighbor);
                }
            }
        }
        if (marked[v]) return buildPath(u, v, edgeTo);
        else return emptyList();
    }
}
