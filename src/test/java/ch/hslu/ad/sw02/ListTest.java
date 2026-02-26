package ch.hslu.ad.sw02;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ListTest {

    @Test
    void testAddOne() {
        List<Integer> list = new List<>();
        boolean ret = list.add(10);

        assertThat(ret).isTrue();
        assertThat(list.head()).isNotNull();
        assertThat(list.head().getItem()).isEqualTo(10);
    }

    @Test
    void testAddTwo() {
        List<Integer> list = new List<>();
        boolean ret1 = list.add(20);
        boolean ret2 = list.add(30);

        assertThat(ret1).isTrue();
        assertThat(ret2).isTrue();

        assertThat(list.head()).isNotNull();
        assertThat(list.head().getItem()).isEqualTo(30);

        Node<Integer> next = list.head().getNext();
        assertThat(next).isNotNull();
        assertThat(next.getItem()).isEqualTo(20);

        Node<Integer> next2 = next.getNext();
        assertThat(next2).isNull();
    }

    @Test
    void testSize() {
        List<Integer> list = new List<>();
        list.add(40);

        assertThat(list.size()).isEqualTo(1);
        list.add(50);
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    void testContainsTrue() {
        List<Integer> list = new List<>();
        list.add(60);
        list.add(70);

        assertThat(list.contains(60)).isTrue();
        assertThat(list.contains(70)).isTrue();
    }

    @Test
    void testContainsFalse() {
        List<Integer> list = new List<>();
        list.add(80);
        list.add(90);

        assertThat(list.contains(100)).isFalse();
    }

    @Test
    void testContainsEmpty() {
        List<Integer> list = new List<>();

        assertThat(list.contains(100)).isFalse();
    }

    @Test
    void testPop() {
        List<Integer> list = new List<>();
        list.add(100);

        assertThat(list.pop()).isEqualTo(100);
        assertThat(list.size()).isEqualTo(0);
    }

    @Test
    void testPopEmpty() {
        List<Integer> list = new List<>();

        assertThat(list.pop()).isNull();
    }

    @Test
    void testRemoveEmpty() {
        List<Integer> list = new List<>();

        assertThat(list.remove(0)).isFalse();
    }

    @Test
    void testRemoveOneItem() {
        List<Integer> list = new List<>();
        list.add(110);

        assertThat(list.remove(110)).isTrue();
        assertThat(list.head()).isNull();
        assertThat(list.size()).isEqualTo(0);
    }

    @Test
    void testRemoveFirst() {
        List<Integer> list = new List<>();
        list.add(120);
        list.add(130);

        assertThat(list.remove(130)).isTrue();
        assertThat(list.head()).isNotNull();
        assertThat(list.head().getItem()).isEqualTo(120);
        assertThat(list.head().getNext()).isNull();
        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    void testRemoveMid() {
        List<Integer> list = new List<>();
        list.add(140);
        list.add(150);
        list.add(160);

        assertThat(list.remove(150)).isTrue();
        assertThat(list.head()).isNotNull();
        assertThat(list.head().getItem()).isEqualTo(160);
        assertThat(list.head().getNext()).isNotNull();
        assertThat(list.head().getNext().getItem()).isEqualTo(140);
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    void testRemoveLast() {
        List<Integer> list = new List<>();
        list.add(170);
        list.add(180);
        list.add(190);

        assertThat(list.remove(170)).isTrue();
        assertThat(list.head()).isNotNull();
        assertThat(list.head().getItem()).isEqualTo(190);
        assertThat(list.head().getNext()).isNotNull();
        assertThat(list.head().getNext().getItem()).isEqualTo(180);
        assertThat(list.head().getNext().getNext()).isNull();
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    void testRemoveNever() {
        List<Integer> list = new List<>();
        list.add(200);
        list.add(210);
        list.add(220);

        assertThat(list.remove(230)).isFalse();
        assertThat(list.size()).isEqualTo(3);
    }

    @Test
    void testIterator() {
        List<Integer> list = new List<>();
        list.add(230);
        list.add(240);
        list.add(250);

        int[] result = {250, 240, 230};
        int index = 0;
        for (Integer item : list) {
            assertThat(item).isEqualTo(result[index]);
            index++;
        }
    }

    @Test
    void testIteratorEmpty() {
        List<Integer> list = new List<>();

        int count = 0;
        for (Integer _ : list) {
            count++;
        }

        assertThat(count).isEqualTo(0);
    }
}
