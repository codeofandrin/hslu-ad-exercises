package ch.hslu.ad.sw03.binary_search_tree;

/**
 * Base of all binary search trees with add, remove, contains, and balance operations.
 */
public interface ITree<T> {

    /**
     * Adds an item to the tree.
     * @param item The item to add
     * @return Whether the item was added successfully
     */
    boolean add(T item);

    /**
     * Removes an item from the tree.
     * @param item The item to remove
     * @return Whether the item was removed successfully
     */
    boolean remove(T item);

    /**
     * Searches the item in the binary search tree and checks whether it's in the tree or not..
     * @param item The item to search for
     * @return Whether the item is in the tree or not
     */
    boolean contains(T item);
}
