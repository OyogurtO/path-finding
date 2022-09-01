package com.github.oyogurto.path;

import com.github.oyogurto.graph.DefaultGraph;
import com.github.oyogurto.graph.Edge;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Jingchun.Zhou
 * @since 8/31/2022
 */
public class DefaultPath<N, E extends Edge<N>> extends DefaultGraph<N, E> implements Path<N, E> {
    @Getter
    protected N start;
    @Getter
    protected N goal;

    public DefaultPath(N start) {
        this.start = start;
        this.goal = start;
    }

    public N preState(N state) {
        Collection<E> edges = incomingEdges(state);
        if (edges.size() != 1) {
            return null;
        }
        return edges.iterator().next().getSource();
    }

    public N nextState(N state) {
        Collection<E> edges = outgoingEdges(state);
        if (edges.size() != 1) {
            return null;
        }
        return edges.iterator().next().getTarget();
    }

    public boolean addStart(E edge) {
        if (edge.getTarget().equals(start)) {
            if (super.addEdge(edge)) {
                start = edge.getSource();
                return true;
            }
        }
        return false;
    }

    public boolean addGoal(E edge) {
        if (edge.getSource().equals(goal)) {
            if (super.addEdge(edge)) {
                goal = edge.getTarget();
                return true;
            }
        }
        return false;
    }

    public boolean removeStart() {
        N next = nextState(start);
        if (next != null) {
            super.remove(start);
            start = next;
            return true;
        }
        return false;
    }

    public boolean removeGoal() {
        N pre = preState(goal);
        if (pre != null) {
            super.remove(goal);
            goal = pre;
            return true;
        }
        return false;
    }

    @Override
    public List<N> states() {
        List<N> result = new ArrayList<>();
        N cur = start;
        do {
            result.add(cur);
        } while ((cur = nextState(cur)) != null);
        return result;
    }

    @Override
    public List<E> actions() {
        List<E> result = new ArrayList<>();
        N pre = start;
        N next;
        while ((next = nextState(pre)) != null) {
            result.add(getEdge(pre, next));
            pre = next;
        }
        return result;
    }

    @Override
    public boolean add(N n) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        if (o.equals(start)) {
            return removeStart();
        } else if (o.equals(goal)) {
            return removeGoal();
        }
        throw new IllegalStateException("Only start or goal can be removed. " + o);
    }

    @Override
    public boolean addEdge(E edge) {
        if (edge.getTarget().equals(start)) {
            return addStart(edge);
        } else if (edge.getSource().equals(goal)) {
            return addGoal(edge);
        }
        throw new IllegalStateException("Only edges neighbor to start or goal node can be added" + edge);
    }

    @Override
    public boolean removeEdge(E edge) {
        if (containsEdge(edge)) {
            if (edge.getSource().equals(start)) {
                return removeStart();
            } else if (edge.getTarget().equals(goal)) {
                return removeGoal();
            }
        }
        return false;
    }
}
