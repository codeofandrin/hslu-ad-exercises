package ch.hslu.ad.sw03.binary_search_tree;

import java.util.*;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinarySearchTree<T extends Comparable<T>> implements ITree<T> {
    private static final Logger LOG = LoggerFactory.getLogger(BinarySearchTree.class);

    private Node<T> root = null;
    private int size = 0;

    public BinarySearchTree() {}

    public BinarySearchTree(final Collection<T> items) {
        items.forEach(this::add);
    }

    @SafeVarargs
    public BinarySearchTree(final T... items) {
        new ArrayList<>(Arrays.asList(items)).forEach(this::add);
    }

    private void recInOrder(final Consumer<T> action, final Node<T> node) {
        if (node == null) {
            return;
        }

        // traverse left
        recInOrder(action, node.getLeft());
        // process node
        action.accept(node.getItem());
        // traverse right
        recInOrder(action, node.getRight());
    }

    public List<T> inOrder() {
        List<T> items = new ArrayList<>();
        forEachInOrder(items::add);
        return items;
    }

    public void forEachInOrder(final Consumer<T> action) {
        recInOrder(action, this.root);
    }

    private void recPreOrder(final Consumer<T> action, final Node<T> node) {
        if (node == null) {
            return;
        }

        // process node
        action.accept(node.getItem());
        // traverse left
        recPreOrder(action, node.getLeft());
        // traverse right
        recPreOrder(action, node.getRight());
    }

    public List<T> preOrder() {
        List<T> items = new ArrayList<>();
        forEachPreOrder(items::add);
        return items;
    }

    public void forEachPreOrder(final Consumer<T> action) {
        recPreOrder(action, this.root);
    }

    private void recPostOrder(final Consumer<T> action, final Node<T> node) {
        if (node == null) {
            return;
        }

        // traverse left
        recPostOrder(action, node.getLeft());
        // traverse right
        recPostOrder(action, node.getRight());
        // process node
        action.accept(node.getItem());
    }

    public List<T> postOrder() {
        List<T> items = new ArrayList<>();
        forEachPostOrder(items::add);
        return items;
    }

    public void forEachPostOrder(final Consumer<T> action) {
        recPostOrder(action, this.root);
    }

    private boolean recAdd(final Node<T> current, final Node<T> target) {
        LOG.info("add(), current node: {}", current == null ? null : current.getItem());

        if (current == null) {
            // first item
            this.root = target;
            return true;
        }

        int compare = target.compareTo(current);
        if (compare < 0) {
            // left
            LOG.info("add(), left: {} < {}", target.getItem(), current.getItem());
            if (current.getLeft() == null) {
                current.setLeft(
                        new Node<>(target.getItem(), null, null, current, NodeDirection.LEFT));
                return true;
            }

            return this.recAdd(current.getLeft(), target);
        } else if (compare > 0) {
            // right
            LOG.info("add(), right: {} > {}", target.getItem(), current.getItem());
            if (current.getRight() == null) {
                current.setRight(
                        new Node<>(target.getItem(), null, null, current, NodeDirection.RIGHT));
                return true;
            }

            return this.recAdd(current.getRight(), target);
        } else {
            // match
            LOG.info("add(), match: {} = {}", target.getItem(), current.getItem());
            return false;
        }
    }

    @Override
    public boolean add(T item) {
        boolean result = recAdd(this.root, new Node<>(item, null, null, null, null));
        if (result) {
            this.size++;
        }
        return result;
    }

    private Node<T> recSearchMostLeftOnRight(final Node<T> current) {
        Node<T> left = current.getLeft();

        if (left == null) {
            return current;
        }

        return recSearchMostLeftOnRight(left);
    }

    private boolean recRemove(final Node<T> current, final Node<T> target) {
        LOG.info("remove(), current node: {}", current == null ? null : current.getItem());

        Node<T> match = this.recRetrieveNode(current, target);
        if (match == null) {
            // not found
            return false;
        }

        Node<T> left = match.getLeft();
        Node<T> right = match.getRight();
        Node<T> parent = match.getParent();
        boolean isLeft = match.isLeft();

        if (left == null && right == null) {
            // target has no children -> let's directly set target to null
            if (parent == null) {
                this.root = null;
            } else {
                if (isLeft) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
            }

            return true;
        }

        if (right == null) {
            // target has only left child -> let's replace target with the only child
            if (parent == null) {
                this.root = match.getLeft();
            } else {
                if (isLeft) {
                    parent.setLeft(match.getLeft());
                } else {
                    parent.setRight(match.getLeft());
                }
            }

            return true;
        }

        if (left == null) {
            // target has only right child -> let's replace target with the only child
            if (parent == null) {
                this.root = match.getRight();
            } else {
                if (isLeft) {
                    parent.setLeft(match.getRight());
                } else {
                    parent.setRight(match.getRight());
                }
            }

            return true;
        }

        // no child is null, both need be considered
        Node<T> mostLeftOnRight = recSearchMostLeftOnRight(right);
        Node<T> mostLeftOnRightParent = mostLeftOnRight.getParent();

        // we only change the item, not the relations, otherwise we would remove all children of the
        // removed node as well
        match.setItem(mostLeftOnRight.getItem());

        // check because the "most left on right" could also be a right child (e.g. if it's a
        // straight right line)
        if (mostLeftOnRight.isLeft()) {
            mostLeftOnRightParent.setLeft(null);
        } else {
            // if it's a right child here, there can't be a left child to consider
            mostLeftOnRightParent.setRight(mostLeftOnRight.getRight());
        }

        return true;
    }

    @Override
    public boolean remove(T item) {
        boolean result = recRemove(this.root, new Node<>(item, null, null, null, null));
        if (result) {
            size--;
        }
        return result;
    }

    private Node<T> recRetrieveNode(final Node<T> current, final Node<T> target) {
        LOG.info("recRetrieveNode(), current node: {}", current == null ? null : current.getItem());

        if (current == null) {
            // not found
            return null;
        }

        int compare = target.compareTo(current);
        if (compare < 0) {
            // left
            LOG.info("recRetrieveNode(), left: {} < {}", target.getItem(), current.getItem());
            return this.recRetrieveNode(current.getLeft(), target);
        } else if (compare > 0) {
            // right
            LOG.info("recRetrieveNode(), right: {} > {}", target.getItem(), current.getItem());
            return this.recRetrieveNode(current.getRight(), target);
        } else {
            // match
            LOG.info("recRetrieveNode(), match: {} = {}", target.getItem(), current.getItem());
            return current;
        }
    }

    @Override
    public boolean contains(T item) {
        return this.recRetrieveNode(this.root, new Node<>(item, null, null, null, null)) != null;
    }

    public int size() {
        return this.size;
    }

    public T root() {
        return this.root == null ? null : this.root.getItem();
    }
}
