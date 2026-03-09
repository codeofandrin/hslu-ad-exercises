package ch.hslu.ad.sw03.binary_search_tree;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {

    private static final List<Integer> NODES_SAMPLE = List.of(7, 8, 9, 10, 3, 2, 1, 5, 4, 6);

    @Test
    void testConstructorDefault() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        assertThat(bst.size()).isEqualTo(0);
        assertThat(bst.root()).isNull();
    }

    @Test
    void testConstructorCollection() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(List.of(1, 2, 3));
        assertThat(bst.size()).isEqualTo(3);
        assertThat(bst.root()).isEqualTo(1);
    }

    @Test
    void testConstructorVarArgs() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(4, 5, 6, 7);
        assertThat(bst.size()).isEqualTo(4);
        assertThat(bst.root()).isEqualTo(4);
    }

    @Test
    void testContainsInnerNode() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(NODES_SAMPLE);
        assertThat(bst.contains(3)).isTrue();
    }

    @Test
    void testContainsRoot() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(NODES_SAMPLE);
        assertThat(bst.contains(7)).isTrue();
    }

    @Test
    void testContainsLeafNode() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(NODES_SAMPLE);
        assertThat(bst.contains(1)).isTrue();
    }

    @Test
    void testContainsNever() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(NODES_SAMPLE);
        assertThat(bst.contains(20)).isFalse();
    }

    @Test
    void testContainsEmpty() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        assertThat(bst.contains(5)).isFalse();
    }

    @Test
    void testAdd() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(NODES_SAMPLE);
        assertThat(bst.add(11)).isTrue();
        assertThat(bst.contains(11)).isTrue();
        assertThat(bst.inOrder()).isEqualTo(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
        assertThat(bst.preOrder()).isEqualTo(List.of(7, 3, 2, 1, 5, 4, 6, 8, 9, 10, 11));
        assertThat(bst.postOrder()).isEqualTo(List.of(1, 2, 4, 6, 5, 3, 11, 10, 9, 8, 7));
    }

    @Test
    void testAddDuplicate() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(NODES_SAMPLE);
        assertThat(bst.add(5)).isFalse();
        assertThat(bst.contains(5)).isTrue();
        assertThat(bst.inOrder()).isEqualTo(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        assertThat(bst.preOrder()).isEqualTo(List.of(7, 3, 2, 1, 5, 4, 6, 8, 9, 10));
        assertThat(bst.postOrder()).isEqualTo(List.of(1, 2, 4, 6, 5, 3, 10, 9, 8, 7));
    }

    @Test
    void testAddEmpty() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        assertThat(bst.add(5)).isTrue();
        assertThat(bst.contains(5)).isTrue();
        assertThat(bst.root()).isEqualTo(5);
        assertThat(bst.inOrder()).isEqualTo(List.of(5));
    }

    @Test
    void testAddOneItem() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(7);
        assertThat(bst.add(9)).isTrue();
        assertThat(bst.size()).isEqualTo(2);
        assertThat(bst.root()).isEqualTo(7);
        assertThat(bst.inOrder()).isEqualTo(List.of(7, 9));
        assertThat(bst.preOrder()).isEqualTo(List.of(7, 9));
        assertThat(bst.postOrder()).isEqualTo(List.of(9, 7));
    }

    @Test
    void testInOrder() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(NODES_SAMPLE);
        assertThat(bst.inOrder()).isEqualTo(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }

    @Test
    void testInOrderEmpty() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        assertThat(bst.inOrder()).isEqualTo(List.of());
    }

    @Test
    void testInOrderOneItem() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(520);
        assertThat(bst.inOrder()).isEqualTo(List.of(520));
    }

    @Test
    void testForEachInOrder() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(NODES_SAMPLE);
        List<Integer> items = new ArrayList<>();
        bst.forEachInOrder(items::add);

        assertThat(items).isEqualTo(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }

    @Test
    void testPreOrder() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(NODES_SAMPLE);
        assertThat(bst.preOrder()).isEqualTo(List.of(7, 3, 2, 1, 5, 4, 6, 8, 9, 10));
    }

    @Test
    void testPreOrderEmpty() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        assertThat(bst.preOrder()).isEqualTo(List.of());
    }

    @Test
    void testPreOrderOneItem() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(64);
        assertThat(bst.preOrder()).isEqualTo(List.of(64));
    }

    @Test
    void testForEachPreOrder() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(NODES_SAMPLE);
        List<Integer> items = new ArrayList<>();
        bst.forEachPreOrder(items::add);

        assertThat(items).isEqualTo(List.of(7, 3, 2, 1, 5, 4, 6, 8, 9, 10));
    }

    @Test
    void testPostOrder() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(NODES_SAMPLE);
        assertThat(bst.postOrder()).isEqualTo(List.of(1, 2, 4, 6, 5, 3, 10, 9, 8, 7));
    }

    @Test
    void testPostOrderEmpty() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        assertThat(bst.postOrder()).isEqualTo(List.of());
    }

    @Test
    void testPostOrderOneItem() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(64);
        assertThat(bst.postOrder()).isEqualTo(List.of(64));
    }

    @Test
    void testForEachPostOrder() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(NODES_SAMPLE);
        List<Integer> items = new ArrayList<>();
        bst.forEachPostOrder(items::add);

        assertThat(items).isEqualTo(List.of(1, 2, 4, 6, 5, 3, 10, 9, 8, 7));
    }

    @Test
    void testRemoveInnerNodeWithLeftChild() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(NODES_SAMPLE);
        assertThat(bst.remove(2)).isTrue();
        assertThat(bst.size()).isEqualTo(9);
        assertThat(bst.inOrder()).isEqualTo(List.of(1, 3, 4, 5, 6, 7, 8, 9, 10));
        assertThat(bst.preOrder()).isEqualTo(List.of(7, 3, 1, 5, 4, 6, 8, 9, 10));
        assertThat(bst.postOrder()).isEqualTo(List.of(1, 4, 6, 5, 3, 10, 9, 8, 7));
    }

    @Test
    void testRemoveInnerNodeWithRightChild() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(NODES_SAMPLE);
        assertThat(bst.remove(8)).isTrue();
        assertThat(bst.size()).isEqualTo(9);
        assertThat(bst.inOrder()).isEqualTo(List.of(1, 2, 3, 4, 5, 6, 7, 9, 10));
        assertThat(bst.preOrder()).isEqualTo(List.of(7, 3, 2, 1, 5, 4, 6, 9, 10));
        assertThat(bst.postOrder()).isEqualTo(List.of(1, 2, 4, 6, 5, 3, 10, 9, 7));
    }

    @Test
    void testRemoveInnerNodeWithTwoChildren() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(NODES_SAMPLE);
        assertThat(bst.remove(3)).isTrue();
        assertThat(bst.size()).isEqualTo(9);
        assertThat(bst.inOrder()).isEqualTo(List.of(1, 2, 4, 5, 6, 7, 8, 9, 10));
        assertThat(bst.preOrder()).isEqualTo(List.of(7, 4, 2, 1, 5, 6, 8, 9, 10));
        assertThat(bst.postOrder()).isEqualTo(List.of(1, 2, 6, 5, 4, 10, 9, 8, 7));
    }

    @Test
    void testRemoveRootTwoChildren() {
        BinarySearchTree<Integer> bst =
                new BinarySearchTree<>(List.of(6, 8, 7, 9, 10, 3, 2, 1, 5, 4, 6));
        assertThat(bst.remove(6)).isTrue();
        assertThat(bst.size()).isEqualTo(9);
        assertThat(bst.inOrder()).isEqualTo(List.of(1, 2, 3, 4, 5, 7, 8, 9, 10));
        assertThat(bst.preOrder()).isEqualTo(List.of(7, 3, 2, 1, 5, 4, 8, 9, 10));
        assertThat(bst.postOrder()).isEqualTo(List.of(1, 2, 4, 5, 3, 10, 9, 8, 7));
    }

    @Test
    void testRemoveRootLeftChildren() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(List.of(7, 3, 2, 1, 5, 4, 6));
        assertThat(bst.remove(7)).isTrue();
        assertThat(bst.size()).isEqualTo(6);
        assertThat(bst.inOrder()).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
        assertThat(bst.preOrder()).isEqualTo(List.of(3, 2, 1, 5, 4, 6));
        assertThat(bst.postOrder()).isEqualTo(List.of(1, 2, 4, 6, 5, 3));
    }

    @Test
    void testRemoveRootRightChildren() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(List.of(7, 8, 9, 10));
        assertThat(bst.remove(7)).isTrue();
        assertThat(bst.size()).isEqualTo(3);
        assertThat(bst.inOrder()).isEqualTo(List.of(8, 9, 10));
        assertThat(bst.preOrder()).isEqualTo(List.of(8, 9, 10));
        assertThat(bst.postOrder()).isEqualTo(List.of(10, 9, 8));
    }

    @Test
    void testRemoveRootNoChildren() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(5);
        assertThat(bst.remove(5)).isTrue();
        assertThat(bst.size()).isEqualTo(0);
        assertThat(bst.inOrder()).isEqualTo(List.of());
    }

    @Test
    void testRemoveLeafNode() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(NODES_SAMPLE);
        assertThat(bst.remove(1)).isTrue();
        assertThat(bst.size()).isEqualTo(9);
        assertThat(bst.inOrder()).isEqualTo(List.of(2, 3, 4, 5, 6, 7, 8, 9, 10));
        assertThat(bst.preOrder()).isEqualTo(List.of(7, 3, 2, 5, 4, 6, 8, 9, 10));
        assertThat(bst.postOrder()).isEqualTo(List.of(2, 4, 6, 5, 3, 10, 9, 8, 7));
    }

    @Test
    void testRemoveNever() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(NODES_SAMPLE);
        assertThat(bst.remove(20)).isFalse();
        assertThat(bst.size()).isEqualTo(10);
        assertThat(bst.inOrder()).isEqualTo(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }

    @Test
    void testRemoveEmpty() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        assertThat(bst.remove(5)).isFalse();
    }
}
