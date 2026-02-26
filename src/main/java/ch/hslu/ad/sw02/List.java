package ch.hslu.ad.sw02;

import java.util.Iterator;
import java.util.Objects;

public class List<T> implements Iterable<T> {
    private Node<T> head = null;
    private long size = 0;

    public long size() {
        return size;
    }

    public Node<T> head() {
        return head;
    }

    public boolean add(T item) {
        this.head = new Node<>(item, this.head);
        size++;

        return true;
    }

    public T pop() {
        T temp = null;
        if (size > 0) {
            temp = this.head.getItem();
            this.head = this.head.getNext();
            size--;
        }

        return temp;
    }

    public boolean contains(T item) {
        boolean ret = false;
        Node<T> current = this.head;

        while (current != null) {
            if (Objects.equals(current.getItem(), item)) {
                ret = true;
                break;
            }

            current = current.getNext();
        }

        return ret;
    }

    public boolean remove(T item) {
        boolean ret = false;
        Node<T> before = null;
        Node<T> current = this.head;

        while (current != null) {
            if (Objects.equals(current.getItem(), item)) {
                if (before == null) {
                    // first item match
                    this.head = current.getNext();
                } else {
                    before.setNext(current.getNext());
                }
                size--;
                ret = true;
                break;
            }

            before = current;
            current = current.getNext();
        }

        return ret;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<>(this.head);
    }
}
