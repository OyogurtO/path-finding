package com.github.oyogurto.path;

import com.github.oyogurto.graph.Edge;

import java.util.List;

/**
 * @author Jingchun.Zhou
 * @since 8/31/2022
 */
public interface Path<S, A extends Edge<S>> {
    S getStart();
    S getGoal();
    List<S> states();
    List<A> actions();
}
