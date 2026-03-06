package ch.hslu.ad.sw02.stack;

public class Stack<T> implements IStack<T> {

    private Object[] items;
    private int maxSize = 1000;
    private int nextIndex = 0;

    public Stack() {
        this.items = new Object[this.maxSize];
    }

    public Stack(final int capacity) {
        this.maxSize = capacity;
        this.items = new Object[this.maxSize];
    }

    @Override
    public int size() {
        return this.maxSize;
    }

    @Override
    public int count() {
        return this.nextIndex;
    }

    @Override
    public boolean push(final T item) {
        if (!this.isFull()) {
            this.items[nextIndex] = item;
            this.nextIndex++;
            return true;
        }
        return false;
    }

    @Override
    public T pop() {
        T head = this.peek();
        if (head == null) {
            return null;
        }
        this.items[this.nextIndex - 1] = null;
        this.nextIndex--;
        return head;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T peek() {
        if (!this.isEmpty()) {
            return (T) this.items[this.nextIndex - 1];
        }
        return null;
    }

    @Override
    public void clear() {
        this.items = new Object[this.size()];
        this.nextIndex = 0;
    }

    @Override
    public boolean isFull() {
        return this.count() == this.size();
    }

    @Override
    public boolean isEmpty() {
        return this.count() == 0;
    }
}
