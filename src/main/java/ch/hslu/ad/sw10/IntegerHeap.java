package ch.hslu.ad.sw10;

/**
 * A heap for integers with max-heap property.
 * The largest value is always at the root.
 */
public interface IntegerHeap {

    /**
     * Inserts a new element into the heap.
     * After insertion, the heap property is restored
     * (typically by "bubbling up" the element).
     *
     * @param element the value to insert
     */
    void insert(final int element);

    /**
     * Removes and returns the maximum element from the heap.
     * After removal, the heap property is restored
     * (typically by "bubbling down" the new root).
     *
     * @return the largest element currently in the heap
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    int pop();

    /**
     * Returns the maximum element without removing it.
     *
     * @return the largest element currently in the heap
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    int peek();

    /**
     * Returns the number of elements currently stored in the heap.
     *
     * @return the current heap size
     */
    int size();

    /**
     * Checks whether the heap contains no elements.
     *
     * @return {@code true} if the heap is empty, {@code false} otherwise
     */
    boolean isEmpty();
}
