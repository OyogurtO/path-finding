package com.github.oyogurto.path.algo.astar;

import com.github.oyogurto.graph.Edge;
import com.github.oyogurto.graph.Graph;
import com.github.oyogurto.path.DefaultPath;
import com.github.oyogurto.path.Path;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Jingchun.Zhou
 * @since 9/1/2022
 */
public class Astar<N, E extends Edge<N>> {
    private final Graph<N,E> graph;
    private final CostFunction<N, E> costFunction;
    private final N start;
    private final Collection<N> goals;

    private final Map<N, N> predecessorMap = new HashMap<>();

    private N reachedGoal;

    public Astar(Graph<N, E> graph, CostFunction<N, E> costFunction, N start, Collection<N> goals) {
        this.graph = graph;
        this.costFunction = costFunction;
        this.start = start;
        this.goals = goals;
    }

    private void run() {

    }

    public Path<N, E> getPath(Function<N, DefaultPath<N, E>> pathFactory) {
        if (reachedGoal == null) {
            return null;
        }
        DefaultPath<N, E> path = pathFactory.apply(start);
        N src, tgt = reachedGoal;
        while ((src = predecessorMap.get(tgt)) != null) {
            path.addEdge(graph.getEdge(src, tgt));
            tgt = src;
        }
        return path;
    }
}
