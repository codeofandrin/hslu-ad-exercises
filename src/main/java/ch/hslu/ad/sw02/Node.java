package ch.hslu.ad.sw02;

public class Node<T> {

    private Node<T> next = null;
    private T item;

    public Node(final T item) {
        this.item = item;
    }

    public Node(final T item, final Node<T> next) {
        this.item = item;
        this.next = next;
    }

    public T getItem() {
        return item;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(final Node<T> next) {
        this.next = next;
    }
}
