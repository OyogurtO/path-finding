package com.github.oyogurto.graph;

/**
 * @author Jingchun.Zhou
 * @since 8/30/2022
 */
public interface Edge<N> {
    /**
     * Get the source node.
     * @return the source node
     */
    N getSource();

    /**
     * Get the target node
     * @return the target node
     */
    N getTarget();
}
