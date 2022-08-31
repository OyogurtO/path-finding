package com.github.oyogurto.graph;

import lombok.Data;

/**
 * @author Jingchun.Zhou
 * @since 8/31/2022
 */
@Data
public class DefaultEdge<N> implements Edge<N> {
    private final N source;
    private final N target;
}
