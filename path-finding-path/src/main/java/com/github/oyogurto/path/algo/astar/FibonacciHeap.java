package com.github.oyogurto.path.algo.astar;

import lombok.Getter;

/**
 * @author Jingchun.Zhou
 * @since 9/2/2022
 */
public class FibonacciHeap <K extends Comparable<K>, V> {
    private int nodeCount = 0;
    private Node<K, V> minRoot = null;

    public boolean isEmpty() {
        return nodeCount == 0;
    }

    public Handle<K, V> insert(K key, V value) {
        Node<K, V> node = new Node<>(key, value);
        if (minRoot == null) {
            minRoot = node;
        }
    }

    private void insertRoot(Node<K, V> x)
    {
        x.right = minRoot.right;
        x.left = minRoot;
        x.right.left = x;
        x.left.right = x;
        x.mark = false;
        x.parent = null;

        if (x.key.compareTo(minRoot.key) < 0) {
            minRoot = x;
        }
    }

    public interface Handle<K, V> {
        boolean isValid();
    }

    private static class Node<K, V> implements Handle<K, V> {
        @Getter
        private boolean valid = true;
        private K key;
        private V value;
        private Node<K, V> child = null;
        private Node<K, V> parent = null;
        private Node<K, V> left = this;
        private Node<K, V> right = this;
        private int degree = 0;
        private boolean mark = false;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
