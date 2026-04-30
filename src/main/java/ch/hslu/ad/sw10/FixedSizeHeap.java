package ch.hslu.ad.sw10;

import java.util.NoSuchElementException;

public final class FixedSizeHeap implements IntegerHeap {

    final int[] data;
    int size = 0;

    FixedSizeHeap(final int capacity) {
        this.data = new int[capacity];
    }

    private int getLeftChildIdx(final int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int getRightChildIdx(final int parentIndex) {
        return 2 * parentIndex + 2;
    }

    private int getParentIdx(final int index) {
        if (index % 2 == 0) {
            return (index - 2) / 2;
        }
        return (index - 1) / 2;
    }

    private void swap(final int a, final int b) {
        final int temp = this.data[a];
        this.data[a] = this.data[b];
        this.data[b] = temp;
    }

    private void bubbleUp(final int index) {
        int parentIdx = this.getParentIdx(index);
        int i = index;
        while (parentIdx >= 0 && this.data[parentIdx] < this.data[i]) {
            this.swap(parentIdx, i);

            i = parentIdx;
            parentIdx = this.getParentIdx(i);
        }
    }

    @Override
    public void insert(final int element) {

        if (this.isEmpty()) {
            this.data[0] = element;
        } else {
            // insert on last niveau on the rightest
            final int nextIdx = size;
            this.data[nextIdx] = element;

            this.bubbleUp(nextIdx);
        }

        this.size++;
    }

    private void bubbleDown() {
        int i = 0;
        while (this.data[this.getRightChildIdx(i)] > 0 || this.data[this.getLeftChildIdx(i)] > 0) {
            int rightIdx = this.getRightChildIdx(i);
            int leftIdx = this.getLeftChildIdx(i);
            int right = this.data[rightIdx];
            int left = this.data[leftIdx];
            if (right > left) {
                this.swap(i, rightIdx);
                i = rightIdx;
            } else {
                this.swap(i, leftIdx);
                i = leftIdx;
            }
        }
    }

    @Override
    public int pop() {
        final int root = this.peek();

        this.data[0] = this.data[this.size - 1];
        this.data[this.size - 1] = 0;
        this.size--;

        this.bubbleDown();

        return root;
    }

    @Override
    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("heap is empty");
        }
        return this.data[0];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    public int[] toArray() {
        return this.data;
    }
}
