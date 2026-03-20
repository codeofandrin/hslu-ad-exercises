package ch.hslu.ad.sw04.hash_set_list;

import ch.hslu.ad.sw02.chain_list.Node;
import java.util.Arrays;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// only disadvantage is the use of a node, but still O(1) for add!
// search is O(n) in worst case
public class HashSetList<T> {
    private static final Logger LOG = LoggerFactory.getLogger(HashSetList.class);

    private Node<T>[] items;
    private final int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public HashSetList(final int capacity) {
        this.capacity = capacity;
        this.items = (Node<T>[]) new Node[capacity];
    }

    private int getIndex(final T item) {
        return Math.abs(item.hashCode() % this.capacity);
    }

    public boolean add(final T item) {
        // optional
        if (this.size >= this.capacity) {
            return false;
        }

        final int index = this.getIndex(item);

        if (this.contains(item)) {
            return false;
        }

        LOG.info("index of {}: {}", item, index);

        Node<T> currentHead = this.items[index];
        Node<T> newHead = new Node<>(item);

        if (this.items[index] != null) {
            newHead.setNext(currentHead);
        }
        this.items[index] = newHead;
        this.size++;
        return true;
    }

    public boolean contains(final T item) {
        if (this.size == 0) {
            return false;
        }

        final int index = this.getIndex(item);

        if (this.items[index] == null) {
            return false;
        }

        Node<T> current = this.items[index];
        while (current != null) {
            if (Objects.equals(current.getItem(), item)) {
                return true;
            }
            current = current.getNext();
        }

        return false;
    }

    public boolean remove(final T item) {
        if (this.size == 0) {
            return false;
        }

        final int index = this.getIndex(item);

        if (this.items[index] == null) {
            return false;
        }

        Node<T> before = null;
        Node<T> current = this.items[index];
        while (current != null) {
            if (Objects.equals(current.getItem(), item)) {
                if (before == null) {
                    // first item match
                    this.items[index] = current.getNext();
                } else {
                    before.setNext(current.getNext());
                }

                this.size--;
                return true;
            }

            before = current;
            current = current.getNext();
        }

        return false;
    }

    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        return "HashSetList{"
                + "items="
                + Arrays.toString(items)
                + ", capacity="
                + capacity
                + ", size="
                + size
                + '}';
    }
}
