package graphlib;

import java.util.List;

/**
 * Path searching function.
 * @param <E> Edge type.
 */
@FunctionalInterface
public interface PathFinder<E> {
    List<Integer> getPath(int u, int v, Representation<E> graph);
}
