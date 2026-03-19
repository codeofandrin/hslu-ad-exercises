package ch.hslu.ad.sw04.hash_set_list;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HashSetListTest {

    private HashSetList<Integer> hashSet;

    @BeforeEach
    void setUp() {
        hashSet = new HashSetList<>(10);
    }

    @Test
    void testAddSingleElement() {
        boolean result = hashSet.add(5);
        assertThat(result).isTrue();
        assertThat(hashSet.size()).isEqualTo(1);
    }

    @Test
    void testAddMultipleElements() {
        boolean result1 = hashSet.add(5);
        boolean result2 = hashSet.add(15);
        boolean result3 = hashSet.add(25);

        assertThat(result1).isTrue();
        assertThat(result2).isTrue();
        assertThat(result3).isTrue();
        assertThat(hashSet.size()).isEqualTo(3);
    }

    @Test
    void testAddDuplicateElementImmediately() {
        hashSet.add(5);
        boolean result = hashSet.add(5);

        assertThat(result).isFalse();
        assertThat(hashSet.size()).isEqualTo(1);
    }

    @Test
    void testAddDuplicateElementLater() {
        hashSet.add(5);
        hashSet.add(15);
        System.out.println(hashSet);

        boolean result = hashSet.add(5);
        System.out.println(hashSet);

        assertThat(result).isFalse();
        assertThat(hashSet.size()).isEqualTo(2);
    }

    @Test
    void testAddWhenFull() {
        HashSetList<Integer> smallSet = new HashSetList<>(2);
        smallSet.add(1);
        smallSet.add(2);
        boolean result = smallSet.add(3);

        assertThat(result).isFalse();
        assertThat(smallSet.size()).isEqualTo(2);
    }

    @Test
    void testContainsExistingElement() {
        hashSet.add(5);
        boolean result = hashSet.contains(5);

        assertThat(result).isTrue();
    }

    @Test
    void testContainsNonExistingElement() {
        hashSet.add(5);
        boolean result = hashSet.contains(10);

        assertThat(result).isFalse();
    }

    @Test
    void testContainsInEmptySet() {
        boolean result = hashSet.contains(5);

        assertThat(result).isFalse();
    }

    @Test
    void testContainsInZeroSet() {
        hashSet = new HashSetList<>(0);
        boolean result = hashSet.contains(5);

        assertThat(result).isFalse();
    }

    @Test
    void testContainsWithCollisions() {
        hashSet.add(5);
        hashSet.add(15);
        hashSet.add(25);

        assertThat(hashSet.contains(5)).isTrue();
        assertThat(hashSet.contains(15)).isTrue();
        assertThat(hashSet.contains(25)).isTrue();
    }

    @Test
    void testRemoveSingleElement() {
        hashSet.add(5);
        boolean result = hashSet.remove(5);

        assertThat(result).isTrue();
        assertThat(hashSet.size()).isEqualTo(0);
        assertThat(hashSet.contains(5)).isFalse();
    }

    @Test
    void testRemoveMultipleElements() {
        hashSet.add(5);
        hashSet.add(15);
        hashSet.add(25);

        System.out.println(hashSet);

        boolean result1 = hashSet.remove(5);
        System.out.println(hashSet);

        boolean result2 = hashSet.remove(15);
        System.out.println(hashSet);

        assertThat(result1).isTrue();
        assertThat(result2).isTrue();
        assertThat(hashSet.size()).isEqualTo(1);
        assertThat(hashSet.contains(25)).isTrue();
    }

    @Test
    void testRemoveNonExistingElement() {
        hashSet.add(5);
        boolean result = hashSet.remove(10);

        assertThat(result).isFalse();
        assertThat(hashSet.size()).isEqualTo(1);
    }

    @Test
    void testRemoveFromEmptySet() {
        boolean result = hashSet.remove(5);

        assertThat(result).isFalse();
        assertThat(hashSet.size()).isEqualTo(0);
    }

    @Test
    void testRemoveWithCollisions() {
        hashSet.add(5);
        hashSet.add(15);
        hashSet.add(25);
        System.out.println(hashSet);

        boolean result = hashSet.remove(15);
        System.out.println(hashSet);

        assertThat(result).isTrue();
        assertThat(hashSet.size()).isEqualTo(2);
        assertThat(hashSet.contains(5)).isTrue();
        assertThat(hashSet.contains(15)).isFalse();
        assertThat(hashSet.contains(25)).isTrue();
    }

    @Test
    void testSize() {
        assertThat(hashSet.size()).isEqualTo(0);

        hashSet.add(1);
        assertThat(hashSet.size()).isEqualTo(1);

        hashSet.add(2);
        assertThat(hashSet.size()).isEqualTo(2);

        hashSet.remove(1);
        assertThat(hashSet.size()).isEqualTo(1);
    }

    @Test
    void testAddRemoveAddCycle() {
        hashSet.add(5);
        assertThat(hashSet.size()).isEqualTo(1);

        hashSet.remove(5);
        assertThat(hashSet.size()).isEqualTo(0);

        hashSet.add(5);
        assertThat(hashSet.size()).isEqualTo(1);
        assertThat(hashSet.contains(5)).isTrue();
    }
}
