package ch.hslu.ad.sw10;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FixedSizeHeapTest {

    @Test
    void testConstructor() {
        FixedSizeHeap heap = new FixedSizeHeap(10);

        assertThat(heap.isEmpty()).isTrue();
        assertThat(heap.size()).isZero();
    }

    @Test
    void testInsert_singleElement() {
        FixedSizeHeap heap = new FixedSizeHeap(5);

        heap.insert(5);

        assertThat(heap.size()).isEqualTo(1);
        assertThat(heap.toArray()).containsExactly(5, 0, 0, 0, 0);
    }

    @Test
    void testInsert_twoElements_bubbleUp() {
        FixedSizeHeap heap = new FixedSizeHeap(5);

        heap.insert(5);
        heap.insert(10);

        assertThat(heap.toArray()).containsExactly(10, 5, 0, 0, 0);
    }

    @Test
    void testInsert_threeElements_completeTree() {
        FixedSizeHeap heap = new FixedSizeHeap(5);

        heap.insert(5);
        heap.insert(10);
        heap.insert(3);

        assertThat(heap.toArray()).containsExactly(10, 5, 3, 0, 0);
    }

    @Test
    void testInsert_requiresMultipleBubbleUps() {
        FixedSizeHeap heap = new FixedSizeHeap(5);

        heap.insert(3);
        heap.insert(4);
        heap.insert(5);
        heap.insert(10);

        assertThat(heap.toArray()).containsExactly(10, 5, 4, 3, 0);
    }

    @Test
    void testInsert_increasingOrder() {
        FixedSizeHeap heap = new FixedSizeHeap(7);

        heap.insert(1);
        heap.insert(2);
        heap.insert(3);
        heap.insert(4);
        heap.insert(5);

        assertThat(heap.toArray()).containsExactly(5, 4, 2, 1, 3, 0, 0);
    }

    @Test
    void testInsert_decreasingOrder() {
        FixedSizeHeap heap = new FixedSizeHeap(6);

        heap.insert(5);
        heap.insert(4);
        heap.insert(3);
        heap.insert(2);
        heap.insert(1);

        assertThat(heap.toArray()).containsExactly(5, 4, 3, 2, 1, 0);
    }

    @Test
    void testInsert_duplicateValues() {
        FixedSizeHeap heap = new FixedSizeHeap(5);

        heap.insert(5);
        heap.insert(5);
        heap.insert(5);

        assertThat(heap.toArray()).containsExactly(5, 5, 5, 0, 0);
    }

    @Test
    void testInsert_untilCapacity() {
        FixedSizeHeap heap = new FixedSizeHeap(3);

        heap.insert(1);
        heap.insert(2);
        heap.insert(3);

        assertThat(heap.size()).isEqualTo(3);
        assertThat(heap.toArray()).containsExactly(3, 1, 2);
    }

    @Test
    void testInsert_beyondCapacity_shouldThrow() {
        FixedSizeHeap heap = new FixedSizeHeap(2);

        heap.insert(1);
        heap.insert(2);

        assertThatThrownBy(() -> heap.insert(3)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void testPeek_singleElement() {
        FixedSizeHeap heap = new FixedSizeHeap(5);

        heap.insert(5);

        assertThat(heap.peek()).isEqualTo(5);
    }

    @Test
    void testPeek_multipleElements_returnsMax() {
        FixedSizeHeap heap = new FixedSizeHeap(10);

        heap.insert(3);
        heap.insert(10);
        heap.insert(7);

        assertThat(heap.peek()).isEqualTo(10);
    }

    @Test
    void testPeek_doesNotModifyHeapStructure() {
        FixedSizeHeap heap = new FixedSizeHeap(10);

        heap.insert(5);
        heap.insert(10);
        heap.insert(3);

        int[] before = java.util.Arrays.copyOf(heap.toArray(), heap.size());

        heap.peek();

        int[] after = java.util.Arrays.copyOf(heap.toArray(), heap.size());

        assertThat(after).containsExactly(before);
    }

    @Test
    void testPeek_calledMultipleTimes_returnsSameValue() {
        FixedSizeHeap heap = new FixedSizeHeap(10);

        heap.insert(4);
        heap.insert(9);
        heap.insert(1);

        int first = heap.peek();
        int second = heap.peek();

        assertThat(first).isEqualTo(9);
        assertThat(second).isEqualTo(9);
    }

    @Test
    void testPeek_afterInsert_stillCorrect() {
        FixedSizeHeap heap = new FixedSizeHeap(10);

        heap.insert(5);
        assertThat(heap.peek()).isEqualTo(5);

        heap.insert(8);
        assertThat(heap.peek()).isEqualTo(8);

        heap.insert(3);
        assertThat(heap.peek()).isEqualTo(8);
    }

    @Test
    void testPeek_onEmptyHeap_shouldThrow() {
        FixedSizeHeap heap = new FixedSizeHeap(5);

        assertThatThrownBy(heap::peek).isInstanceOf(java.util.NoSuchElementException.class);
    }

    @Test
    void testPop_singleElement() {
        FixedSizeHeap heap = new FixedSizeHeap(5);

        heap.insert(5);

        int result = heap.pop();

        assertThat(result).isEqualTo(5);
        assertThat(heap.size()).isEqualTo(0);
    }

    @Test
    void testPop_returnsMaxElement() {
        FixedSizeHeap heap = new FixedSizeHeap(10);

        heap.insert(3);
        heap.insert(10);
        heap.insert(7);

        assertThat(heap.pop()).isEqualTo(10);
    }

    @Test
    void testPop_updatesHeapStructure_correctly() {
        FixedSizeHeap heap = new FixedSizeHeap(10);

        heap.insert(5);
        heap.insert(10);
        heap.insert(3);
        heap.insert(8);

        heap.pop(); // removes 10

        assertThat(java.util.Arrays.copyOf(heap.toArray(), heap.size())).containsExactly(8, 5, 3);
    }

    @Test
    void testPop_multipleElements_descendingOrder() {
        FixedSizeHeap heap = new FixedSizeHeap(10);

        heap.insert(5);
        heap.insert(10);
        heap.insert(3);
        heap.insert(8);

        assertThat(heap.pop()).isEqualTo(10);
        assertThat(heap.pop()).isEqualTo(8);
        assertThat(heap.pop()).isEqualTo(5);
        assertThat(heap.pop()).isEqualTo(3);
    }

    @Test
    void testPop_interleavedOperations() {
        FixedSizeHeap heap = new FixedSizeHeap(5);

        heap.insert(4);
        heap.insert(9);
        heap.insert(1);

        assertThat(heap.pop()).isEqualTo(9);
        assertThat(heap.toArray()).containsExactly(4, 1, 0, 0, 0);

        heap.insert(7);

        assertThat(heap.toArray()).containsExactly(7, 1, 4, 0, 0);
    }

    @Test
    void testPop_reducesSize() {
        FixedSizeHeap heap = new FixedSizeHeap(10);

        heap.insert(5);
        heap.insert(6);

        heap.pop();

        assertThat(heap.size()).isEqualTo(1);
    }

    @Test
    void testPop_onEmptyHeap_shouldThrow() {
        FixedSizeHeap heap = new FixedSizeHeap(5);

        assertThatThrownBy(heap::pop).isInstanceOf(java.util.NoSuchElementException.class);
    }

    @Test
    void testIsEmpty_newHeap() {
        FixedSizeHeap heap = new FixedSizeHeap(5);

        assertThat(heap.isEmpty()).isTrue();
    }

    @Test
    void testIsEmpty_afterInsert() {
        FixedSizeHeap heap = new FixedSizeHeap(5);

        heap.insert(1);

        assertThat(heap.isEmpty()).isFalse();
    }

    @Test
    void testIsEmpty_afterInsertAndPop() {
        FixedSizeHeap heap = new FixedSizeHeap(5);

        heap.insert(1);
        heap.pop();

        assertThat(heap.isEmpty()).isTrue();
    }

    @Test
    void testSize_newHeap() {
        FixedSizeHeap heap = new FixedSizeHeap(5);

        assertThat(heap.size()).isEqualTo(0);
    }

    @Test
    void testSize_afterMultipleInserts() {
        FixedSizeHeap heap = new FixedSizeHeap(5);

        heap.insert(1);
        heap.insert(2);
        heap.insert(3);

        assertThat(heap.size()).isEqualTo(3);
    }

    @Test
    void testSize_afterInsertAndPop() {
        FixedSizeHeap heap = new FixedSizeHeap(5);

        heap.insert(1);
        heap.insert(2);
        heap.pop();

        assertThat(heap.size()).isEqualTo(1);
    }

    @Test
    void testToArray_emptyHeap() {
        FixedSizeHeap heap = new FixedSizeHeap(3);

        assertThat(java.util.Arrays.copyOf(heap.toArray(), heap.size())).isEmpty();
    }

    @Test
    void testToArray_afterInsert() {
        FixedSizeHeap heap = new FixedSizeHeap(5);

        heap.insert(5);
        heap.insert(10);
        heap.insert(3);

        assertThat(java.util.Arrays.copyOf(heap.toArray(), heap.size())).containsExactly(10, 5, 3);
    }

    @Test
    void testToArray_afterPop() {
        FixedSizeHeap heap = new FixedSizeHeap(5);

        heap.insert(5);
        heap.insert(10);
        heap.insert(3);

        heap.pop(); // remove 10

        assertThat(java.util.Arrays.copyOf(heap.toArray(), heap.size())).containsExactly(5, 3);
    }

    @Test
    void testToArray_doesNotExposeInternalGarbage() {
        FixedSizeHeap heap = new FixedSizeHeap(5);

        heap.insert(1);
        heap.insert(2);

        int[] visible = java.util.Arrays.copyOf(heap.toArray(), heap.size());

        assertThat(visible).containsExactly(2, 1);
    }
}
