package ch.hslu.ad.sw03.binary_search_tree;

import java.util.Objects;

final class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    private T item;
    private Node<T> left;
    private Node<T> right;

    // makes removing easier
    private Node<T> parent;
    private final NodeDirection nodeDirection;

    Node(
            final T item,
            final Node<T> left,
            final Node<T> right,
            final Node<T> parent,
            final NodeDirection nodeDirection) {
        this.item = item;
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.nodeDirection = nodeDirection;
    }

    T getItem() {
        return this.item;
    }

    Node<T> getLeft() {
        return this.left;
    }

    Node<T> getRight() {
        return this.right;
    }

    Node<T> getParent() {
        return this.parent;
    }

    void setLeft(Node<T> left) {
        this.left = left;
    }

    void setRight(Node<T> right) {
        this.right = right;
    }

    void setItem(T item) {
        this.item = item;
    }

    boolean isLeft() {
        return this.nodeDirection == NodeDirection.LEFT;
    }

    boolean isRight() {
        return this.nodeDirection == NodeDirection.RIGHT;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.item);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        return obj instanceof Node<?> o && Objects.equals(this.item, o.item);
    }

    @Override
    public int compareTo(Node<T> o) {
        if (this == o) {
            return 0;
        }

        return item.compareTo(o.item);
    }
}
