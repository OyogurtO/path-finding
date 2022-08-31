package com.github.oyogurto.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Jingchun.Zhou
 * @since 8/31/2022
 */
public class DefaultGraphTest {
    @Test
    void test1() {
        DefaultGraph<Integer, DefaultEdge<Integer>> graph = new DefaultGraph<>();
        graph.addEdge(new DefaultEdge<>(1, 2));
        graph.addEdge(new DefaultEdge<>(2, 3));
        Assertions.assertEquals(2, graph.edgeSize());
        graph.remove(2);
        Assertions.assertEquals(0, graph.edgeSize());
    }
}
