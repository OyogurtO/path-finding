package com.github.oyogurto.graph;

import java.util.*;

/**
 * @author Jingchun.Zhou
 * @since 8/30/2022
 */
public abstract class AbstractGraph<N, E extends Edge<N>> extends AbstractCollection<N> implements Graph<N, E> {

    @Override
    public Iterator<N> iterator() {
        return nodes().iterator();
    }

    @Override
    public int size() {
        return nodes().size();
    }

    @Override
    public int edgeSize() {
        return edges().size();
    }

    @Override
    public boolean containsEdge(E edge) {
        return containsEdge(edge.getSource(), edge.getTarget());
    }

    @Override
    public boolean addAllEdges(Collection<E> edges) {
        Objects.requireNonNull(edges);
        boolean modified = false;
        for (E e : edges) {
            if (addEdge(e)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean removeAllEdges(Collection<E> edges) {
        Objects.requireNonNull(edges);
        boolean modified = false;
        for (E e : new ArrayList<>(edges)) {
            modified |= removeEdge(e);
        }
        return modified;
    }

    @Override
    public boolean containsEdge(N src, N tgt) {
        return getEdge(src, tgt) != null;
    }

    @Override
    public boolean removeEdge(N src, N tgt) {
        return removeEdge(getEdge(src, tgt));
    }
}
