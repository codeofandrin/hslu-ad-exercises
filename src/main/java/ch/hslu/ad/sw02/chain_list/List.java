package ch.hslu.ad.sw02.chain_list;

import ch.hslu.ad.sw02.Node;
import java.util.Iterator;
import java.util.Objects;

public class List<T> implements Iterable<T> {
    private Node<T> head = null;
    private long size = 0;

    public long size() {
        return this.size;
    }

    public T peek() {
        return this.head == null ? null : head.getItem();
    }

    public boolean add(T item) {
        this.head = new Node<>(item, this.head);
        this.size++;

        return true;
    }

    public T pop() {
        T temp = null;
        if (this.size > 0) {
            temp = this.head.getItem();
            this.head = this.head.getNext();
            this.size--;
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
                this.size--;
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
