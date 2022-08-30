package com.github.oyogurto.graph;

import com.github.oyogurto.collection.ComputableTable;
import com.github.oyogurto.collection.HashBasedComputableTable;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Jingchun.Zhou
 * @since 8/30/2022
 */
public class DefaultGraph<N, E extends Edge<N>> extends AbstractGraph<N, E> {
    protected final Set<N> nodeSet = new HashSet<>();
    protected final ComputableTable<N, N, E> edgeTable = HashBasedComputableTable.create();

    @Override
    public Collection<N> nodes() {
        return nodeSet;
    }

    @Override
    public boolean contains(Object o) {
        return nodeSet.contains(o);
    }

    @Override
    public boolean add(N n) {
        return nodeSet.add(n);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean remove(Object o) {
        if (!contains(o)) {
            return false;
        } else {
            N node = (N) o;
            for (E edge : outgoingEdges(node)) {
                edgeTable.remove(edge.getSource(), edge.getTarget());
            }
            for (E edge : incomingEdges(node)) {
                edgeTable.remove(edge.getSource(), edge.getTarget());
            }
            nodeSet.remove(node);
            return true;
        }
    }

    @Override
    public Collection<E> edges() {
        return edgeTable.values();
    }

    @Override
    public boolean addEdge(E edge) {
        nodeSet.add(edge.getSource());
        nodeSet.add(edge.getTarget());
        return edgeTable.putIfAbsent(edge.getSource(), edge.getTarget(), edge) == null;
    }

    @Override
    public boolean removeEdge(E edge) {
        return edgeTable.remove(edge.getSource(), edge.getTarget()) != null;
    }

    @Override
    public E getEdge(N src, N tgt) {
        return edgeTable.get(src, tgt);
    }

    @Override
    public Collection<E> outgoingEdges(N node) {
        return edgeTable.row(node).values();
    }

    @Override
    public Collection<E> incomingEdges(N node) {
        return edgeTable.column(node).values();
    }
}
