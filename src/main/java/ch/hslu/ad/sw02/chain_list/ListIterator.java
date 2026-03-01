package ch.hslu.ad.sw02.chain_list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListIterator<T> implements Iterator<T> {
    private Node<T> current;

    public ListIterator(final Node<T> current) {
        this.current = current;
    }

    @Override
    public boolean hasNext() {
        return this.current != null && this.current.getNext() != null;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        Node<T> temp = this.current;
        this.current = temp.getNext();
        return temp.getItem();
    }
}
