package ch.hslu.ad.sw02.chain_list;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NodeTest {

    @Test
    void testNodeConstructor() {
        Node<Integer> second = new Node<>(10);
        Node<Integer> first = new Node<>(20, second);

        assertThat(first.getItem()).isEqualTo(20);
        assertThat(first.getNext()).isEqualTo(second);
        assertThat(second.getItem()).isEqualTo(10);
        assertThat(second.getNext()).isNull();
    }

    @Test
    void testSetNext() {
        Node<Integer> node = new Node<>(30, new Node<>(40));
        node.setNext(new Node<>(50));

        assertThat(node.getNext().getItem()).isEqualTo(50);
    }
}
