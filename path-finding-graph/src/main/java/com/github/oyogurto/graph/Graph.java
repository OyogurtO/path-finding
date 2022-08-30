package com.github.oyogurto.graph;

import java.util.Collection;

/**
 * @author Jingchun.Zhou
 * @since 8/30/2022
 */
public interface Graph<N, E extends Edge<N>> extends Collection<N> {
    /**
     * Gets all nodes in this graph.
     * @return a collection view of the nodes in this graph
     */
    Collection<N> nodes();

    /**
     * Gets all edges in this graph.
     * @return a collection view of the edges in this graph
     */
    Collection<E> edges();

    /**
     * Gets the number of edges in this graph
     * @return the number of edges in this graph
     */
    int edgeSize();

    /**
     * Checks if this graph contains the specified edge.
     * @param edge the edge whose presence in this graph is to be tested
     * @return <code>true</code> if this graph contains the specified edge
     */
    boolean containsEdge(E edge);

    /**
     * Adds the specified edge to this graph if it is not already present.
     * @param edge edge to be added to this graph
     * @return <code>true</code> if this graph did not contain the specified edge
     */
    boolean addEdge(E edge);

    /**
     * Removes the specified edge from this graph if it is present.
     * @param edge edge to be removed from this graph
     * @return <code>true</code> if this graph contained the specified edge
     */
    boolean removeEdge(E edge);

    /**
     * Adds all edges in the specified collection to this graph if they are not already present.
     * @param edges edge collection to be added to this graph
     * @return <code>true</code> if this graph changed
     */
    boolean addAllEdges(Collection<E> edges);

    /**
     * Removes all edges in the specified collection from this graph if they are present.
     * @param edges edge collection to be removed from this graph
     * @return <code>true</code> if this graph changed
     */
    boolean removeAllEdges(Collection<E> edges);

    /**
     * Checks if this graph contains the specified edge.
     * @param src the source node of the edge
     * @param tgt the target node of the edge
     * @return <code>true</code> if this graph contains the specified edge
     */
    boolean containsEdge(N src, N tgt);

    /**
     * Removes the specified edge from this graph if it is present.
     * @param src the source node of the edge
     * @param tgt the target node of the edge
     * @return <code>true</code> if this graph contained the specified edge
     */
    boolean removeEdge(N src, N tgt);

    /**
     * Gets the specified edge.
     * @param src the source node of the edge
     * @param tgt the target node of the edge
     * @return the specified edge, or <code>null</code> if not exist
     */
    E getEdge(N src, N tgt);

    /**
     * Gets all edges with the given node as source.
     * @param node the source node of the edge
     * @return all edges with the given node as source
     */
    Collection<E> outgoingEdges(N node);

    /**
     * Gets all edges with the given node as target.
     * @param node the target node of the edge
     * @return all edges with the given node as target
     */
    Collection<E> incomingEdges(N node);
}
