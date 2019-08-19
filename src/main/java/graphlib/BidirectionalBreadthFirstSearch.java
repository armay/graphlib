package graphlib;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import static java.util.Collections.emptyList;

/**
 * Simple bidirectional BFS implementation.
 * @param <E> Edge type.
 */
public class BidirectionalBreadthFirstSearch<E> extends AbstractPathFinder<E> {
    @Override
    public List<Integer> getPath(int u, int v, Representation<E> graph) {
        int n = graph.numberOfVertices();
        int[] edgeForward = new int[n];
        int[] edgeBackward = new int[n];
        boolean[] markedForward = new boolean[n];
        boolean[] markedBackward = new boolean[n];
        Queue<Integer> queueForward = new LinkedList<>();
        Queue<Integer> queueBackward = new LinkedList<>();

        markedForward[u] = true;
        queueForward.add(u);
        markedBackward[v] = true;
        queueBackward.add(v);
        int intersection = -1;

        search: while (!queueForward.isEmpty() && !queueBackward.isEmpty()) {
            int mf = queueForward.size();
            for (int i = 0; i < mf; i++) {
                int head = queueForward.remove();
                if (markedBackward[head]) {
                    intersection = head;
                    break search;
                }
                Set<Integer> neighbors = graph.getForwardNeighbors(head);
                for (int neighbor : neighbors) {
                    if (!markedForward[neighbor]) {
                        edgeForward[neighbor] = head;
                        markedForward[neighbor] = true;
                        if (markedBackward[neighbor]) {
                            intersection = neighbor;
                            break search;
                        }
                        queueForward.add(neighbor);
                    }
                }
            }
            int mb = queueBackward.size();
            for (int i = 0; i < mb; i++) {
                int tail = queueBackward.remove();
                if (markedForward[tail]) {
                    intersection = tail;
                    break search;
                }
                Set<Integer> neighbors = graph.getBackwardNeighbors(tail);
                for (int neighbor : neighbors) {
                    if (!markedBackward[neighbor]) {
                        edgeBackward[tail] = neighbor;
                        markedBackward[neighbor] = true;
                        if (markedForward[neighbor]) {
                            intersection = neighbor;
                            break search;
                        }
                        queueBackward.add(neighbor);
                    }
                }
            }
        }

        if (intersection != -1) {
            LinkedList<Integer> path = buildPath(u, intersection, edgeForward);
            if (intersection != v) {
                path.removeLast();
                path.addAll(buildPath(intersection, v, edgeBackward));
            }
            return path;
        } else return emptyList();
    }
}
