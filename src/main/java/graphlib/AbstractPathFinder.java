package graphlib;

import java.util.LinkedList;

public abstract class AbstractPathFinder<E> implements PathFinder<E> {
    protected LinkedList<Integer> buildPath(int u, int v, int[] edgeTo) {
        LinkedList<Integer> path = new LinkedList<>();
        for (int w = v; w != u; w = edgeTo[w]) path.addFirst(w);
        path.addFirst(u);
        return path;
    }
}
