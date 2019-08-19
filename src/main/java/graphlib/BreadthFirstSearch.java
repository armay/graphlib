package graphlib;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import static java.util.Collections.emptyList;

/**
 * Simple BFS implementation.
 * @param <E> Edge type.
 */
public class BreadthFirstSearch<E> extends AbstractPathFinder<E> {
    @Override
    public List<Integer> getPath(int u, int v, Representation<E> graph) {
        int n = graph.numberOfVertices();
        int[] edgeTo = new int[n];
        boolean[] marked = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        marked[u] = true;
        queue.add(u);
        search: while (!queue.isEmpty()) {
            int head = queue.remove();
            if (head == v) break;
            Set<Integer> neighbors = graph.getForwardNeighbors(head);
            for (int neighbor : neighbors) {
                if (!marked[neighbor]) {
                    edgeTo[neighbor] = head;
                    marked[neighbor] = true;
                    if (neighbor == v) break search;
                    queue.add(neighbor);
                }
            }
        }
        if (marked[v]) return buildPath(u, v, edgeTo);
        else return emptyList();
    }
}
