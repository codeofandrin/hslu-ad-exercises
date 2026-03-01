package ch.hslu.ad.sw02.stack;

/**
 * Base of all stack collections.
 * @param <T> The stored data type
 */
public interface IStack<T> {

    /**
     * Returns the size of the stack
     * @return The size
     */
    int size();

    /**
     * Returns the count of valid items in the stack
     * @return The count
     */
    int count();

    /**
     * Pushes an item on top of the stack
     * @param item The item to push on
     * @return Whether the operation was successful or not.
     */
    boolean push(T item);

    /**
     * Pops out the item on top of the stack and returns it.
     * @return The top item
     */
    T pop();

    /**
     * Returns the item on top of the stack without removing it
     * @return The top item
     */
    T peek();

    /**
     * Clears the stack.
     */
    void clear();

    /**
     * Returns whether the stack is full or not.
     * @return Whether the stack is full or not.
     */
    boolean isFull();

    /**
     * Returns whether the stack is empty or not.
     * @return Whether the stack is empty or not.
     */
    boolean isEmpty();
}
